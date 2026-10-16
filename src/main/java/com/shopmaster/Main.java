package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.Producto;

public class Main {

    public static void main(String[] args) {
        // Demo temporal del desglose Composite (luego se reemplaza por los 3 casos oficiales)
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

        System.out.println("=== Desglose del catalogo (Composite) ===");
        comboGamer.mostrarDesglose(0);
        System.out.println();
        System.out.println("Total del combo: $" + (long) comboGamer.getPrecio());
    }
}
