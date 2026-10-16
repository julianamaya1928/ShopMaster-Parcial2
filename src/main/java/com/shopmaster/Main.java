package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.Producto;
import com.shopmaster.pago.StripeUSA;
import com.shopmaster.pedido.EmpaqueRegalo;
import com.shopmaster.pedido.GarantiaExtendida;
import com.shopmaster.pedido.ManejoFragil;
import com.shopmaster.pedido.Pedido;
import com.shopmaster.pedido.PedidoBase;

public class Main {

    public static void main(String[] args) {
        // Catalogo (Composite)
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

        // Pedido con extras (Decorator)
        Pedido pedido = new PedidoBase(comboGamer);
        pedido = new GarantiaExtendida(pedido);
        pedido = new EmpaqueRegalo(pedido);

        System.out.println("=== Pedido con extras (Decorator) ===");
        System.out.println(pedido.getDescripcion());
        System.out.println("Costo final: $" + (long) pedido.getCosto() + " COP");
        System.out.println();

        // Demo StripeUSA: API externa incompatible con ProcesadorPago
        // ShopMaster necesita cobrarEnPesos(...); Stripe solo tiene chargeInDollars(...)
        System.out.println("=== Demo StripeUSA (API externa, sin adapter todavia) ===");
        StripeUSA stripe = new StripeUSA();
        stripe.chargeInDollars(100.0, "prueba@shopmaster.com", "mk_test_shopmaster");

        // Demo otro extra
        Pedido pedidoFragil = new PedidoBase(new Producto("Servidor Empresarial", 6000000));
        pedidoFragil = new ManejoFragil(pedidoFragil);
        System.out.println();
        System.out.println("=== Demo Manejo Fragil ===");
        System.out.println(pedidoFragil.getDescripcion() + " -> $" + (long) pedidoFragil.getCosto());
    }
}
