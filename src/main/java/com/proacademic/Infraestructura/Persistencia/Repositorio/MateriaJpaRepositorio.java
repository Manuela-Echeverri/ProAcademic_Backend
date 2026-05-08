package com.proacademic.Infraestructura.Persistencia.Repositorio;

import com.proacademic.Infraestructura.Persistencia.Entidad.MateriaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaJpaRepositorio extends JpaRepository<MateriaEntidad, Long> {

    boolean existsByNombre(String nombre);

}
