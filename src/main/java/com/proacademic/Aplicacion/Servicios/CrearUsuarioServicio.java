package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Entrada.CrearUsuarioUseCase;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CrearUsuarioServicio implements CrearUsuarioUseCase {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder passwordEncoder;

    public CrearUsuarioServicio(UsuarioRepositorio repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario crear(Usuario usuario) {

        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es requerido");
        }
        if (usuario.getUsuario() == null || usuario.getUsuario().isBlank()) {
            throw new RuntimeException("El nombre de usuario es requerido");
        }
        if (repositorio.existePorCorreo(usuario.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }
        if (repositorio.existePorNombreUsuario(usuario.getUsuario())) {
            throw new RuntimeException("Ya existe un usuario con ese nombre de usuario");
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        return repositorio.guardar(usuario);
    }
}