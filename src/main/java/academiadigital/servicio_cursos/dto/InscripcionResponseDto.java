package academiadigital.servicio_cursos.dto;

public record InscripcionResponseDto (
        Long id,
        Long cursoId,
        Long estudianteId
){
}
