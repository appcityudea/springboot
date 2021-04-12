package com.appcity.app.autenticacion.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appcity.app.autenticacion.models.Role;
import com.appcity.app.autenticacion.models.Usuario;

@FeignClient(name = "app-usuarios")
public interface UsersFeignClients {

	@GetMapping("/users/search/buscar-user")
	public Optional<Usuario> findByUsernameOrEmailOrPhone(@RequestParam String username);

	@GetMapping("/users/search/buscar")
	public Usuario findByUsernameOrEmailOrPhoneOrToken(@RequestParam String username);

	@GetMapping("/users/username/search/exist-user")
	public Boolean existsByUsername(@RequestParam String username);

	@GetMapping("/users/email/search/exist-email")
	public Boolean existsByEmail(@RequestParam String email);

	@GetMapping("/users/phone/search/exist-phone")
	public Boolean existsByPhone(@RequestParam String phone);

	@GetMapping("/roles/lista")
	public List<Role> getRoles();
	
}
