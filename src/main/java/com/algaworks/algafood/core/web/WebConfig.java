package com.algaworks.algafood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
 		registry.addMapping("/**")
 				.allowedMethods("*");
 		
// 			.allowedOrigins("*") ou especifique("http://localhost:8080");
// 			.maxAge(30); especifica o tempo em cache da requisição
// 			.allowedMethods("*") ou especifique("GET", "HEAD", "POST"...);
	}
}
