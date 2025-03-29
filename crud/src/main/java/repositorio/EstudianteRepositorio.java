package repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import entidad.Usuario;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Usuario, Integer> {
    
}
