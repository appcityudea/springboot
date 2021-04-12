package com.appcity.app.usuarios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appcity.app.usuarios.models.Role;
import com.appcity.app.usuarios.models.User;
import com.appcity.app.usuarios.repository.RoleRepository;
import com.appcity.app.usuarios.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/roles/lista")
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	@GetMapping("/users/listar")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users")
	public Optional<User> findByUsernameOrEmailOrPhone(@PathVariable String username, @PathVariable String email, @PathVariable String phone){
		return userRepository.findByUsernameOrEmailOrPhone(username, email, phone);
	}
	
	@GetMapping("/users/buscar")
	public User findByUsernameOrEmailOrPhoneOrToken(@PathVariable String username, @PathVariable String email, @PathVariable String phone, @PathVariable String token){
		return userRepository.findByUsernameOrEmailOrPhoneOrToken(username, email, phone, token);
	}
	
	@GetMapping("/users/username")
	public Optional<Boolean> existsByUsername(@PathVariable String username) {
		return userRepository.existsByUsername(username);
	}
	
	@GetMapping("/users/email")
	public Boolean existsByEmail(@PathVariable String email) {
		return userRepository.existsByEmail(email);
	}
	
	@GetMapping("/users/phone")
	public Boolean existsByPhone(@PathVariable String phone) {
		return userRepository.existsByPhone(phone);
	}
	
	@DeleteMapping("/users/eliminar/{user}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminarId(@PathVariable String user) {
		User p = findByUsernameOrEmailOrPhoneOrToken(user, user, user, user);
		String id = p.getId();
		userRepository.deleteById(id);
	}
	
	@GetMapping("/roles")
	public Optional<Role> findByName(@PathVariable String name){
		return roleRepository.findByName(name);
	}
	
	@PostMapping("/users/crear")
	public User crearUsuario(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/editar/{user}")
	@ResponseStatus(HttpStatus.CREATED)
	public User editar(@RequestBody User usuario, @PathVariable String user) {
		User usuarioDb = userRepository.findByUsernameOrEmailOrPhoneOrToken(user, user, user, user);
		if (usuario.getNombre() != null)
			usuarioDb.setNombre(usuario.getNombre());
		if (usuario.getApellido() != null)
			usuarioDb.setApellido(usuario.getApellido());
		if (usuario.getIntereses() != null)
			usuarioDb.setIntereses(usuario.getIntereses());
		if (usuario.getUbicacion() != null)
			usuarioDb.setUbicacion(usuario.getUbicacion());
		if (usuario.getEmail() != null)
			usuarioDb.setEmail(usuario.getEmail());
		if (usuario.getUsername() != null)
			usuarioDb.setUsername(usuario.getUsername());
		return userRepository.save(usuarioDb);
	}
	
}
