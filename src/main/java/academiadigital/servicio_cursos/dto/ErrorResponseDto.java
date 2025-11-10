package academiadigital.servicio_cursos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    private final LocalDateTime timestamp; //cuando ocurrio el error
    private final String mensaje; //mensaje amigable
    private final String detalles;  //Detalles de la peticion fallida
    private final int codigoEstado; //Codigo HTTP (404, 400, 500)

    //
    public ErrorResponseDto(String mensaje, String detalles, int codigoEstado){
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.codigoEstado = codigoEstado;
    }
}
