package academiadigital.servicio_cursos.service.impl;

import academiadigital.servicio_cursos.client.EstudianteFeignClient;
import academiadigital.servicio_cursos.dto.EstudianteResponseDto;
import academiadigital.servicio_cursos.dto.InscripcionRequestDto;
import academiadigital.servicio_cursos.dto.InscripcionResponseDto;
import academiadigital.servicio_cursos.exception.RecursoNoEncontradoException;
import academiadigital.servicio_cursos.mapper.MapperInscripcion;
import academiadigital.servicio_cursos.model.Inscripcion;
import academiadigital.servicio_cursos.repository.CursosRepository;
import academiadigital.servicio_cursos.repository.InscripcionRepository;
import academiadigital.servicio_cursos.service.InscripcionService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static academiadigital.servicio_cursos.util.ApiConstants.ERROR_VALIDACION_ID_CURSO_NO_ENCONTRADO;
import static academiadigital.servicio_cursos.util.ApiConstants.ERROR_VALIDACION_ID_ESTUDIANTE;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private static final Logger log = LoggerFactory.getLogger(InscripcionServiceImpl.class);
    @Autowired
    private EstudianteFeignClient estudianteFeignClient;
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private MapperInscripcion mapperInscripcion;
    @Autowired
    private CursosRepository cursosRepository;

    //Obtener informacion de estudiante especifico con el uso de Feing Client
    @Override
    public InscripcionResponseDto realizarInscripcion(InscripcionRequestDto request) {
        try{
            log.info("Verificando existencia de estudiante con ID: {}", request.estudianteId());
            EstudianteResponseDto EstudianteExiste = estudianteFeignClient.obtenerEstudiantePorId(request.estudianteId());
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setCursoId(request.cursoId());
            inscripcion.setEstudianteId(request.estudianteId());
            inscripcion.setFechaInscripcion(LocalDateTime.now());
            Inscripcion nuevaInscripcion = inscripcionRepository.save(inscripcion);
            log.info("Estudiante registrado en el curso con ID: {}", request.cursoId());
            return mapperInscripcion.MapearInscripcionADto(nuevaInscripcion);
        }catch (FeignException.NotFound error){
            log.warn("Estudiante con ID {} no encontrado al consultar servicio", request.estudianteId());
            throw new RecursoNoEncontradoException(ERROR_VALIDACION_ID_ESTUDIANTE);
        }
    }

    public List<Long> obtenerEstudiantePorCurso(Long cursoId){
        //Se verifica si el curso existe
        if(!cursosRepository.existsById(cursoId)){
            throw new RecursoNoEncontradoException(ERROR_VALIDACION_ID_CURSO_NO_ENCONTRADO);
        }
        List<Inscripcion> inscripciones = inscripcionRepository.findByCursoId(cursoId);
        //Uso de Streams para mapear la lista de objetos Inscripcion a una Lista de Long (Solos los Ids)
        return inscripciones.stream()
                .map(Inscripcion::getEstudianteId)
                .collect(Collectors.toList());
    }
}
