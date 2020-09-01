package com.kasale.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.kasale.pontointeligente.api.entities.Empresa;
import com.kasale.pontointeligente.api.entities.Funcionario;
import com.kasale.pontointeligente.api.entities.Lancamento;
import com.kasale.pontointeligente.api.enums.PerfilEnum;
import com.kasale.pontointeligente.api.enums.TipoEnum;
import com.kasale.pontointeligente.api.util.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private Long funcionarioId;
	
	private static final String EMAIL = "email.teste@mail.com";
	private static final String CPF = "57469778020";

	@BeforeEach
	public void setUp() throws Exception {

		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));

		this.funcionarioId = funcionario.getId();
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));

	}
	

	@AfterEach
	public void tearDown() {
		this.empresaRepository.deleteAll();
	}

	
	@Test
	public void testBuscarLancamenrosFuncionarioId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioIdOPaginado() {
		
		PageRequest pageRequest = PageRequest.of(0, 10);
		Page<Lancamento> listaPage = this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
		
		assertEquals(2, listaPage.getTotalElements());
	}
	
	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de Exemplo");
		empresa.setCnpj("47568415000120");
		return empresa;
		
	}
	
	private Funcionario obterDadosFuncionario(final Empresa empresa) {
		
		Funcionario funcionario = new Funcionario();
		funcionario.setEmpresa(empresa);
		funcionario.setNome("Nome do Funcionário");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtil.gerarBCrypt("123456"));
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setQdtHorasAlmoco(0.4F);
		funcionario.setQtdHorasTrabalhadaDia(8F);
		funcionario.setValorHora(new BigDecimal(8));
		return funcionario;
	}
	
	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
 
		final Lancamento lancamento = new Lancamento();
		lancamento.setFuncionario(funcionario);
		lancamento.setData(new Date());
		lancamento.setDescricao("Descrição do lançamento");
		lancamento.setLocalizacao("Localização do lançamento 1..033.2.5635");
		lancamento.setTipo(TipoEnum.INICIO_TRABALHO);

		
		return lancamento;
	}
	
}
