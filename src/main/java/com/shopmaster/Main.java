package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.Producto;
import com.shopmaster.facade.CompraRapidaFacade;

/**
 * Punto de entrada. Los 3 escenarios de prueba del parcial
 * estan programados aqui (sin Scanner).
 * Toda la complejidad se delega a la Facade.
 */
public class Main {

    public static void main(String[] args) {
        CompraRapidaFacade compra = new CompraRapidaFacade();

        // --------------------------------------------------
        // Caso de Prueba 1 (Compra Exitosa Completa)
        // Combo + sub-combo, usuario valido, garantia + empaque
        // --------------------------------------------------
        Combo kitLimpieza = new Combo("Kit de Limpieza");
        kitLimpieza.agregar(new Producto("Limpia pantallas", 25000));
        kitLimpieza.agregar(new Producto("Panos de microfibra", 15000));

        Combo comboGamer = new Combo("Combo Setup Gamer");
        comboGamer.agregar(new Producto("Monitor", 800000));
        comboGamer.agregar(new Producto("Teclado", 200000));
        comboGamer.agregar(kitLimpieza);

        compra.ejecutarCompraRapida(
                "Caso de Prueba 1 (Compra Exitosa Completa)",
                comboGamer,
                "cliente_vip",
                true,   // Poliza de Garantia Extendida
                true,   // Empaque de Regalo Premium
                false
        );

        // --------------------------------------------------
        // Caso de Prueba 2 (Rechazo por Tope de Dinero)
        // Servidor 6.000.000 + Manejo Prioritario Fragil
        // --------------------------------------------------
        compra.ejecutarCompraRapida(
                "Caso de Prueba 2 (Rechazo por Tope de Dinero)",
                new Producto("Servidor Empresarial", 6000000),
                "cliente_normal",
                false,
                false,
                true    // Manejo Prioritario Fragil
        );

        // --------------------------------------------------
        // Caso de Prueba 3 (Rechazo por Usuario Reportado)
        // Mouse economico + usuario exactamente "BLOQUEADO"
        // --------------------------------------------------
        compra.ejecutarCompraRapida(
                "Caso de Prueba 3 (Rechazo por Usuario Reportado)",
                new Producto("Mouse", 150000),
                "BLOQUEADO",
                false,
                false,
                false
        );
    }
}
