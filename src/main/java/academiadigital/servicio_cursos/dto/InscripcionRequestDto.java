package academiadigital.servicio_cursos.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Entrada para la inscripcion
@Schema(description = "Informacion requerida para crear o actualizar un curso")
public record InscripcionRequestDto(

        @Schema(description = "Id del curso",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "El ID del curso es obligatorio y tiene que ser mayor o igual a 1")
        Long cursoId,

        @Schema(description = "Id del estudiante",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "El ID del estudiante es obligatorio y tiene que ser mayor o igual a 1")
        Long estudianteId
) {

}
