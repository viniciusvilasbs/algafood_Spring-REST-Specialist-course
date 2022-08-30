package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema."),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido."),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incrompreensível."),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado."),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade está em uso!"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio!");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
