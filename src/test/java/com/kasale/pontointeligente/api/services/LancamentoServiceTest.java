package com.kasale.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.kasale.pontointeligente.api.entities.Lancamento;
import com.kasale.pontointeligente.api.repositories.LancamentoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	@MockBean
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@BeforeEach
	public void setUp() throws Exception{
		BDDMockito.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class))).willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.lancamentoRepository.getOne(Mockito.anyLong())).willReturn(new Lancamento());
		BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());			
	}

	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		PageRequest pageRequest = PageRequest.of(0, 10);
		Page<Lancamento> listaLancamento = this.lancamentoService.buscarPorFuncionarioId(1L, pageRequest);
		assertNotNull(listaLancamento);
	}
	
	@Test
	public void testBuscarLancamentoPorId() {
		Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(1L);
		assertTrue(lancamento.isPresent());		
	}
	
	@Test
	public void testPersitirLancamento() {
		Lancamento lancamento = this.lancamentoService.persitir(new Lancamento());
		assertNotNull(lancamento);
	}
}
