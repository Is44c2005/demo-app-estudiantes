package com.udla.arquitectura.demo.repository;

import com.udla.arquitectura.demo.model.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Patron Proxy: mismo contrato que el repositorio real (ProductoRepository),
 * pero intercepta cada llamada para registrar su duracion antes de delegar.
 * El service recibe este proxy sin saber que no es el repositorio real
 * (principio de sustitucion de Liskov / inversion de dependencias).
 */
@Repository
@Primary
public class ProductoRepositoryProxy implements ProductoRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductoRepositoryProxy.class);

    private final ProductoRepository real;

    public ProductoRepositoryProxy(@Qualifier("repositorioProductoEnMemoria") ProductoRepository real) {
        this.real = real;
    }

    @Override
    public List<Producto> listarTodos() {
        long inicio = System.nanoTime();
        try {
            return real.listarTodos();
        } finally {
            registrar("listarTodos()", inicio);
        }
    }

    @Override
    public Producto buscarPorId(Long id) {
        long inicio = System.nanoTime();
        try {
            return real.buscarPorId(id);
        } finally {
            registrar("buscarPorId(" + id + ")", inicio);
        }
    }

    private void registrar(String operacion, long inicioNanos) {
        long ms = (System.nanoTime() - inicioNanos) / 1_000_000;
        log.info("Acceso al repositorio real: {} tardo {} ms", operacion, ms);
    }
}
