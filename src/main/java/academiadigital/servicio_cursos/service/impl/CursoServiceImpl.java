package academiadigital.servicio_cursos.service.impl;

import academiadigital.servicio_cursos.dto.CursoRequestDto;
import academiadigital.servicio_cursos.dto.CursoResponseDto;
import academiadigital.servicio_cursos.exception.RecursoDuplicadoException;
import academiadigital.servicio_cursos.exception.RecursoNoEncontradoException;
import academiadigital.servicio_cursos.mapper.MapperCurso;
import academiadigital.servicio_cursos.model.Curso;
import academiadigital.servicio_cursos.repository.CursosRepository;
import academiadigital.servicio_cursos.service.CursoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CursoServiceImpl implements CursoService {

    @Autowired
    private final CursosRepository cursosRepository;
    @Autowired
    private final MapperCurso mapperCurso;
    @Autowired
    private static final Logger log = LoggerFactory.getLogger(InscripcionServiceImpl.class);

    public CursoServiceImpl(CursosRepository cursosRepository, MapperCurso mapperCurso) {
        this.cursosRepository = cursosRepository;
        this.mapperCurso = mapperCurso;
    }

    @Override
    @Transactional
    public CursoResponseDto crearCurso(CursoRequestDto request) {
        cursosRepository.findById(request.id()).ifPresent(crs ->{
           throw new RecursoDuplicadoException("El curso '"+request.titulo()+"' ya existe.");
        });
        Curso curso = new Curso();
        curso.setTitulo(request.titulo());
        curso.setDescripcion(request.descripcion());
        Curso nuevoCurso = cursosRepository.save(curso);
        log.info("Creando un curso nuevo.");
        return mapperCurso.MapearACursoDto(nuevoCurso);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso obtenerCursoPorId(Long id) {
        log.info("Buscando curso por id especifico.");
        return cursosRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("El curso con el ID: '"+id+"' No existe."));
    }
}