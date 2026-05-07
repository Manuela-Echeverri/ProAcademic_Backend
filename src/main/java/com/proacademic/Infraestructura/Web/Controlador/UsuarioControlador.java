package com.proacademic.Infraestructura.Web.Controlador;

import com.proacademic.Aplicacion.Servicios.CrearUsuarioServicio;
import com.proacademic.Aplicacion.Servicios.LogInServicio;
import com.proacademic.Dominio.Modelo.Usuario;
import com.proacademic.Infraestructura.Web.DTO.LogInSolicitud;
import com.proacademic.Infraestructura.Web.DTO.UsuarioDTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioControlador {

    private final CrearUsuarioServicio crearUsuarioServicio;
    private final LogInServicio logInServicio;

    public UsuarioControlador(CrearUsuarioServicio crearUsuarioServicio,
                              LogInServicio logInServicio) {

        this.crearUsuarioServicio = crearUsuarioServicio;
        this.logInServicio = logInServicio;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario){

        return crearUsuarioServicio.crear(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LogInSolicitud solicitud){

        return logInServicio.autenticar(
                solicitud.getCorreo(),
                solicitud.getContrasena()
        );
    }
}
