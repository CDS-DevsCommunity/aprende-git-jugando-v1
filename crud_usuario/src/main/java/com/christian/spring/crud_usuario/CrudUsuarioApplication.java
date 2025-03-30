package com.christian.spring.crud_usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.christian.spring.crud_usuario.entidad.Usuario;
import com.christian.spring.crud_usuario.repositorio.EstudianteRepositorio;

@SpringBootApplication
public class CrudUsuarioApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudUsuarioApplication.class, args);
	}

	@Autowired
	private EstudianteRepositorio repositorio;
	@Override
	public void run(String... args) throws Exception {
		Usuario usuario1 = new Usuario(null, "Christian", "Maldonado","ejemplo1@gmail.com");
		repositorio.save(usuario1);

		Usuario usuario2 = new Usuario("Vivian", "Flores","ejemplo2@gmail.com");	
		repositorio.save(usuario2);
	}

}
