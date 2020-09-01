package com.kasale.pontointeligente.api.services;

import java.util.Optional;

import com.kasale.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {

	Funcionario persistir(final Funcionario funcionario);
	
	Optional<Funcionario> buscarPorCpf(final String cpf);
	
	Optional<Funcionario> buscarPorEmail(final String email);
	
	Optional<Funcionario> buscarPorId(final Long id);
	
	
}
