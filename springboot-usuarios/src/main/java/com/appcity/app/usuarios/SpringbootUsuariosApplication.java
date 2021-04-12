package com.appcity.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootUsuariosApplication.class, args);
	}

}
