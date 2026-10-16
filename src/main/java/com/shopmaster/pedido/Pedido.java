package com.shopmaster.pedido;

/**
 * Patron Decorator: interfaz comun del pedido.
 * El pedido base y los extras se tratan igual.
 */
public interface Pedido {

    double getCosto();

    String getDescripcion();
}
