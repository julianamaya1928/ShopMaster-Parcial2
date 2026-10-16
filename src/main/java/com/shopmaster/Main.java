package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.Producto;
import com.shopmaster.facade.CompraRapidaFacade;

public class Main {

    public static void main(String[] args) {
        // Un solo punto de entrada (Facade). El Main ya no arma adapter ni proxy.
        CompraRapidaFacade compra = new CompraRapidaFacade();

        // Demo 1: compra exitosa con garantia y empaque
        Combo kitLimpieza = new Combo("Kit de Limpieza");
        kitLimpieza.agregar(new Producto("Limpia pantallas", 25000));
        kitLimpieza.agregar(new Producto("Panos de microfibra", 15000));

        Combo comboGamer = new Combo("Combo Setup Gamer");
        comboGamer.agregar(new Producto("Monitor", 800000));
        comboGamer.agregar(new Producto("Teclado", 200000));
        comboGamer.agregar(kitLimpieza);

        compra.ejecutarCompraRapida(
                "Demo 1 - Compra exitosa",
                comboGamer,
                "cliente_vip",
                true,   // garantia
                true,   // empaque
                false   // fragil
        );

        // Demo 2: rechazo por tope de dinero
        compra.ejecutarCompraRapida(
                "Demo 2 - Rechazo por tope",
                new Producto("Servidor Empresarial", 6000000),
                "cliente_normal",
                false,
                false,
                true    // fragil
        );

        // Demo 3: rechazo por usuario bloqueado
        compra.ejecutarCompraRapida(
                "Demo 3 - Usuario BLOQUEADO",
                new Producto("Mouse", 150000),
                "BLOQUEADO",
                false,
                false,
                false
        );
    }
}
