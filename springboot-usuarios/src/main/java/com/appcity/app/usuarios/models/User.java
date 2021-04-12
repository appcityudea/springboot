package com.appcity.app.usuarios.models;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;

	@NotBlank(message = "Username cannot be null")
	@Size(max = 20)
	private String username;

	@NotBlank(message = "Password cannot be null")
	@Size(min = 6, max = 20, message 
		      = "About Me must be between 6 and 20 characters")
	private String password;

	private Boolean enabled;

	@NotBlank(message = "Phone cannot be null")
	@Size(max = 50)
	private String phone;

	private String nombre;
	private String apellido;

	@NotBlank(message = "Email cannot be null")
	@Size(max = 50)
	@Email(message = "Email should be valid")
	private String email;

	private String ubicacion;
	private String intereses;
	private Integer intentos;

	private String llave;

	private String token;

	//@DBRef
	private List<Role> roles;

	
	
	public User() {
		
	}

	public User(String username, String password,
			Boolean enabled, String phone, String nombre, String apellido,
			String email, String ubicacion, String intereses, Integer intentos,
			String llave, String token) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.phone = phone;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.ubicacion = ubicacion;
		this.intereses = intereses;
		this.intentos = intentos;
		this.llave = llave;
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getIntereses() {
		return intereses;
	}

	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public String getLlave() {
		return llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

