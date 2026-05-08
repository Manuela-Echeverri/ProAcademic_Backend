package com.proacademic.Infraestructura.Persistencia.Adaptador;

import com.proacademic.Dominio.Modelo.Materia;
import com.proacademic.Dominio.Puertos.Salida.MateriaRepositorio;
import com.proacademic.Infraestructura.Persistencia.Entidad.MateriaEntidad;
import com.proacademic.Infraestructura.Persistencia.Repositorio.MateriaJpaRepositorio;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MateriaRepositorioAdaptador implements MateriaRepositorio {

    private final MateriaJpaRepositorio jpa;

    public MateriaRepositorioAdaptador(MateriaJpaRepositorio jpa) {
        this.jpa = jpa;
    }

    // ── Entidad → Dominio ──────────────────────────────────────
    private Materia toDominio(MateriaEntidad e) {
        Materia m = new Materia();
        m.setId(e.getId());
        m.setNombre(e.getNombre());
        m.setDescripcion(e.getDescripcion());
        return m;
    }

    // ── Dominio → Entidad ──────────────────────────────────────
    private MateriaEntidad toEntidad(Materia m) {
        MateriaEntidad e = new MateriaEntidad();
        e.setId(m.getId());
        e.setNombre(m.getNombre());
        e.setDescripcion(m.getDescripcion());
        return e;
    }

    @Override
    public Materia guardar(Materia materia) {
        return toDominio(jpa.save(toEntidad(materia)));
    }

    @Override
    public Optional<Materia> buscarPorId(Long id) {
        return jpa.findById(id).map(this::toDominio);
    }

    @Override
    public List<Materia> buscarTodas() {
        return jpa.findAll().stream()
                .map(this::toDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Materia actualizar(Materia materia) {
        return toDominio(jpa.save(toEntidad(materia)));
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return jpa.existsByNombre(nombre);
    }
}
