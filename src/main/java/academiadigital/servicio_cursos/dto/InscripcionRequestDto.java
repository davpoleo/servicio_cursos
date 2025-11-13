package academiadigital.servicio_cursos.dto;


import jakarta.validation.constraints.NotNull;

//Entrada para la inscripcion
public record InscripcionRequestDto(

        @NotNull(message = "El ID del curso es obligatorio")
        Long cursoId,

        @NotNull(message = "El ID del estudiante es obligatorio")
        Long estudianteId
) {

}
