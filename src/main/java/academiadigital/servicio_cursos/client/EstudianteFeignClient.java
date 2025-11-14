package academiadigital.servicio_cursos.client;

import academiadigital.servicio_cursos.dto.EstudianteResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-estudiantes", url = "http://localhost:8081")
public interface EstudianteFeignClient {

    @GetMapping("/api/v1/estudiantes/{id}")
    EstudianteResponseDto obtenerEstudiantePorId(@PathVariable("id") Long id);

}
//Rest tempale
//Feing
//Web client