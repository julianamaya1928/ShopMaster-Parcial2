package com.shopmaster.pedido;

/**
 * Decorador abstracto: guarda el pedido envuelto y delega.
 * Los extras concretos heredan de esta clase.
 */
public abstract class ExtraDecorator implements Pedido {

    protected Pedido pedido;

    public ExtraDecorator(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public double getCosto() {
        return pedido.getCosto();
    }

    @Override
    public String getDescripcion() {
        return pedido.getDescripcion();
    }
}
