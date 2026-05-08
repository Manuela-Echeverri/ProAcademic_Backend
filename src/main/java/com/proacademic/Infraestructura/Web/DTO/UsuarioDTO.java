package com.proacademic.Infraestructura.Web.DTO;

import com.proacademic.Dominio.Modelo.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para crear o actualizar un usuario")
public class UsuarioDTO {

    @Schema(description = "Nombre", example = "María")
    private String nombre;

    @Schema(description = "Apellido", example  = "González")
    private String apellido;

    @Schema(description = "Correo electrónico", example = "maria@proacademic.com")
    private String correo;

    @Schema(description = "Nombre de usuario único", example = "mgonzalez")
    private String usuario;

    @Schema(description = "Contraseña", example = "segura123")
    private String contrasena;

    @Schema(description = "Rol del usuario", example = "DOCENTE",
            allowableValues = {"ADMIN", "DOCENTE", "ESTUDIANTE"})
    private String rol;

    public UsuarioDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Usuario toDomain() {
        Usuario u = new Usuario();
        u.setNombre(this.nombre);
        u.setApellido(this.apellido);
        u.setCorreo(this.correo);
        u.setUsuario(this.usuario);
        u.setContrasena(this.contrasena);
        u.setRol(this.rol);
        return u;
    }
}