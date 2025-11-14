package academiadigital.servicio_cursos.mapper;

import academiadigital.servicio_cursos.dto.InscripcionResponseDto;
import academiadigital.servicio_cursos.model.Inscripcion;
import org.springframework.stereotype.Component;

@Component
public class MapperInscripcion {
    public InscripcionResponseDto MapearInscripcionADto(Inscripcion inscripcion){
        return new InscripcionResponseDto(
                inscripcion.getId(),
                inscripcion.getCursoId(),
                inscripcion.getEstudianteId()
        );
    }
}
