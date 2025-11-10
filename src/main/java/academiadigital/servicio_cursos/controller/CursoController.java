package academiadigital.servicio_cursos.controller;

import academiadigital.servicio_cursos.dto.CrearCursoDto;
import academiadigital.servicio_cursos.dto.CursoDto;
import academiadigital.servicio_cursos.dto.InscripcionDto;
import academiadigital.servicio_cursos.dto.InscripcionRequestDto;
import academiadigital.servicio_cursos.model.Curso;
import academiadigital.servicio_cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    @PostMapping("/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public CursoDto crearCurso(@RequestBody @Valid CrearCursoDto crearDto){
        //Mapeo de DTO a la Entidad antes de pasar al servicio
        Curso curso = new Curso();
        curso.setTitulo(crearDto.getTitulo());
        curso.setDescripcion(crearDto.getDescripcion());

        return cursoService.crearCurso(curso);
    }

    @GetMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CursoDto obtenerCursoPorId(@PathVariable Long id){
        return cursoService.obtenerCursoPorId(id);
    }

    // -------- ENDPOINT DE INSCRIPCIONES -------- //
    @PostMapping("/inscripciones")
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionDto inscribirEstudiante(@RequestBody @Valid InscripcionRequestDto requestDto){
        return cursoService.crearinscripcion(requestDto);
    }

    @GetMapping("/cursos/{cursoId}/estudiantes")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> obtenerEstudiantesInscritos(@PathVariable Long cursoId){
        return cursoService.obtenerEstudiantePorCurso(cursoId);
    }






}
