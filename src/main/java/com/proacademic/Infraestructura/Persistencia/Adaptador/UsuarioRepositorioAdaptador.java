package com.proacademic.Infraestructura.Persistencia.Adaptador;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import com.proacademic.Infraestructura.Persistencia.Entidad.UsuarioEntidad;
import com.proacademic.Infraestructura.Persistencia.Repositorio.UsuarioJpaRepositorio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioRepositorioAdaptador implements UsuarioRepositorio {

    private final UsuarioJpaRepositorio jpa;

    public UsuarioRepositorioAdaptador(UsuarioJpaRepositorio jpa) {
        this.jpa = jpa;
    }

    // ── Entidad → Dominio ──────────────────────────────────────
    private Usuario toDominio(UsuarioEntidad e) {
        Usuario u = new Usuario();
        u.setId(e.getId());
        u.setNombre(e.getNombre());
        u.setApellido(e.getApellido());
        u.setCorreo(e.getCorreo());
        u.setUsuario(e.getUsuario());
        u.setContrasena(e.getContrasena());
        u.setRol(e.getRol() != null ? e.getRol().name() : null);
        u.setFechaCreacion(e.getFechaCreacion());
        return u;
    }

    // ── Dominio → Entidad ──────────────────────────────────────
    private UsuarioEntidad toEntidad(Usuario u) {
        UsuarioEntidad e = new UsuarioEntidad();
        e.setId(u.getId());
        e.setNombre(u.getNombre());
        e.setApellido(u.getApellido());
        e.setCorreo(u.getCorreo());
        e.setUsuario(u.getUsuario());
        e.setContrasena(u.getContrasena());
        if (u.getRol() != null) {
            e.setRol(UsuarioEntidad.Rol.valueOf(u.getRol()));
        }
        return e;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntidad guardado = jpa.save(toEntidad(usuario));
        return toDominio(guardado);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpa.findById(id).map(this::toDominio);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return jpa.findByCorreo(correo).map(this::toDominio);
    }

    @Override
    public Optional<Usuario> buscarPorNombreUsuario(String nombreUsuario) {
        return jpa.findByUsuario(nombreUsuario).map(this::toDominio);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return jpa.findAll().stream()
                .map(this::toDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return jpa.findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre)
                .stream()
                .map(this::toDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        UsuarioEntidad actualizado = jpa.save(toEntidad(usuario));
        return toDominio(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return jpa.existsByCorreo(correo);
    }

    @Override
    public boolean existePorNombreUsuario(String nombreUsuario) {
        return jpa.existsByUsuario(nombreUsuario);
    }
}
