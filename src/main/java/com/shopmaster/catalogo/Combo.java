package com.shopmaster.catalogo;

import java.util.ArrayList;
import java.util.List;

/**
 * Compuesto del Composite: combo o caja que puede tener productos
 * y tambien otros combos adentro (anidacion).
 */
public class Combo implements ComponenteCatalogo {

    private String nombre;
    private List<ComponenteCatalogo> componentes;

    public Combo(String nombre) {
        this.nombre = nombre;
        this.componentes = new ArrayList<>();
    }

    public void agregar(ComponenteCatalogo componente) {
        componentes.add(componente);
    }

    public List<ComponenteCatalogo> getComponentes() {
        return componentes;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        double total = 0;
        for (ComponenteCatalogo componente : componentes) {
            total = total + componente.getPrecio();
        }
        return total;
    }
}
