package com.shopmaster.pedido;

/**
 * Extra concreto del Decorator: Poliza de Garantia Extendida (+10% del valor actual).
 */
public class GarantiaExtendida extends ExtraDecorator {

    public static final double PORCENTAJE = 0.10;

    public GarantiaExtendida(Pedido pedido) {
        super(pedido);
    }

    @Override
    public double getCosto() {
        return pedido.getCosto() + (pedido.getCosto() * PORCENTAJE);
    }

    @Override
    public String getDescripcion() {
        return pedido.getDescripcion() + " + Poliza de Garantia Extendida";
    }
}
