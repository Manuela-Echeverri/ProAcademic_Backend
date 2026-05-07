package com.proacademic.Dominio.Puertos.Entrada;

import com.proacademic.Dominio.Modelo.Usuario;
import java.util.List;

public interface GestionarUsuarioUseCase {

    List<Usuario> listarTodos();

    List<Usuario> buscarPorNombre(String nombre);

    Usuario buscarPorId(Long id);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);

}
