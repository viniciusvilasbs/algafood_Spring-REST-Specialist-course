package com.algaworks.algafood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algaworks.algafood.infrastructure.repository.CustomJpaRepositoryImpl;

//@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
//		comando alternativo para alterar a inicialização da API, inicializando o Base64ProtocolResolver
//		var app = new SpringApplication(AlgafoodApiApplication.class);
//		app.addListeners(new Base64ProtocolResolver());
//		app.run(args);
		
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}
}
