package com.proacademic.Infraestructura.Persistencia.Repositorio;

import com.proacademic.Infraestructura.Persistencia.Entidad.UsuarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioJpaRepositorio extends JpaRepository<UsuarioEntidad, Long> {

    Optional<UsuarioEntidad> findByCorreo(String correo);

    Optional<UsuarioEntidad> findByUsuario(String usuario);

    List<UsuarioEntidad> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(
            String nombre, String apellido);

    boolean existsByCorreo(String correo);

    boolean existsByUsuario(String usuario);
}
