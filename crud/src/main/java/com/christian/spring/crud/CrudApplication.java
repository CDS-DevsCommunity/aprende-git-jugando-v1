package com.christian.spring.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import entidad.Usuario;
import repositorio.EstudianteRepositorio;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Autowired
	private EstudianteRepositorio repositorio;

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario1 = new Usuario(1, "Christian", "Garcia", "ejemplo@gmail.com");	
		repositorio.save(usuario1);

		Usuario usuario2 = new Usuario("Vivian", "Flores","ejemplo2@gmail.com");	
		repositorio.save(usuario2);
	}

}
