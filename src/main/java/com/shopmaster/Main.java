package com.shopmaster;

import com.shopmaster.catalogo.Combo;
import com.shopmaster.catalogo.Producto;
import com.shopmaster.pago.AdaptadorStripe;
import com.shopmaster.pago.ProcesadorPago;
import com.shopmaster.pago.ProxyPagoSeguro;
import com.shopmaster.pago.StripeUSA;
import com.shopmaster.pedido.EmpaqueRegalo;
import com.shopmaster.pedido.GarantiaExtendida;
import com.shopmaster.pedido.ManejoFragil;
import com.shopmaster.pedido.Pedido;
import com.shopmaster.pedido.PedidoBase;

public class Main {

    public static void main(String[] args) {
        // Procesador real (Adapter) envuelto por el Proxy de seguridad
        ProcesadorPago procesadorReal = new AdaptadorStripe(new StripeUSA(), "mk_live_shopmaster");
        ProcesadorPago procesador = new ProxyPagoSeguro(procesadorReal);

        // ----- Demo 1: cobro exitoso (pasa el proxy) -----
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

        System.out.println("=== Demo 1: cobro OK (Proxy + Adapter) ===");
        comboGamer.mostrarDesglose(0);

        Pedido pedido = new PedidoBase(comboGamer);
        pedido = new GarantiaExtendida(pedido);
        pedido = new EmpaqueRegalo(pedido);
        System.out.println(pedido.getDescripcion());
        System.out.println("Costo: $" + (long) pedido.getCosto() + " COP");
        procesador.cobrarEnPesos("cliente_vip", pedido.getCosto());
        System.out.println();

        // ----- Demo 2: rechazo por tope de dinero -----
        System.out.println("=== Demo 2: rechazo por tope (>$5.000.000) ===");
        Pedido pedidoCaro = new PedidoBase(new Producto("Servidor Empresarial", 6000000));
        pedidoCaro = new ManejoFragil(pedidoCaro);
        System.out.println(pedidoCaro.getDescripcion() + " -> $" + (long) pedidoCaro.getCosto());
        procesador.cobrarEnPesos("cliente_normal", pedidoCaro.getCosto());
        System.out.println();

        // ----- Demo 3: rechazo por usuario BLOQUEADO -----
        System.out.println("=== Demo 3: rechazo por usuario BLOQUEADO ===");
        Pedido pedidoMouse = new PedidoBase(new Producto("Mouse", 150000));
        System.out.println(pedidoMouse.getDescripcion() + " -> $" + (long) pedidoMouse.getCosto());
        procesador.cobrarEnPesos("BLOQUEADO", pedidoMouse.getCosto());
    }
}
