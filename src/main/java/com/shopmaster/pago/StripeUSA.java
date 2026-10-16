package com.shopmaster.pago;

/**
 * Pasarela externa de Estados Unidos (simulada).
 * Viene "empaquetada" por un proveedor: NO se debe modificar.
 * Su API es distinta y esta en ingles / dolares.
 */
public class StripeUSA {

    public void chargeInDollars(double amountUSD, String customerEmail, String merchantKey) {
        System.out.println("[StripeUSA] Charging $" + amountUSD + " USD");
        System.out.println("[StripeUSA] Customer: " + customerEmail);
        System.out.println("[StripeUSA] Merchant key: " + merchantKey);
        System.out.println("[StripeUSA] Payment OK");
    }
}
