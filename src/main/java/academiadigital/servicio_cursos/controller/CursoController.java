package academiadigital.servicio_cursos.controller;

import academiadigital.servicio_cursos.dto.*;
import academiadigital.servicio_cursos.model.Curso;
import academiadigital.servicio_cursos.service.CursoService;
import academiadigital.servicio_cursos.service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/cursos")
@Tag(name = "Servicio de cursos", description = "Api para crear, leer, actualizar, eliminar cursos y crear inscripciones de estudiantes a un curso")
public class CursoController {
    private final CursoService cursoService;
    private final InscripcionService inscripcionService;

    public CursoController(CursoService cursoService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    @Operation(summary = "Crear un nuevo curso.",
            description = "Crea un nuevo curso asignando valores de titulo y descripcion.")
    @ApiResponse(responseCode = "201", description = "Curso creado satisfactoriamente.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EstudianteResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "Curso ya registrado en el sistema.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)))
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CursoResponseDto> crearCurso(@Valid @RequestBody CursoRequestDto request){
        CursoResponseDto cursoCreado = cursoService.crearCurso(request);
        return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.obtenerCursoPorId(id));
    }

    // -------- ENDPOINT DE INSCRIPCIONES -------- //
    @PostMapping("/inscripciones")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InscripcionResponseDto> realizarInscripcion(@Valid @RequestBody InscripcionRequestDto request
            ){
        InscripcionResponseDto inscripcionRealizada = inscripcionService.realizarInscripcion(request);
        return new ResponseEntity<>(inscripcionRealizada, HttpStatus.CREATED);
    }

}
