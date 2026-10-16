package com.shopmaster.pedido;

/**
 * Extra concreto del Decorator: Empaque de Regalo Premium (+$15.000 fijos).
 */
public class EmpaqueRegalo extends ExtraDecorator {

    public static final double COSTO_EXTRA = 15000;

    public EmpaqueRegalo(Pedido pedido) {
        super(pedido);
    }

    @Override
    public double getCosto() {
        return pedido.getCosto() + COSTO_EXTRA;
    }

    @Override
    public String getDescripcion() {
        return pedido.getDescripcion() + " + Empaque de Regalo Premium";
    }
}
