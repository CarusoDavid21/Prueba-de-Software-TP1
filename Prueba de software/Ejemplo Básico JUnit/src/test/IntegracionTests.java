package test;

import static org.junit.jupiter.api.Assertions.*;
import modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

class IntegracionTests {

    Cuenta cuenta;
    Credito tarjetaCredito;
    Debito tarjetaDebito;

    @BeforeEach
    void setUp() throws Exception {
    	cuenta = new Cuenta("123456", "Juan Perez");
    	cuenta.ingresar(1000); // Ingresar 1000 como saldo inicial
        tarjetaCredito = new Credito("987654", "Titular Crédito", new Date(), 500);
        tarjetaDebito = new Debito("654321", "Titular Débito", new Date());
        tarjetaCredito.setCuenta(cuenta);
        tarjetaDebito.setCuenta(cuenta);
    }

    @Test
    void testCreditoRetiroConFondosSuficientes() throws Exception {
        tarjetaCredito.retirar(100);
        assertEquals(900, cuenta.getSaldo());
        assertEquals(100, tarjetaCredito.getSaldo());
    }

    @Test
    void testCreditoRetiroConFondosInsuficientes() {
        Exception exception = assertThrows(Exception.class, () -> tarjetaCredito.retirar(1200));
        assertTrue(exception.getMessage().contains("Credito insuficiente"));
    }

    @Test
    void testDebitoRetiroConFondosSuficientes() throws Exception {
        tarjetaDebito.retirar(100);
        assertEquals(900, cuenta.getSaldo());
    }

    @Test
    void testDebitoRetiroConFondosInsuficientes() {
        Exception exception = assertThrows(Exception.class, () -> tarjetaDebito.retirar(1200));
        assertTrue(exception.getMessage().contains("Saldo insuficiente"));
    }

    @Test
    void testPagoCreditoEnEstablecimiento() throws Exception {
        tarjetaCredito.pagoEnEstablecimiento("Tienda ABC", 200);
        assertEquals(200, tarjetaCredito.getSaldo());
        assertEquals(800, cuenta.getSaldo());
    }

    @Test
    void testPagoDebitoEnEstablecimiento() throws Exception {
        tarjetaDebito.pagoEnEstablecimiento("Tienda XYZ", 100);
        assertEquals(900, cuenta.getSaldo());
    }

    @Test
    void testIngresoCredito() throws Exception {
        tarjetaCredito.ingresar(150);
        assertEquals(1150, cuenta.getSaldo());
    }

    @Test
    void testIngresoDebito() throws Exception {
        tarjetaDebito.ingresar(200);
        assertEquals(1200, cuenta.getSaldo());
    }

    @Test
    void testMovimientosCredito() throws Exception {
        tarjetaCredito.retirar(100);
        assertEquals(1, tarjetaCredito.getMovimientos().size());
    }

    @Test
    void testLiquidarCredito() throws Exception {
        tarjetaCredito.retirar(100);
        tarjetaCredito.liquidar(10, 2024);
        assertEquals(1, cuenta.getMovimientos().size());
    }
}
