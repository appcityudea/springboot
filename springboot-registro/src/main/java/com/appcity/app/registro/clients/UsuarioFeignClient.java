package com.appcity.app.registro.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.appcity.app.registro.models.Usuario;

@FeignClient(name = "app-usuarios")
public interface UsuarioFeignClient {

	@GetMapping("/users/username/search/exist-user")
	public Optional<Boolean> existsByUsername(@RequestParam String username);

	@GetMapping("/users/email/search/exist-email")
	public Boolean existsByEmail(@RequestParam String email);

	@GetMapping("/users/phone/search/exist-phone")
	public Boolean existsByPhone(@RequestParam String phone);
	
	@PostMapping("/users/crear")
	public Usuario crearUsuario(@RequestBody Usuario usuario);
	
	@GetMapping("/users/search/buscar")
	public Usuario findByUsernameOrEmailOrPhoneOrToken(@RequestParam String username);
}

