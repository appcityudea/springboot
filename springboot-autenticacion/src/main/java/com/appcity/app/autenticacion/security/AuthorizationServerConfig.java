package com.appcity.app.autenticacion.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.PostMapping;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${jwt.clientId:appcity}")
	private String clientId;

	@Value("${jwt.client-secret:udea}")
	private String clientSecret;

	@Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
	private String[] authorizedGrantTypes;

	@Value("${jwt.accessTokenValidititySeconds:3600}") // 12 hours
	private int accessTokenValiditySeconds;

	@Value("${jwt.refreshTokenValiditySeconds:3600}") // 30 days
	private int refreshTokenValiditySeconds;

	@Autowired
	private Environment env;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	@PostMapping("/login/autorizacion/token")
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(env.getProperty("config.security.login.client.id"))
				.secret(passwordEncoder.encode(env.getProperty("config.security.login.client.secret")))
				.scopes("read", "write").authorizedGrantTypes(authorizedGrantTypes)
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accesTokenConverter()));

		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(accesTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		// TODO Auto-generated method stub
		return new JwtTokenStore(accesTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accesTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.login.jwt.key"));
		return tokenConverter;
	}

}
