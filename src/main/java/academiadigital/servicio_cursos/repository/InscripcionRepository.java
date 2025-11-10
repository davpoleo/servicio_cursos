package academiadigital.servicio_cursos.repository;

import academiadigital.servicio_cursos.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByCursoId(Long cursoId);
}
