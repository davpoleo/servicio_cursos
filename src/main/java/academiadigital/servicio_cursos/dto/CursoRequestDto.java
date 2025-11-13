package academiadigital.servicio_cursos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoRequestDto(
        @NotNull(message = "El ID es obligatorio")
        Long id,

        @NotNull(message = "El titulo del curso es obligatorio")
        @Size(min = 5, message = "El titulo debe tener al menos 5 caracteres")
        String titulo,

        String descripcion
) {

}
