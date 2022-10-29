package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto);
	
	void remover(String nomeArquivo);
	
	FotoRecuperada recuperar(String nomeAquivo);
	
	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		
		if(nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}
	
	
	@Builder
	@Getter
	class NovaFoto{
		private String nomeArquivo;
		private String contentType;
		private InputStream inputStream;
	}
	
	@Builder
	@Getter
	class FotoRecuperada{
		
		private InputStream inputStream;
		private String url;
		
		public boolean temUrl() {
			return url != null;
		}
		
		public boolean temInputStream() {
			return inputStream != null;
		}
	}
}
