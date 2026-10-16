package com.shopmaster.pago;

/**
 * Contrato interno de ShopMaster para cobrar en pesos colombianos.
 * Todo el sistema de la tienda habla con esta interfaz.
 */
public interface ProcesadorPago {

    void cobrarEnPesos(String usuario, double montoCOP);
}
