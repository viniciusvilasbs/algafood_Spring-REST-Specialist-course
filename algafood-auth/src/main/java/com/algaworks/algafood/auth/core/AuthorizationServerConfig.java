package com.algaworks.algafood.auth.core;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;    
	
//	@Autowired
//	private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	private DataSource datasource;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.jdbc(datasource);
//			.inMemory()
//				.withClient("algafood-web")
//				.secret(passwordEncoder.encode("web123"))
//				.authorizedGrantTypes("password", "refresh_token")
//				.scopes("WRITE", "READ")
//				.accessTokenValiditySeconds(60 * 60 * 6) //6 horas (o padrão é 12 horas)
//				.refreshTokenValiditySeconds(60 * 24 * 60 * 60) // 60 dias
//			.and()
//				.withClient("foodanalytics")
//				.secret(passwordEncoder.encode("")) //("food123"))
//				.authorizedGrantTypes("authorization_code")
//				.scopes("WRITE", "READ")
//				.redirectUris("http://www.foodanalytics.local:8082") //("http://aplicacao-cliente")
//			.and()
//				.withClient("webadmin")
//				.authorizedGrantTypes("implicit")
//				.scopes("WRITE", "READ")
//				.redirectUris("http://aplicacao-cliente")
//			.and()
//				.withClient("faturamento")
//				.secret(passwordEncoder.encode("faturamento123"))
//				.authorizedGrantTypes("client_credentials")
//				.scopes("WRITE", "READ")
//			.and()
//				.withClient("checktoken")
//				.secret(passwordEncoder.encode("check123"));
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
		security.checkTokenAccess("permitAll()")
			.tokenKeyAccess("permitAll()")
			.allowFormAuthenticationForClients();
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		var enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(
				Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));
		
		endpoints
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.reuseRefreshTokens(false)
//			.tokenStore(redisTokenStore())
			.accessTokenConverter(jwtAccessTokenConverter())
			.tokenEnhancer(enhancerChain)
			.approvalStore(approvalStore(endpoints.getTokenStore()))
			.tokenGranter(tokenGranter(endpoints));
	}
	
	private ApprovalStore approvalStore(TokenStore tokenStore) {
		
		var approvalStore = new TokenApprovalStore();
		approvalStore.setTokenStore(tokenStore);
		
		return approvalStore;
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
//		jwtAccessTokenConverter.setSigningKey("48c4a46dz8sc4d64s8d4as64c6a4s841asaf8sd4v8dfda56ase7as");
		
//		var jksResource = new ClassPathResource("keystores/algafood.jks");		
//		var keyStorePass = "123456";
//		var keyPairAlias = "algafood";
		
		var jksResource = new ClassPathResource(jwtKeyStoreProperties.getPath());
	    var keyStorePass = jwtKeyStoreProperties.getPassword();
	    var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

	    
	    var keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass.toCharArray());
	    var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);
	    
	    jwtAccessTokenConverter.setKeyPair(keyPair);
		
		return jwtAccessTokenConverter;
	}
	
//	private TokenStore redisTokenStore() {
//		return new RedisTokenStore(redisConnectionFactory);
//	}
	
	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
		var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
				endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
				endpoints.getOAuth2RequestFactory());
		
		var granters = Arrays.asList(
				pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());
		
		return new CompositeTokenGranter(granters);
	}
}
