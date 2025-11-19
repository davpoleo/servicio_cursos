package academiadigital.servicio_cursos.controller;

import academiadigital.servicio_cursos.dto.*;
import academiadigital.servicio_cursos.model.Curso;
import academiadigital.servicio_cursos.service.CursoService;
import academiadigital.servicio_cursos.service.InscripcionService;
import academiadigital.servicio_cursos.util.ApiConstants;
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
@RequestMapping(ApiConstants.API_V1_BASE)
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
    @PostMapping(ApiConstants.API_V1_CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CursoResponseDto> crearCurso(@Valid @RequestBody CursoRequestDto request){
        CursoResponseDto cursoCreado = cursoService.crearCurso(request);
        return new ResponseEntity<>(cursoCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar curso por ID.",
            description = "Busca un curso por su ID")
    @ApiResponse(responseCode = "200", description = "Curso encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EstudianteResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "EL curso no esta registrado en el Sistema",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)))
    @GetMapping(ApiConstants.API_V1_GET_STUDENT_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.obtenerCursoPorId(id));
    }

    // -------- ENDPOINT DE INSCRIPCIONES -------- //
    @Operation(summary = "Inscribir un Estudiante a un curso",
            description = "Inscribe un estudiantea un curso existente")
    @ApiResponse(responseCode = "201", description = "Inscripcion exitosa",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EstudianteResponseDto.class)))
    @ApiResponse(responseCode = "409", description = "Error en las inscripcion",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDto.class)))
    @PostMapping(ApiConstants.API_V1_ENROLLMENT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InscripcionResponseDto> realizarInscripcion(@Valid @RequestBody InscripcionRequestDto request
            ){
        InscripcionResponseDto inscripcionRealizada = inscripcionService.realizarInscripcion(request);
        return new ResponseEntity<>(inscripcionRealizada, HttpStatus.CREATED);
    }

}
