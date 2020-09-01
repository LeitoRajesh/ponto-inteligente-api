
package com.kasale.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.kasale.pontointeligente.api.entities.Funcionario;

@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	
	Funcionario findByCpf(final String cpf);
	
	Funcionario findOne(Long id);	
	
	Funcionario findByEmail(final String email);
	
	Funcionario findByCpfOrEmail(final String cpf, final String email);
}
