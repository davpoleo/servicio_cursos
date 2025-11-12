package academiadigital.servicio_cursos.repository;
//Esta capa habla directamente con la base de datos

import academiadigital.servicio_cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //Bean que indica que esta es la capa de persistencia
public interface CursosRepository extends JpaRepository<Curso, Long>, JpaSpecificationExecutor<Curso> {
    //Optional<Curso> findByCurso(String curso);
}
