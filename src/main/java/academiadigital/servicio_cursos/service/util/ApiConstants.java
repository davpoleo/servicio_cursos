package academiadigital.servicio_cursos.service.util;

public class ApiConstants {
    private ApiConstants(){    }

    //Rutas del controlador
    public static final String API_V1_BASE = "/api/v1/cursos";
    public static final String API_V1_CREATE = "/crear";
    public static final String API_V1_GET_STUDENT_BY_ID = "/{id}";
    public static final String API_V1_ENROLLMENT = "/inscripciones";

    // RUTAS FEING CLIENT
    public static final String FEING_V1_ESTUDIANTES_NAME = "servicio-estudiantes";
    public static final String FEING_V1_ESTUDIANTES_URL = "http://localhost:8081";
    public static final String FEING_V1_ESTUDIANTES_PATH = "/api/v1/estudiantes";

    //MENSAJES DE ERROR DE VALIDACION


    //TRAZABILIDAD
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    public static final String TRACE_ID_MDC_KEY = "traceId";

    //MENSAJES DE ERROR DE NEGOCIO

}
