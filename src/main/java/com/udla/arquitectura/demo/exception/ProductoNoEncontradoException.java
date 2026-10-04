package com.udla.arquitectura.demo.exception;

/**
 * Error de dominio: el producto solicitado no existe.
 */
public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado: " + id);
    }
}
