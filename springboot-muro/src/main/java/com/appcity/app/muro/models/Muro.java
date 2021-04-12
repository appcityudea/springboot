package com.appcity.app.muro.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "muro")
public class Muro {

	@Id
	private String id;

	@NotBlank(message = "Name cannot be null")
	@Size(max = 30)
	private String nombre;

	@NotBlank(message = "url cannot be null")
	private String url;

	@NotBlank(message = "enabled cannot be null")
	private Boolean enabled;

	@NotBlank(message = "descripcion cannot be null")
	private String descripcion;

	@NotBlank(message = "ubicacion cannot be null")
	private String ubicacion;

	@NotBlank(message = "token cannot be null")
	private String token;

	public Muro() {
	}
	
	

	public Muro(String id, String nombre,
			String url,
			Boolean enabled,
			String descripcion,
			String ubicacion,
			String token) {
		this.id = id;
		this.nombre = nombre;
		this.url = url;
		this.enabled = enabled;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.token = token;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	
}
