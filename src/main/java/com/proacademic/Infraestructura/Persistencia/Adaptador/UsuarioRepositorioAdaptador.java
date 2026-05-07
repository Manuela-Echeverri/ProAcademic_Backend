package com.proacademic.Infraestructura.Persistencia.Adaptador;

import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import com.proacademic.Infraestructura.Persistencia.Entidad.UsuarioEntidad;
import com.proacademic.Infraestructura.Persistencia.Repositorio.UsuarioJpaRepositorio;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositorioAdaptador implements UsuarioRepositorio {

    private final UsuarioJpaRepositorio jpa;

    public UsuarioRepositorioAdaptador(UsuarioJpaRepositorio jpa) {
        this.jpa = jpa;
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        UsuarioEntidad entidad = new UsuarioEntidad();

        entidad.setNombre(usuario.getNombre());
        entidad.setApellido(usuario.getApellido());
        entidad.setCorreo(usuario.getCorreo());
        entidad.setUsuario(usuario.getUsuario());
        entidad.setContrasena(usuario.getContrasena());
        entidad.setRol(usuario.getRol());

        UsuarioEntidad guardado = jpa.save(entidad);

        usuario.setId(guardado.getId());

        return usuario;
    }
}
