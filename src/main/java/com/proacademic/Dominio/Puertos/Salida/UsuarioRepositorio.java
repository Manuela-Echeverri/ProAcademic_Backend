package com.proacademic.Dominio.Puertos.Salida;

import com.proacademic.Dominio.Modelo.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorCorreo(String correo);

    Optional<Usuario> buscarPorNombreUsuario(String nombreUsuario);

    List<Usuario> buscarTodos();

    List<Usuario> buscarPorNombre(String nombre);

    Usuario actualizar(Usuario usuario);

    void eliminar(Long id);

    boolean existePorCorreo(String correo);

    boolean existePorNombreUsuario(String nombreUsuario);
}