package com.appcity.app.autenticacion.security.services;

import com.appcity.app.autenticacion.models.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

}
