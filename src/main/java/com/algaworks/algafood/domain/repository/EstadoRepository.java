package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

//	List<Estado> listar();
//	Estado buscar(Long id);
//	Estado salvar(Estado estado);
//	void remover(Long id);
	
}
