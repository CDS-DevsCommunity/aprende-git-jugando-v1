package controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import servicio.EstudianteServicio;


@Controller
public class EstudianteControlador {
    @Autowired
    private EstudianteServicio servicio;

    @GetMapping({"/usuarios","/"})
    public String listaUsuarios(Model modelo) {
        modelo.addAttribute("usuarios", servicio.listarTodosLosUsuarios());
        return "usuarios";
    }
    
}
