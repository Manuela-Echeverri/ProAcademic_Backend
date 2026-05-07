package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Entrada.GestionarUsuarioUseCase;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

public class GestionarUsuarioServicio implements GestionarUsuarioUseCase {

    private final UsuarioRepositorio repositorio;
    private final PasswordEncoder passwordEncoder;

    public GestionarUsuarioServicio(UsuarioRepositorio repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> listarTodos() {
        return repositorio.buscarTodos();
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario actualizar(Long id, Usuario datosActualizados) {

        Usuario existente = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setApellido(datosActualizados.getApellido());
        existente.setCorreo(datosActualizados.getCorreo());
        existente.setUsuario(datosActualizados.getUsuario());
        existente.setRol(datosActualizados.getRol());

        if (datosActualizados.getContrasena() != null && !datosActualizados.getContrasena().isBlank()) {
            existente.setContrasena(passwordEncoder.encode(datosActualizados.getContrasena()));
        }

        return repositorio.actualizar(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (repositorio.buscarPorId(id).isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        repositorio.eliminar(id);
    }

}
