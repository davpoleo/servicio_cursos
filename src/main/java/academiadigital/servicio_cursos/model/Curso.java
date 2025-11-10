package academiadigital.servicio_cursos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity //Indica que la class cursos es una tabla e la BBDD
@Data
@Table(name = "cursos") // nombre de la tabla con el uso de jakarta
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Configura el auto increment
    Long id;
    String titulo;
    String descripcion;

    //Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    //Setters
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
