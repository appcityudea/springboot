package com.appcity.app.registro.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appcity.app.registro.clients.UsuarioFeignClient;
import com.appcity.app.registro.models.Role;
import com.appcity.app.registro.models.Usuario;
import com.appcity.app.registro.payload.request.SignupRequest;
import com.appcity.app.registro.payload.response.MessageResponse;

@CrossOrigin
@RestController
public class RegistroController {

	@Autowired
	private UsuarioFeignClient client;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/registro/crear")
	public ResponseEntity<?> saveUsuario(@RequestBody SignupRequest signUpRequest) {

		if (signUpRequest.getUsername() != "" && signUpRequest.getEmail() != "" && signUpRequest.getPhone() != ""
				&& signUpRequest.getPassword() != "") {
			try {
				client.findByUsernameOrEmailOrPhoneOrToken(signUpRequest.getUsername());
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));

			} catch (Exception e) {
				try {
					client.findByUsernameOrEmailOrPhoneOrToken(signUpRequest.getEmail());
					return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
				} catch (Exception e1) {
					try {
						client.findByUsernameOrEmailOrPhoneOrToken(signUpRequest.getPhone());
						return ResponseEntity.badRequest().body(new MessageResponse("Error: Phone is already taken!"));
					} catch (Exception e2) {
						Usuario user = new Usuario(signUpRequest.getUsername(),
								encoder.encode(signUpRequest.getPassword()), true, signUpRequest.getPhone(), "", "",
								signUpRequest.getEmail(), "", "", 0, "", "");

						List<String> strRoles = signUpRequest.getRoles();
						List<Role> roles = new ArrayList<Role>();

						if (strRoles == null) {
							Role userRole = new Role("3", "ROLE_USER");
							// userRole.setName("ROLE_USER");
							// userRole.setId("3");
							roles.add(userRole);
						} else {
							strRoles.forEach(role -> {
								switch (role) {
								case "admin":
									Role userRole = new Role("1", "ROLE_ADMIN");
									// userRole.setName("ROLE_ADMIN");
									// userRole.setId("1");
									roles.add(userRole);
									break;
								case "mod":
									Role userRole1 = new Role("2", "ROLE_MODERATOR");
									// userRole1.setName("ROLE_MODERATOR");
									roles.add(userRole1);
									break;
								default:
									Role userRole11 = new Role("3", "ROLE_USER");
									// userRole11.setName("ROLE_USER");
									roles.add(userRole11);
								}
							});

						}

						user.setRoles(roles);

						client.crearUsuario(user);

						return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
					}
				}
			}
		} else {
			return ResponseEntity.ok(new MessageResponse("Ingrese todos los datos!"));
		}

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}