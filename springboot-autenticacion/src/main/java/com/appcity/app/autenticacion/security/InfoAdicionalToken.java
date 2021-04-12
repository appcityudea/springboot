package com.appcity.app.autenticacion.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.appcity.app.autenticacion.models.Usuario;
import com.appcity.app.autenticacion.security.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService UsuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();

		Usuario usuario = UsuarioService.findByUsername(authentication.getName());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("celular", usuario.getPhone());
		info.put("email", usuario.getEmail());
		info.put("ubicacion", usuario.getUbicacion());
		info.put("intereses", usuario.getIntereses());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
