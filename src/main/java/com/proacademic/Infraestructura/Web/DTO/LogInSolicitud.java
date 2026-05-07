package com.proacademic.Infraestructura.Web.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Solicitud de inicio de sesión")
public class LogInSolicitud {

    @Schema(description = "Correo electrónico del usuario", example = "admin@proacademic.com")
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "admin123")
    private String contrasena;

    public LogInSolicitud() {}

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
