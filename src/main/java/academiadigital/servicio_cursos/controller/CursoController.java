package academiadigital.servicio_cursos.controller;

import academiadigital.servicio_cursos.dto.*;
import academiadigital.servicio_cursos.model.Curso;
import academiadigital.servicio_cursos.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CursoResponseDto> crearCurso(@Valid @RequestBody CursoRequestDto request){
        CursoResponseDto cursoCreado = cursoService.crearCurso(request);
        return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
    }

    @GetMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.obtenerCursoPorId(id));
    }

    // -------- ENDPOINT DE INSCRIPCIONES -------- //
    /*@PostMapping("/inscripciones")
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionDto inscribirEstudiante(@RequestBody @Valid InscripcionRequestDto requestDto){
        return cursoService.crearinscripcion(requestDto);
    }

    @GetMapping("/cursos/{cursoId}/estudiantes")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> obtenerEstudiantesInscritos(@PathVariable Long cursoId){
        return cursoService.obtenerEstudiantePorCurso(cursoId);
    }
    */





}
