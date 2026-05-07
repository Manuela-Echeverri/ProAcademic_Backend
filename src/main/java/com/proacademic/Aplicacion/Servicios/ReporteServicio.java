package com.proacademic.Aplicacion.Servicios;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.proacademic.Dominio.Modelo.Usuario;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReporteServicio {

    public byte[] generarReporteEstudiantesPorCurso(String nombreCurso,
                                                    List<Usuario> estudiantes) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // ── Título ─────────────────────────────────────────
            Paragraph titulo = new Paragraph("ProAcademic")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();
            document.add(titulo);

            Paragraph subtitulo = new Paragraph("Reporte de Estudiantes")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14);
            document.add(subtitulo);

            // ── Información del curso ──────────────────────────
            document.add(new Paragraph("\nCurso: " + nombreCurso)
                    .setFontSize(12).setBold());

            String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            document.add(new Paragraph("Fecha de generación: " + fecha)
                    .setFontSize(10));

            document.add(new Paragraph("Total de estudiantes: " + estudiantes.size())
                    .setFontSize(10));

            document.add(new Paragraph("\n"));

            // ── Tabla ──────────────────────────────────────────
            Table tabla = new Table(UnitValue.createPercentArray(
                    new float[]{1, 3, 3, 4}))
                    .setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            tabla.addHeaderCell(crearCeldaEncabezado("ID"));
            tabla.addHeaderCell(crearCeldaEncabezado("Nombre"));
            tabla.addHeaderCell(crearCeldaEncabezado("Apellido"));
            tabla.addHeaderCell(crearCeldaEncabezado("Correo"));

            // Filas
            for (Usuario e : estudiantes) {
                tabla.addCell(new Paragraph(String.valueOf(e.getId())));
                tabla.addCell(new Paragraph(e.getNombre()));
                tabla.addCell(new Paragraph(e.getApellido()));
                tabla.addCell(new Paragraph(e.getCorreo()));
            }

            document.add(tabla);
            document.close();

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF: " + e.getMessage());
        }
    }

    private Cell crearCeldaEncabezado(String texto) {
        return new Cell()
                .add(new Paragraph(texto).setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER);
    }

}
