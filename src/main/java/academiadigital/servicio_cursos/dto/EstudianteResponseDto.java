package academiadigital.servicio_cursos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstudianteResponseDto(
        Long id,

        @Schema(description = "Nombre del estudiante. No puede estar vacio.",
                example = "David",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "El nombre es obligatorio.")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 Caracteres")
        String nombre,

        @Schema(description = "Apellido del estudiante. No puede estar vacio.",
                example = "Poleo",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "El apellido es obligatorio.")
        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 Caracteres")
        String apellido,

        @Schema(description = "Correo electronico del estudiante. No puede estar vacio.",
                example = "David@email.com",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "El email es obligatorio.")
        @Email(message = "El formato del email es invalido.")
        @Size(min = 7, max = 100, message = "El Correo debe tener entre 7 y 75 Caracteres")
        String email
) {
}
