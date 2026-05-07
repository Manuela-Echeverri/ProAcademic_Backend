package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LogInServicio {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder passwordEncoder;

    public LogInServicio(UsuarioRepositorio repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario autenticar(String correo, String contrasena) {

        Usuario usuario = repositorio.buscarPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }
}
