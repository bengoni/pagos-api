package com.bienesraices.pagos.api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // El PRIMERO queda seleccionado por defecto en Swagger
        return new OpenAPI().servers(List.of(
            new Server().url("https://pagos-api-production.up.railway.app").description("Producción"),
            new Server().url("http://localhost:8089").description("Local")
        ));
    }
}
