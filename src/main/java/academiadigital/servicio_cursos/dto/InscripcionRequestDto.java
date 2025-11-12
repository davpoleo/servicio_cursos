package academiadigital.servicio_cursos.dto;

import jakarta.validation.constraints.NotNull;

//Entrada para la inscripcion
public class InscripcionRequestDto {
    @NotNull(message = "El ID del curso es obligatorio")
    private Long cursoId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    //Getters y Setters
    public Long getCursoId() {
        return cursoId;
    }
    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
    public Long getEstudianteId() {
        return estudianteId;
    }
    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }
}
