package academiadigital.servicio_cursos.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record InscripcionResponseDto (
        Long id,
        Long cursoId,
        Long estudianteId
){
}
