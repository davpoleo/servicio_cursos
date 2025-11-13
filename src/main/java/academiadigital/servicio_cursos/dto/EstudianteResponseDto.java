package academiadigital.servicio_cursos.dto;

public record EstudianteResponseDto(
        Long id,
        String nombre,
        String apellido,
        String email
) {
}
