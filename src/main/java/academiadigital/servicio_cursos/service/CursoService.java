package academiadigital.servicio_cursos.service;
//Esta es la capa que tiene toda la logica de negocio
//Las clases de Servicio actuan como el traductor entre el controlador (API) y el repositorio (habla directamente con la BBDD)


import academiadigital.servicio_cursos.dto.CursoDto;
import academiadigital.servicio_cursos.dto.InscripcionDto;
import academiadigital.servicio_cursos.dto.InscripcionRequestDto;
import academiadigital.servicio_cursos.handler.RecursoNoEncontradoException;
import academiadigital.servicio_cursos.model.Curso;
import academiadigital.servicio_cursos.model.Inscripcion;
import academiadigital.servicio_cursos.repository.CursosRepository;
import academiadigital.servicio_cursos.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CursoService {
    //Inyeccion de dependencia del repository (Conexion a la BBDD)
    private final CursosRepository cursosRepository;
    private final InscripcionRepository inscripcionRepository;

    //Constuctor de la inyeccion de dependencias.
    public CursoService(CursosRepository cursosRepository, InscripcionRepository inscripcionRepository){
        this.cursosRepository = cursosRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    //------------- METODOS---------------//

    //Conversion de Model a DTO
    private CursoDto mapearCursoADto(Curso curso){
        CursoDto dto = new CursoDto();
        dto.setId(curso.getId());
        dto.setTitulo(curso.getTitulo());
        dto.setDescripcion(curso.getDescripcion());
        return dto;
    }

    private InscripcionDto mapearInscripcionADto(Inscripcion inscripcion){
        InscripcionDto dto = new InscripcionDto();
        dto.setId(inscripcion.getId());
        dto.setCursoId(inscripcion.getCursoId());
        dto.setEstudianteId(inscripcion.getEstudianteId());
        dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        return dto;
    }

    //Logica de cursos (CRUD BASICO)
    public CursoDto crearCurso(Curso curso){
        Curso nuevoCurso = cursosRepository.save(curso);
        return mapearCursoADto(nuevoCurso);
    }

    //Metodo para obtener un curso por ID
    public CursoDto obtenerCursoPorId(Long id){
        Curso curso = cursosRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("Curso no encontrado con ID: " + id));
        return mapearCursoADto(curso);
    }

    // -- Logica de Inscripciones
    public InscripcionDto crearinscripcion(InscripcionRequestDto requestDto){
        //Verficacion de la existencia del curso
        if(!cursosRepository.existsById(requestDto.getCursoId())){
            throw new RecursoNoEncontradoException("No se puede inscribir: Curso no encontrado con ID: " + requestDto.getCursoId());
        }
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCursoId(requestDto.getCursoId());
        inscripcion.setEstudianteId(requestDto.getEstudianteId());
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        Inscripcion nuevaInscripcion = inscripcionRepository.save(inscripcion);
        return mapearInscripcionADto(nuevaInscripcion);
    }

    //Obtener Ids de estudiantes por curso (Endpoint Clave para el reporte)
    public List<Long> obtenerEstudiantePorCurso(Long cursoId){
        //Se verifica si el curso existe
        if(!cursosRepository.existsById(cursoId)){
            throw new RecursoNoEncontradoException("No se puede Obtener la Lista: Curso no encontrado con ID: " + cursoId);
        }
        List<Inscripcion> inscripciones = inscripcionRepository.findByCursoId(cursoId);
        //Uso de Streams para mapear la lista de objetos Inscripcion a una Lista de Long (Solos los Ids)
        return inscripciones.stream()
                .map(Inscripcion::getEstudianteId)
                .collect(Collectors.toList());
    }
}
