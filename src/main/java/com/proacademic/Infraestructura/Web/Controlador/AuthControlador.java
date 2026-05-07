package com.proacademic.Infraestructura.Web.Controlador;

import com.proacademic.Aplicacion.Servicios.LogInServicio;
import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Infraestructura.Web.DTO.LogInSolicitud;
import com.proacademic.Infraestructura.Web.DTO.UsuarioRespuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Tag(name = "Autenticación", description = "Inicio de sesión")
public class AuthControlador {

    private final LogInServicio logInServicio;

    public AuthControlador(LogInServicio logInServicio) {
        this.logInServicio = logInServicio;
    }

    @Operation(summary = "Iniciar sesión",
            description = "Autentica al usuario con correo y contraseña")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInSolicitud solicitud) {
        try {
            Usuario usuario = logInServicio.autenticar(
                    solicitud.getCorreo(),
                    solicitud.getContrasena()
            );
            return ResponseEntity.ok(UsuarioRespuesta.fromDomain(usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}