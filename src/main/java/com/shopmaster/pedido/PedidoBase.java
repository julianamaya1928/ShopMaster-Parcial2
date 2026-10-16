package com.shopmaster.pedido;

import com.shopmaster.catalogo.ComponenteCatalogo;

/**
 * Pedido concreto sin extras.
 * Toma el precio del producto o combo del catalogo (Composite).
 */
public class PedidoBase implements Pedido {

    private ComponenteCatalogo componente;

    public PedidoBase(ComponenteCatalogo componente) {
        this.componente = componente;
    }

    @Override
    public double getCosto() {
        return componente.getPrecio();
    }

    @Override
    public String getDescripcion() {
        return componente.getNombre();
    }
}
