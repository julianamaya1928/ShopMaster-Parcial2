package com.shopmaster.catalogo;

/**
 * Hoja del Composite: producto individual del catalogo.
 */
public class Producto implements ComponenteCatalogo {

    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void mostrarDesglose(int nivel) {
        System.out.println(sangria(nivel) + "- " + nombre + "  $" + (long) precio);
    }

    private String sangria(int nivel) {
        String texto = "";
        for (int i = 0; i < nivel; i++) {
            texto = texto + "    ";
        }
        return texto;
    }
}
