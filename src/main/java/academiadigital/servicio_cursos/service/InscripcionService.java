package academiadigital.servicio_cursos.service;

import academiadigital.servicio_cursos.dto.InscripcionRequestDto;
import academiadigital.servicio_cursos.dto.InscripcionResponseDto;

import java.time.LocalDateTime;

public interface InscripcionService {
    //REALIZAR UNA INSCRIPCION
    InscripcionResponseDto realizarInscripcion(InscripcionRequestDto request);

    //OBTENER ESTUDIANTES INSCRITOS EN UN CURSO
}
