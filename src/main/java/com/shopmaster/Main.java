package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.Producto;
import com.shopmaster.pedido.EmpaqueRegalo;
import com.shopmaster.pedido.Pedido;
import com.shopmaster.pedido.PedidoBase;

public class Main {

    public static void main(String[] args) {
        // Catalogo (Composite) - mismo ejemplo de antes
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

        System.out.println("=== Catalogo (Composite) ===");
        comboGamer.mostrarDesglose(0);
        System.out.println("Total catalogo: $" + (long) comboGamer.getPrecio());
        System.out.println();

        // Demo temporal del Decorator (un solo extra por ahora)
        Pedido pedido = new PedidoBase(comboGamer);
        System.out.println("=== Pedido base ===");
        System.out.println(pedido.getDescripcion() + " -> $" + (long) pedido.getCosto());
        System.out.println();

        // Se envuelve el pedido con un extra (sin subclases por combinacion)
        pedido = new EmpaqueRegalo(pedido);
        System.out.println("=== Pedido con extra (Decorator) ===");
        System.out.println(pedido.getDescripcion() + " -> $" + (long) pedido.getCosto());

        // Doble empaque: se puede volver a envolver
        pedido = new EmpaqueRegalo(pedido);
        System.out.println("=== Pedido con doble empaque ===");
        System.out.println(pedido.getDescripcion() + " -> $" + (long) pedido.getCosto());
    }
}
