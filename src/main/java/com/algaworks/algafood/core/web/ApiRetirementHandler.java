package com.algaworks.algafood.core.web;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiRetirementHandler implements HandlerInterceptor {
	
//	antiga: ApiDeprecationHandler
//	
//		@Override
//		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//				throws Exception {
//			if (request.getRequestURI().startsWith("/v1/")) {
//				response.addHeader("X-AlgaFood-Deprecated",
//						"Essa versão da API está depreciada e deixará de existir a partir de 01/12/2022."
//								+ "Use a versão mais atual da API.");
//			}
//			return true;
//		}
	
	
	
//	Este método impede o v1 de ser acessado, se ficar ativo aparecerá a resposta 410 GONE !!!!!!!
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		if (request.getRequestURI().startsWith("/v1/")) {
//			response.setStatus(HttpStatus.GONE.value());
//			return false;
//		}
//		return true;
//	}
}
