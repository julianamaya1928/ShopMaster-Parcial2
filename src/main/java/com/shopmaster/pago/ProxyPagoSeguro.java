package com.shopmaster.pago;

/**
 * Patron Proxy: se pone en medio fingiendo ser el ProcesadorPago.
 * Controla cupos, usuarios bloqueados y deja registro de auditoria.
 * Si rechaza, NO toca el procesador real.
 */
public class ProxyPagoSeguro implements ProcesadorPago {

    public static final double TOPE_COP = 5000000;
    public static final String USUARIO_BLOQUEADO = "BLOQUEADO";

    private ProcesadorPago procesadorReal;

    // El procesador real se recibe por constructor (el Adapter de Stripe)
    public ProxyPagoSeguro(ProcesadorPago procesadorReal) {
        this.procesadorReal = procesadorReal;
    }

    @Override
    public void cobrarEnPesos(String usuario, double montoCOP) {
        // Regla 1: usuario reportado
        if (USUARIO_BLOQUEADO.equals(usuario)) {
            System.out.println("[SEGURIDAD] Transaccion RECHAZADA.");
            System.out.println("[SEGURIDAD] Motivo: usuario reportado como BLOQUEADO.");
            System.out.println("[SEGURIDAD] No se contacta al procesador bancario.");
            return;
        }

        // Regla 2: tope de dinero
        if (montoCOP > TOPE_COP) {
            System.out.println("[SEGURIDAD] Transaccion RECHAZADA.");
            System.out.println("[SEGURIDAD] Motivo: monto $" + (long) montoCOP
                    + " COP supera el tope de $" + (long) TOPE_COP + " COP.");
            System.out.println("[SEGURIDAD] No se contacta al procesador bancario.");
            return;
        }

        // Datos validos: auditoria y delegacion al procesador real
        System.out.println("[AUDITORIA: Transaccion aprobada para " + usuario
                + " por $" + (long) montoCOP + " COP]");
        procesadorReal.cobrarEnPesos(usuario, montoCOP);
    }
}
