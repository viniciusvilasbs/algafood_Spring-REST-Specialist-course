package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

//	List<Permissao> listar();
//	Permissao buscar(Long id);
//	Permissao salvar(Permissao permissao);
//	void remover(Permissao permissao);
	
}
