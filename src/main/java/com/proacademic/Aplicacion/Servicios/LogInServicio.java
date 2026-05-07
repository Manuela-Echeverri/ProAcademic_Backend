package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import org.springframework.stereotype.Service;

@Service
public class LogInServicio {

    private final UsuarioRepositorio repositorio;

    public LogInServicio(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario autenticar(String correo, String contrasena){

        Usuario usuario = repositorio.buscarPorCorreo(correo);

        if(usuario == null){
            throw new RuntimeException("Usuario no encontrado");
        }

        if(!usuario.getContrasena().equals(contrasena)){
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

}
