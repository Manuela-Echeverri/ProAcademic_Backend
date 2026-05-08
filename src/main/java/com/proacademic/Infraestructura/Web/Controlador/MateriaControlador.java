package com.proacademic.Infraestructura.Web.Controlador;

import com.proacademic.Aplicacion.Servicios.GestionarMateriaServicio;
import com.proacademic.Dominio.Modelo.Materia;
import com.proacademic.Infraestructura.Web.DTO.MateriaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/materias")
@CrossOrigin("*")
@Tag(name = "Materias", description = "Gestión de materias - Solo ADMIN")
public class MateriaControlador {

    private final GestionarMateriaServicio servicio;

    public MateriaControlador(GestionarMateriaServicio servicio) {
        this.servicio = servicio;
    }

    @Operation(summary = "Crear materia")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Materia creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o materia duplicada")
    })
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MateriaDTO dto) {
        try {
            Materia creada = servicio.crear(dto.toDomain());
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Listar todas las materias")
    @GetMapping
    public ResponseEntity<List<Materia>> listarTodas() {
        return ResponseEntity.ok(servicio.listarTodas());
    }

    @Operation(summary = "Buscar materia por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Materia encontrada"),
            @ApiResponse(responseCode = "404", description = "Materia no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID de la materia") @PathVariable Long id) {
        try {
            return ResponseEntity.ok(servicio.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Actualizar materia")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Materia actualizada"),
            @ApiResponse(responseCode = "404", description = "Materia no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID de la materia") @PathVariable Long id,
            @RequestBody MateriaDTO dto) {
        try {
            Materia actualizada = servicio.actualizar(id, dto.toDomain());
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Eliminar materia")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Materia eliminada"),
            @ApiResponse(responseCode = "404", description = "Materia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID de la materia") @PathVariable Long id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
