package modelo;

import java.util.Date;

public class TarjetaConcreta extends Tarjeta {

    public TarjetaConcreta(String numero, String titular, Date fechaCaducidad) {
        super(numero, titular, fechaCaducidad);
    }

    @Override
    public void retirar(double x) {
        // Implementación de prueba
    }

    @Override
    public void ingresar(double x) {
        // Implementación de prueba
    }

    @Override
    public void pagoEnEstablecimiento(String datos, double x) {
        // Implementación de prueba
    }

    @Override
    public double getSaldo() {
        return 0;  // Implementación de prueba
    }
}
