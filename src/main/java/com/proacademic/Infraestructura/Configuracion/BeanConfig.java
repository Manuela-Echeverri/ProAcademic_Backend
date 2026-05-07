package com.proacademic.Infraestructura.Configuracion;

import com.proacademic.Aplicacion.Servicios.CrearUsuarioServicio;
import com.proacademic.Dominio.Puertos.Salida.UsuarioRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CrearUsuarioServicio crearUsuarioServicio(UsuarioRepositorio repo){
        return new CrearUsuarioServicio(repo);
    }

}
