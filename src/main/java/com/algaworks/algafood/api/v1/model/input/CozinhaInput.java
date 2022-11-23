package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

public class CozinhaInput {
	
	@NotBlank
	private String nome;
	
}
