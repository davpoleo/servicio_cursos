package academiadigital.servicio_cursos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearCursoDto {
    @NotBlank(message = "El titulo es obligatorio.")
    @Size(min = 2, message = "El titulo debe tener entrer al menos 5 caracteres")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatoria.")
    private String descripcion;

    //Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
