package com.christian.spring.crud_usuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.christian.spring.crud_usuario.entidad.Usuario;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Usuario,Integer>{
    
}
