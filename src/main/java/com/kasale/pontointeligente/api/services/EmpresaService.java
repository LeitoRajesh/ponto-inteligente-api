package com.kasale.pontointeligente.api.services;

import java.util.Optional;

import com.kasale.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	Optional<Empresa> buscarPorCnpj(final String cnpj);
	
	Empresa persistir(final Empresa empresa);
}
