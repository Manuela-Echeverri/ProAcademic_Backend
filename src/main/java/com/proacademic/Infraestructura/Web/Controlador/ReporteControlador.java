package com.proacademic.Infraestructura.Web.Controlador;

import com.proacademic.Aplicacion.Servicios.GestionarUsuarioServicio;
import com.proacademic.Aplicacion.Servicios.ReporteServicio;
import com.proacademic.Dominio.Modelo.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
@CrossOrigin("*")
@Tag(name = "Reportes", description = "Generación de reportes en PDF")

public class ReporteControlador {

    private final ReporteServicio reporteServicio;
    private final GestionarUsuarioServicio gestionarUsuarioServicio;

    public ReporteControlador(ReporteServicio reporteServicio,
                              GestionarUsuarioServicio gestionarUsuarioServicio) {
        this.reporteServicio = reporteServicio;
        this.gestionarUsuarioServicio = gestionarUsuarioServicio;
    }

    @Operation(summary = "Generar reporte PDF de estudiantes",
            description = "Genera un PDF con la lista de todos los estudiantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "PDF generado exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error al generar el PDF")
    })
    @GetMapping("/estudiantes/pdf")
    public ResponseEntity<?> generarReporteEstudiantes(
            @Parameter(description = "Nombre del curso para el reporte")
            @RequestParam(defaultValue = "General") String curso) {
        try {
            // Filtrar solo estudiantes
            List<Usuario> estudiantes = gestionarUsuarioServicio.listarTodos()
                    .stream()
                    .filter(u -> "ESTUDIANTE".equals(u.getRol()))
                    .collect(Collectors.toList());

            byte[] pdf = reporteServicio
                    .generarReporteEstudiantesPorCurso(curso, estudiantes);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment",
                    "reporte_estudiantes_" + curso + ".pdf");

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
