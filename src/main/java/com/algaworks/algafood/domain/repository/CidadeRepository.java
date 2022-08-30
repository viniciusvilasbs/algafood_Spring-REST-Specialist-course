package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{

//	List<Cidade> listar();
//	Cidade buscar(Long id);
//	Cidade salvar(Cidade cidade);
//	void remover(Long id);

}
