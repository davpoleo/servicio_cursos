package academiadigital.servicio_cursos.service;
//Esta es la capa que tiene toda la logica de negocio
//Las clases de Servicio actuan como el traductor entre el controlador (API) y el repositorio (habla directamente con la BBDD)

import academiadigital.servicio_cursos.dto.CursoRequestDto;
import academiadigital.servicio_cursos.dto.CursoResponseDto;
import academiadigital.servicio_cursos.dto.InscripcionResponseDto;
import academiadigital.servicio_cursos.model.Curso;
import org.springframework.stereotype.Service;

@Service
public interface CursoService {

    //CREAR UN CURSO
    CursoResponseDto crearCurso(CursoRequestDto request);

    //OBTENER UN CURSO POR ID
    Curso obtenerCursoPorId(Long id);

    //OBTENER TODOS LOS CURSOS
}