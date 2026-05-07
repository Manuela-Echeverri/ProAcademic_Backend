package com.proacademic.Infraestructura.Web.DTO;

import com.proacademic.Dominio.Modelo.Usuario;

public class UsuarioDTO {

    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String contrasena;
    private String rol;

    public UsuarioDTO() {}

    // GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // CONVERTIR A ENTITY
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
