package academiadigital.servicio_cursos.client;

import academiadigital.servicio_cursos.dto.EstudianteResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static academiadigital.servicio_cursos.util.ApiConstants.*;


@FeignClient(
        name = FEING_V1_ESTUDIANTES_NAME,
        url = FEING_V1_ESTUDIANTES_URL,
        path = FEING_V1_ESTUDIANTES_PATH
)
public interface EstudianteFeignClient {

    @GetMapping(API_V1_GET_STUDENT_BY_ID)
    EstudianteResponseDto obtenerEstudiantePorId(@Valid @PathVariable("id") Long id);
    //ResponseEntity<EstudianteResponseDto> obtenerEstudiantePorId(@PathVariable("id") Long id);
}
//Rest tempale
//Feing
//Web client