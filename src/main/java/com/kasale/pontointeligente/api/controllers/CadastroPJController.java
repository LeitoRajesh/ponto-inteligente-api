package com.kasale.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kasale.pontointeligente.api.dtos.CadastroPJDto;
import com.kasale.pontointeligente.api.entities.Empresa;
import com.kasale.pontointeligente.api.entities.Funcionario;
import com.kasale.pontointeligente.api.enums.PerfilEnum;
import com.kasale.pontointeligente.api.response.Response;
import com.kasale.pontointeligente.api.services.EmpresaService;
import com.kasale.pontointeligente.api.services.FuncionarioService;
import com.kasale.pontointeligente.api.util.PasswordUtil;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrar(@Valid @RequestBody CadastroPJDto cadastroPJDto, BindingResult result) throws NoSuchAlgorithmException{
		log.info("Cadastrando PJ: {}", result.getAllErrors());
		Response<CadastroPJDto> response = new Response<CadastroPJDto>();
		
		this.validarDadosExistentes(cadastroPJDto, result);
		Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDto);
		Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPJDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando daods do cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		
		this.funcionarioService.persistir(funcionario);
		
		response.setData(this.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
	}

	private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {
		CadastroPJDto cadastroPJDto = new CadastroPJDto();
		cadastroPJDto.setId(funcionario.getId());
		cadastroPJDto.setNome(funcionario.getNome());
		cadastroPJDto.setEmail(funcionario.getEmail());
		cadastroPJDto.setCpf(funcionario.getCpf());
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		return cadastroPJDto;
	}

	private Funcionario converterDtoParaFuncionario(@Valid CadastroPJDto cadastroPJDto, BindingResult result) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtil.gerarBCrypt(cadastroPJDto.getSenha()));
		return funcionario;
	}

	private Empresa converterDtoParaEmpresa(@Valid CadastroPJDto cadastroPJDto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());
		return empresa;
	}

	private void validarDadosExistentes(@Valid CadastroPJDto cadastroPJDto, BindingResult result) {
		this.empresaService.buscarPorCnpj(cadastroPJDto.getCnpj()).ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existe")));
		this.funcionarioService.buscarPorCpf(cadastroPJDto.getCpf()).ifPresent(emp -> result.addError(new ObjectError("funcionario", "CPF já existe")));
		this.funcionarioService.buscarPorEmail(cadastroPJDto.getEmail()).ifPresent(emp -> result.addError(new ObjectError("funcionario", "E-mail já existe")));
		
	}
	
}
