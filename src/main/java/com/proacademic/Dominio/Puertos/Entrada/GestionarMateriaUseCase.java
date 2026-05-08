package com.proacademic.Dominio.Puertos.Entrada;

import com.proacademic.Dominio.Modelo.Materia;
import java.util.List;

public interface GestionarMateriaUseCase {

    Materia crear(Materia materia);

    List<Materia> listarTodas();

    Materia buscarPorId(Long id);

    Materia actualizar(Long id, Materia materia);

    void eliminar(Long id);

}
