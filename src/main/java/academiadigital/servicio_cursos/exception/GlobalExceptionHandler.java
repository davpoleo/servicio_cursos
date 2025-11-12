package academiadigital.servicio_cursos.exception;

//Esta clase escuchara las excepciones de cualquier @RestController

import academiadigital.servicio_cursos.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
//Esto le indica a Spring que esta clase debe escuchar exepciones de cualquier @RestController
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDto> handleRecursoNoEncontradoException(
            RecursoNoEncontradoException exception,
            WebRequest webRequest
    ) {
        //DTO de respuesta 404
        ErrorResponseDto errorDto = new ErrorResponseDto(
                exception.getMessage(),
                webRequest.getDescription(false), //Obtiene la ruta (ej: uri=/api/v1/estudiantes/2
                HttpStatus.NOT_FOUND.value() //404
        );
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    //Maneja los errore de validacion de DTOs da como resultado un HTTP 400 Bad Request
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest webRequest
    ) {
        //Se crea un mapa para recolectar todos los errores de validacion
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        //Convertimos el mapa de errores en un String para el mensaje principal
        String mensajeErrores = "Errores de validacion: " + errors.toString();

        //Creamos el DTO de respuesta 400
        ErrorResponseDto errorDto = new ErrorResponseDto(
                mensajeErrores,
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST.value() //400
        );

        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
