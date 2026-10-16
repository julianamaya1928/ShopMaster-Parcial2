package com.shopmaster.pago;

/**
 * Patron Adapter: hace que StripeUSA se use como un ProcesadorPago local.
 * Convierte pesos a dolares (1 USD = 4000 COP) y adapta los parametros.
 * StripeUSA no se modifica.
 */
public class AdaptadorStripe implements ProcesadorPago {

    public static final double COP_POR_USD = 4000;

    private StripeUSA stripe;
    private String merchantKey;

    public AdaptadorStripe(StripeUSA stripe, String merchantKey) {
        this.stripe = stripe;
        this.merchantKey = merchantKey;
    }

    @Override
    public void cobrarEnPesos(String usuario, double montoCOP) {
        double montoUSD = montoCOP / COP_POR_USD;
        String email = usuario + "@shopmaster.com";

        System.out.println("[AdaptadorStripe] Monto original: $" + (long) montoCOP + " COP");
        System.out.println("[AdaptadorStripe] Convertido: $" + montoUSD + " USD (tasa 1 USD = 4000 COP)");

        stripe.chargeInDollars(montoUSD, email, merchantKey);
    }
}
