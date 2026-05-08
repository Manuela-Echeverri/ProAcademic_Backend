package com.proacademic.Dominio.Puertos.Salida;

import com.proacademic.Dominio.Modelo.Materia;
import java.util.List;
import java.util.Optional;

public interface MateriaRepositorio {

    Materia guardar(Materia materia);

    Optional<Materia> buscarPorId(Long id);

    List<Materia> buscarTodas();

    Materia actualizar(Materia materia);

    void eliminar(Long id);

    boolean existePorNombre(String nombre);

}
