package com.proacademic.Aplicacion.Servicios;

import com.proacademic.Dominio.Modelo.Materia;
import com.proacademic.Dominio.Puertos.Salida.MateriaRepositorio;

import java.util.List;

public class GestionarMateriaServicio {

    private final MateriaRepositorio repositorio;

    public GestionarMateriaServicio(MateriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Materia crear(Materia materia) {
        if (materia.getNombre() == null || materia.getNombre().isBlank()) {
            throw new RuntimeException("El nombre de la materia es requerido");
        }
        if (repositorio.existePorNombre(materia.getNombre())) {
            throw new RuntimeException("Ya existe una materia con ese nombre");
        }
        return repositorio.guardar(materia);
    }

    public List<Materia> listarTodas() {
        return repositorio.buscarTodas();
    }

    public Materia buscarPorId(Long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));
    }

    public Materia actualizar(Long id, Materia datosActualizados) {
        Materia existente = repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setDescripcion(datosActualizados.getDescripcion());

        return repositorio.actualizar(existente);
    }

    public void eliminar(Long id) {
        if (repositorio.buscarPorId(id).isEmpty()) {
            throw new RuntimeException("Materia no encontrada con id: " + id);
        }
        repositorio.eliminar(id);
    }

}
