package com.kasale.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.kasale.pontointeligente.api.entities.Empresa;
import com.kasale.pontointeligente.api.entities.Funcionario;
import com.kasale.pontointeligente.api.enums.PerfilEnum;
import com.kasale.pontointeligente.api.util.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String EMAIL = "email.teste@mail.com";
	private static final String CPF = "57469778020";
	

	@BeforeEach
	public void setUp() throws Exception{
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}
	
	@AfterEach
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario  funcionario = this.funcionarioRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, funcionario.getEmail());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);
		assertEquals(CPF, funcionario.getCpf());		
	}
	
	@Test
	public void testeBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void testBuscarFuncionarioPorEmailEParaCpfInvalido() {
		
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("12345678944", EMAIL);
		
		assertNotNull(funcionario);
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
}
