package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Entrada.CrearUsuarioUseCase;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;

public class CrearUsuarioServicio implements CrearUsuarioUseCase {

    private final UsuarioRepositorio repositorio;

    public CrearUsuarioServicio(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Usuario crear(Usuario usuario) {

        if(usuario.getCorreo() == null || usuario.getCorreo().isEmpty()){
            throw new RuntimeException("Email requerido");
        }

        return repositorio.guardar(usuario);
    }
}
