package com.proacademic.Infraestructura.Web.Controlador;

import com.proacademic.Aplicacion.Servicios.CrearUsuarioServicio;
import com.proacademic.Aplicacion.Servicios.GestionarUsuarioServicio;
import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Infraestructura.Web.DTO.UsuarioDTO;
import com.proacademic.Infraestructura.Web.DTO.UsuarioRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@Tag(name = "Usuarios", description = "Gestión de usuarios - Solo ADMIN")
public class UsuarioControlador {

    private final CrearUsuarioServicio crearUsuarioServicio;
    private final GestionarUsuarioServicio gestionarUsuarioServicio;

    public UsuarioControlador(CrearUsuarioServicio crearUsuarioServicio,
                              GestionarUsuarioServicio gestionarUsuarioServicio) {
        this.crearUsuarioServicio = crearUsuarioServicio;
        this.gestionarUsuarioServicio = gestionarUsuarioServicio;
    }

    @Operation(summary = "Crear usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario duplicado")
    })
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody UsuarioDTO dto) {
        try {
            Usuario creado = crearUsuarioServicio.crear(dto.toDomain());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(UsuarioRespuesta.fromDomain(creado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UsuarioRespuesta>> listarTodos() {
        List<UsuarioRespuesta> lista = gestionarUsuarioServicio.listarTodos()
                .stream()
                .map(UsuarioRespuesta::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Buscar usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del usuario") @PathVariable Long id) {
        try {
            Usuario u = gestionarUsuarioServicio.buscarPorId(id);
            return ResponseEntity.ok(UsuarioRespuesta.fromDomain(u));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Buscar usuarios por nombre o apellido")
    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioRespuesta>> buscarPorNombre(
            @Parameter(description = "Texto a buscar") @RequestParam String nombre) {
        List<UsuarioRespuesta> resultados = gestionarUsuarioServicio.buscarPorNombre(nombre)
                .stream()
                .map(UsuarioRespuesta::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultados);
    }

    @Operation(summary = "Actualizar usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del usuario") @PathVariable Long id,
            @RequestBody UsuarioDTO dto) {
        try {
            Usuario actualizado = gestionarUsuarioServicio.actualizar(id, dto.toDomain());
            return ResponseEntity.ok(UsuarioRespuesta.fromDomain(actualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Eliminar usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del usuario") @PathVariable Long id) {
        try {
            gestionarUsuarioServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}