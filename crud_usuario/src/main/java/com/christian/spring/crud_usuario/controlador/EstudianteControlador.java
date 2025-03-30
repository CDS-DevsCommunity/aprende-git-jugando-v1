package com.christian.spring.crud_usuario.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.christian.spring.crud_usuario.servicio.EstudianteServicio;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstudianteControlador {
    @Autowired
    private EstudianteServicio servicio;

    @GetMapping({ "/usuarios", "/" })
    public String listaUsuarios(Model modelo) {
        modelo.addAttribute("usuarios", servicio.listarTodosLosUsuarios());
        return "usuarios";
    }

}
