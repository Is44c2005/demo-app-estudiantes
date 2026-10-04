package com.udla.arquitectura.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS para desarrollo.
 *
 * El navegador bloquea las peticiones entre origenes distintos (esquema, host
 * o puerto) salvo que el servidor responda con la cabecera
 * Access-Control-Allow-Origin. Un frontend en http://localhost:5500 llamando a
 * http://localhost:8080 es un origen distinto, por eso se declara aqui.
 * Los origenes permitidos se configuran en application.properties.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final String[] origenesPermitidos;

    public CorsConfig(@Value("${app.cors.allowed-origins}") String[] origenesPermitidos) {
        this.origenesPermitidos = origenesPermitidos;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(origenesPermitidos)
                .allowedMethods("GET");
    }
}
