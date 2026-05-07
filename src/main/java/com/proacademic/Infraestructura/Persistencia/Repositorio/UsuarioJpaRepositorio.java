package com.proacademic.Infraestructura.Persistencia.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proacademic.Infraestructura.Persistencia.Entidad.UsuarioEntidad;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioJpaRepositorio extends JpaRepository<UsuarioEntidad, Long> {

    Optional<UsuarioEntidad> findByUsuario(String usuario);

}
