package com.kasale.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.kasale.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {
	
	/**
	 * @param id
	 * @param pageRequest
	 * @return
	 */
	Page<Lancamento> buscarPorFuncionarioId(final Long id, final PageRequest pageRequest);
	
	/**
	 * @param id
	 * @return
	 */
	Optional<Lancamento> buscarPorId(final Long id);
	
	/**
	 * @param lancamento
	 * @return
	 */
	Lancamento persitir(final Lancamento lancamento);
	
	/**
	 * @param id
	 */
	void remover(final Long id);	
	
	
}
