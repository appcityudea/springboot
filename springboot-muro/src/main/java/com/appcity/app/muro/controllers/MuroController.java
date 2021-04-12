package com.appcity.app.muro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appcity.app.muro.models.Muro;
import com.appcity.app.muro.repository.MuroRepository;

@RestController
public class MuroController {

	@Autowired
	MuroRepository muroRepository;
	
	@GetMapping("/muro/lista")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Muro> getMuro(){
		return muroRepository.findAll();
	}
	
	@GetMapping("/muro")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Muro findByNombreOrToken(@PathVariable String nombre, @PathVariable String token) {
		return muroRepository.findByNombreOrToken(nombre, token);
	}
	
	@PostMapping("/muro/crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Muro crearMuro(@RequestBody Muro muro) {
		return muroRepository.save(muro);
	}
	
	@DeleteMapping("/muro/eliminar/{nombre}")
	@ResponseStatus(code = HttpStatus.OK)
	public void eliminarMuro(@PathVariable String nombre) {
		Muro muro = muroRepository.findByNombreOrToken(nombre, nombre);
		String id = muro.getId();
		muroRepository.deleteById(id);
	}
	
	
}
