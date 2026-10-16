package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.ComponenteCatalogo;
import com.shopmaster.catalogo.Producto;

public class Main {

    public static void main(String[] args) {
        // Demo temporal del Composite (luego se reemplaza por los 3 casos oficiales)
        Producto monitor = new Producto("Monitor", 800000);
        Producto teclado = new Producto("Teclado", 200000);
        Producto limpiaPantallas = new Producto("Limpia pantallas", 25000);
        Producto panos = new Producto("Panos de microfibra", 15000);

        Combo kitLimpieza = new Combo("Kit de Limpieza");
        kitLimpieza.agregar(limpiaPantallas);
        kitLimpieza.agregar(panos);

        Combo comboGamer = new Combo("Combo Setup Gamer");
        comboGamer.agregar(monitor);
        comboGamer.agregar(teclado);
        comboGamer.agregar(kitLimpieza);

        System.out.println("=== Demo Composite ===");
        mostrarPrecio(monitor);
        mostrarPrecio(kitLimpieza);
        mostrarPrecio(comboGamer);
    }

    // Mismo metodo para producto suelto o combo (polimorfismo del Composite)
    private static void mostrarPrecio(ComponenteCatalogo componente) {
        System.out.println(componente.getNombre() + " -> $" + (long) componente.getPrecio());
    }
}
