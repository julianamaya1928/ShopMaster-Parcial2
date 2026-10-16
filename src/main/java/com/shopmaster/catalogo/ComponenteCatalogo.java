package com.shopmaster.catalogo;

/**
 * Patron Composite: interfaz comun para productos individuales y combos.
 * Asi el sistema trata igual un articulo suelto que un paquete compuesto.
 */
public interface ComponenteCatalogo {

    String getNombre();

    double getPrecio();

    // nivel controla la sangria al imprimir el arbol
    void mostrarDesglose(int nivel);
}
