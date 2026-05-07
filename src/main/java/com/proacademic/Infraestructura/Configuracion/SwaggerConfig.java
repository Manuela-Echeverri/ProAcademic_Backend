package com.proacademic.Infraestructura.Configuracion;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI proAcademicOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ProAcademic API")
                        .description("Backend del sistema académico ProAcademic. " +
                                "Gestión de usuarios, cursos, materias, " +
                                "inscripciones y calificaciones.")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("ProAcademic Team")
                                .email("admin@proacademic.com")));
    }
}