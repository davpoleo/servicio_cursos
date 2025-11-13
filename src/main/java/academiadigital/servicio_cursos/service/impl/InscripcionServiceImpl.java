package academiadigital.servicio_cursos.service.impl;

import academiadigital.servicio_cursos.client.EstudianteFeignClient;
import academiadigital.servicio_cursos.dto.EstudianteResponseDto;
import academiadigital.servicio_cursos.dto.InscripcionRequestDto;
import academiadigital.servicio_cursos.dto.InscripcionResponseDto;
import academiadigital.servicio_cursos.mapper.MapperInscripcion;
import academiadigital.servicio_cursos.model.Inscripcion;
import academiadigital.servicio_cursos.repository.InscripcionRepository;
import academiadigital.servicio_cursos.service.InscripcionService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private EstudianteFeignClient estudianteFeignClient;
    private InscripcionRepository inscripcionRepository;
    private MapperInscripcion mapperInscripcion;

    //Obtener informacion de estudiante especifico con el uso de Feing Client
    @Override
    public InscripcionResponseDto realizarInscripcion(InscripcionRequestDto request) {
        try{
            EstudianteResponseDto EstudianteExiste = estudianteFeignClient.obtenerEstudiantePorId(request.estudianteId());
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setCursoId(request.cursoId());
            inscripcion.setEstudianteId(request.estudianteId());
            inscripcion.setFechaInscripcion(LocalDateTime.now());
            Inscripcion nuevaInscripcion = inscripcionRepository.save(inscripcion);
            return mapperInscripcion.MapearInscripcionADto(nuevaInscripcion);

        }catch (FeignException.NotFound error){
            throw new RuntimeException("Error - Estudiante con ID "+request.estudianteId()+" No existe");
        }
    }

}
