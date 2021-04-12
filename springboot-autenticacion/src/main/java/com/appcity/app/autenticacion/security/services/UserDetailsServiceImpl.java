package com.appcity.app.autenticacion.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appcity.app.autenticacion.clients.UsersFeignClients;
import com.appcity.app.autenticacion.models.Role;
import com.appcity.app.autenticacion.models.Usuario;

@Service
public class UserDetailsServiceImpl implements IUsuarioService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UsersFeignClients client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		/*
		 * Usuario usuario = client.findByUsernameOrEmailOrPhone(username)
		 * .orElseThrow(() -> new
		 * UsernameNotFoundException("User Not Found with username: " + username));
		 */
		
		Usuario usuario = client.findByUsernameOrEmailOrPhoneOrToken(username);
		
		if (usuario == null) {
			log.error("Error en el login, no existe el usuario '" + username + "'en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario '" + username + "'en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		 
		log.info("Usuario autenticado: " + username);

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	public Usuario findByUsername(String username) {
		return client.findByUsernameOrEmailOrPhoneOrToken(username);
	}

}
