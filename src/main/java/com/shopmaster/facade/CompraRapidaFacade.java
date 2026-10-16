package com.shopmaster.facade;

import com.shopmaster.catalogo.ComponenteCatalogo;
import com.shopmaster.pago.AdaptadorStripe;
import com.shopmaster.pago.ProcesadorPago;
import com.shopmaster.pago.ProxyPagoSeguro;
import com.shopmaster.pago.StripeUSA;
import com.shopmaster.pedido.EmpaqueRegalo;
import com.shopmaster.pedido.GarantiaExtendida;
import com.shopmaster.pedido.ManejoFragil;
import com.shopmaster.pedido.Pedido;
import com.shopmaster.pedido.PedidoBase;

/**
 * Patron Facade: un solo punto de entrada para la compra rapida.
 * El Main no necesita conocer combos, extras, adapter ni proxy por dentro.
 */
public class CompraRapidaFacade {

    private ProcesadorPago procesador;

    public CompraRapidaFacade() {
        // Arma la cadena de pago una sola vez: Proxy -> Adapter -> StripeUSA
        ProcesadorPago adaptador = new AdaptadorStripe(new StripeUSA(), "mk_live_shopmaster");
        this.procesador = new ProxyPagoSeguro(adaptador);
    }

    /**
     * Ejecuta la compra completa: desglose, extras opcionales y cobro seguro.
     *
     * @param tituloCaso     texto para separar en consola
     * @param catalogo       producto o combo (Composite)
     * @param usuario        nombre del cliente
     * @param conGarantia    si se agrega poliza (+10%)
     * @param conEmpaque     si se agrega empaque de regalo (+15000)
     * @param conFragil      si se agrega manejo fragil (+25000)
     */
    public void ejecutarCompraRapida(String tituloCaso,
                                     ComponenteCatalogo catalogo,
                                     String usuario,
                                     boolean conGarantia,
                                     boolean conEmpaque,
                                     boolean conFragil) {
        System.out.println("========== " + tituloCaso + " ==========");
        System.out.println("Usuario: " + usuario);
        System.out.println();

        System.out.println("--- Desglose del catalogo ---");
        catalogo.mostrarDesglose(0);
        System.out.println();

        Pedido pedido = new PedidoBase(catalogo);

        if (conGarantia) {
            pedido = new GarantiaExtendida(pedido);
        }
        if (conEmpaque) {
            pedido = new EmpaqueRegalo(pedido);
        }
        if (conFragil) {
            pedido = new ManejoFragil(pedido);
        }

        System.out.println("--- Pedido ---");
        System.out.println(pedido.getDescripcion());
        System.out.println("Total a cobrar: $" + (long) pedido.getCosto() + " COP");
        System.out.println();

        System.out.println("--- Procesando pago ---");
        procesador.cobrarEnPesos(usuario, pedido.getCosto());
        System.out.println("========================================");
        System.out.println();
    }
}
