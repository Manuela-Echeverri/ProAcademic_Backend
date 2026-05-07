package com.proacademic.Infraestructura.Web.DTO;

import com.proacademic.Dominio.Modelo.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Respuesta con datos del usuario (sin contraseña)")

public class UsuarioRespuesta {

    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String rol;
    private LocalDateTime fechaCreacion;

    public UsuarioRespuesta() {}

    public static UsuarioRespuesta fromDomain(Usuario u) {
        UsuarioRespuesta r = new UsuarioRespuesta();
        r.setId(u.getId());
        r.setNombre(u.getNombre());
        r.setApellido(u.getApellido());
        r.setCorreo(u.getCorreo());
        r.setUsuario(u.getUsuario());
        r.setRol(u.getRol());
        r.setFechaCreacion(u.getFechaCreacion());
        return r;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
