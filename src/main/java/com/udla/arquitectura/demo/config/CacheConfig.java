package com.udla.arquitectura.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Activa el soporte de cache de Spring (@Cacheable en el service).
 */
@Configuration
@EnableCaching
public class CacheConfig {

    public static final String CACHE_PRODUCTOS = "productos";
    public static final String CACHE_PRODUCTO = "producto";
}
