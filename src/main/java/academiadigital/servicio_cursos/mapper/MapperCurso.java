package academiadigital.servicio_cursos.mapper;

import academiadigital.servicio_cursos.dto.CursoResponseDto;
import academiadigital.servicio_cursos.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class MapperCurso {
    public CursoResponseDto MapearACursoDto(Curso curso){
        return new CursoResponseDto(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescripcion()
        );
    }

}
