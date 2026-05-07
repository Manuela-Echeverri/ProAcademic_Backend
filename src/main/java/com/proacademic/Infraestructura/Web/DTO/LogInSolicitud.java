package com.proacademic.Infraestructura.Web.DTO;

public class LogInSolicitud {

    private String correo;
    private String contrasena;

    public LogInSolicitud() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
