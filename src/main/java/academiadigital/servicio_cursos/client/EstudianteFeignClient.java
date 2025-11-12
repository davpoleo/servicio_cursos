package academiadigital.servicio_cursos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-estudiantes", url = "http://localhost:8081")
public class EstudianteFeignClient {

   // @GetMapping("/api/estudiantes/{id}")
    //EstudianteResponseDto obtenerEstudiantePorId(@PathVariable("id") Long id);

}
