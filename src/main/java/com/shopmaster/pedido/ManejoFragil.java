package com.shopmaster.pedido;

/**
 * Extra concreto del Decorator: Manejo Prioritario Fragil (+$25.000 fijos).
 */
public class ManejoFragil extends ExtraDecorator {

    public static final double COSTO_EXTRA = 25000;

    public ManejoFragil(Pedido pedido) {
        super(pedido);
    }

    @Override
    public double getCosto() {
        return pedido.getCosto() + COSTO_EXTRA;
    }

    @Override
    public String getDescripcion() {
        return pedido.getDescripcion() + " + Manejo Prioritario Fragil";
    }
}
