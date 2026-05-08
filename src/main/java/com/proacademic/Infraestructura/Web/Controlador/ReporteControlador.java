package com.proacademic.Infraestructura.Web.Controlador;


import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import com.proacademic.Aplicacion.Servicios.GestionarMateriaServicio;
import com.proacademic.Aplicacion.Servicios.GestionarUsuarioServicio;
import com.proacademic.Aplicacion.Servicios.ReporteServicio;

import com.proacademic.Dominio.Modelo.Materia;
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

import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
@CrossOrigin("*")
@Tag(name = "Reportes", description = "Generación de reportes en PDF")

public class ReporteControlador {

    private final ReporteServicio reporteServicio;

    private final GestionarUsuarioServicio gestionarUsuarioServicio;

    private final GestionarMateriaServicio gestionarMateriaServicio;

    public ReporteControlador(
            ReporteServicio reporteServicio,
            GestionarUsuarioServicio gestionarUsuarioServicio,
            GestionarMateriaServicio gestionarMateriaServicio
    ) {

        this.reporteServicio = reporteServicio;

        this.gestionarUsuarioServicio = gestionarUsuarioServicio;

        this.gestionarMateriaServicio = gestionarMateriaServicio;
    }

    @Operation(
            summary = "Generar reporte PDF de estudiantes",
            description = "Genera un PDF con la lista de todos los estudiantes"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "PDF generado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al generar el PDF"
            )
    })

    @GetMapping("/estudiantes/pdf")

    public ResponseEntity<?> generarReporteEstudiantes(

            @Parameter(
                    description = "Nombre del curso para el reporte"
            )

            @RequestParam(defaultValue = "General")
            String curso
    ) {

        try {

            List<Usuario> estudiantes = gestionarUsuarioServicio
                    .listarTodos()
                    .stream()
                    .filter(u -> "ESTUDIANTE".equals(u.getRol()))
                    .collect(Collectors.toList());

            byte[] pdf = reporteServicio
                    .generarReporteEstudiantesPorCurso(
                            curso,
                            estudiantes
                    );

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_PDF);

            headers.setContentDispositionFormData(
                    "attachment",
                    "reporte_estudiantes_" + curso + ".pdf"
            );

            return new ResponseEntity<>(
                    pdf,
                    headers,
                    HttpStatus.OK
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


    @Operation(
            summary = "Generar reporte PDF de materias",
            description = "Genera un PDF con la lista de materias"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "PDF generado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al generar el PDF"
            )
    })

    @GetMapping("/materias/pdf")
    public ResponseEntity<?> generarReporteMaterias() {

        try {

            List<Materia> materias =
                    gestionarMateriaServicio.listarTodas();

            ByteArrayOutputStream salida =
                    new ByteArrayOutputStream();

            PdfWriter writer =
                    new PdfWriter(salida);

            PdfDocument pdf =
                    new PdfDocument(writer);

            Document documento =
                    new Document(pdf);

            documento.add(
                    new Paragraph("Listado de Materias")
            );

            documento.add(
                    new Paragraph(" ")
            );

            Table tabla = new Table(3);

            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Descripción");

            for (Materia materia : materias) {

                tabla.addCell(
                        String.valueOf(materia.getId())
                );

                tabla.addCell(
                        materia.getNombre()
                );

                tabla.addCell(
                        materia.getDescripcion()
                );
            }

            documento.add(tabla);

            documento.close();

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_PDF
            );

            headers.setContentDispositionFormData(
                    "attachment",
                    "reporte_materias.pdf"
            );

            return new ResponseEntity<>(
                    salida.toByteArray(),
                    headers,
                    HttpStatus.OK
            );

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
