package com.appcity.app.usuarios.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.appcity.app.usuarios.models.User;


public interface UserRepository extends MongoRepository<User, String> {
	
	@RestResource(path="buscar-user")
	public Optional<User> findByUsernameOrEmailOrPhone(@Param("username") String username, @Param("username") String email, @Param("username") String phone);
	
	@RestResource(path="buscar")
	public User findByUsernameOrEmailOrPhoneOrToken(@Param("username") String username, @Param("username") String email, @Param("username") String phone,  @Param("username") String token);

	@RestResource(path="exist-user")
	public Optional<Boolean> existsByUsername(@Param("username") String username);

	@RestResource(path="exist-email")
	public Boolean existsByEmail(@Param("email") String email);
  
	@RestResource(path="exist-phone")
	public Boolean existsByPhone(@Param("phone") String phone);

}