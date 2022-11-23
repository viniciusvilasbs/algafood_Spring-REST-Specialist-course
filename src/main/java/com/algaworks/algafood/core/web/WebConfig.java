package com.algaworks.algafood.core.web;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

//	@Autowired
//	private ApiDeprecationHandler apiDeprecationHandler;
	
	@Autowired
	private ApiRetirementHandler apiRetirementHandler;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
 		registry.addMapping("/**")
 				.allowedMethods("*");
 		
// 			.allowedOrigins("*") ou especifique("http://localhost:8080");
// 			.maxAge(30); especifica o tempo em cache da requisição
// 			.allowedMethods("*") ou especifique("GET", "HEAD", "POST"...);
	}
	
//	Este método é para inserir o header informando que a API está depreciada
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(apiDeprecationHandler);
//	}
	
//	Este método é para EXCLUIR a versão que foi depreciada anteriorment, resulta no código 410
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiRetirementHandler);
	}
	
//	Usar este método para usar versionamento MediaType
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.defaultContentType(AlgaMediaTypes.V2_APPLICATION_JSON);
//	}
	
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
}
