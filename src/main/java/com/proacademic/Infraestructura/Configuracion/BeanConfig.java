package com.proacademic.Infraestructura.Configuracion;

import com.proacademic.Aplicacion.Servicios.CrearUsuarioServicio;
import com.proacademic.Aplicacion.Servicios.GestionarMateriaServicio;
import com.proacademic.Aplicacion.Servicios.GestionarUsuarioServicio;
import com.proacademic.Aplicacion.Servicios.LogInServicio;
import com.proacademic.Dominio.Puertos.Salida.MateriaRepositorio;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public CrearUsuarioServicio crearUsuarioServicio(UsuarioRepositorio repo,
                                                     PasswordEncoder passwordEncoder) {
        return new CrearUsuarioServicio(repo, passwordEncoder);
    }

    @Bean
    public GestionarUsuarioServicio gestionarUsuarioServicio(UsuarioRepositorio repo,
                                                             PasswordEncoder passwordEncoder) {
        return new GestionarUsuarioServicio(repo, passwordEncoder);
    }

    @Bean
    public LogInServicio logInServicio(UsuarioRepositorio repo,
                                       PasswordEncoder passwordEncoder,
                                       JwtServicio jwtServicio) {
        return new LogInServicio(repo, passwordEncoder, jwtServicio);
    }

    @Bean
    public GestionarMateriaServicio gestionarMateriaServicio(MateriaRepositorio repo) {
        return new GestionarMateriaServicio(repo);
    }
}