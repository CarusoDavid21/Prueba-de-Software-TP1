package test;

import modelo.Cuenta;
import modelo.TarjetaConcreta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class TarjetaUnitTest {

    private TarjetaConcreta tarjeta;
    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        tarjeta = new TarjetaConcreta("1234 5678 9012 3456", "Juan Pérez", new Date(2025, 10, 12));
        cuenta = new Cuenta("1234567890", "Juan Pérez");
        tarjeta.setCuenta(cuenta);
    }

    @Test
    void testSetCuenta() {
        // Verificamos que la cuenta asociada fue correctamente asignada
        assertNotNull(tarjeta.getCuentaAsociada());
        assertEquals(cuenta, tarjeta.getCuentaAsociada());
    }

    @Test
    void testNumeroTitularFecha() {
        // Verificamos que los datos de número, titular y fecha son correctos
        assertEquals("1234 5678 9012 3456", tarjeta.getNumero());
        assertEquals("Juan Pérez", tarjeta.getTitular());
        assertEquals(new Date(2025, 10, 12), tarjeta.getFechaDeCaducidad());
    }

    @Test
    void testRetirar() {
        // Verificamos que el método retirar no arroja ninguna excepción (implementación vacía)
        assertDoesNotThrow(() -> tarjeta.retirar(100.0));
    }

    @Test
    void testIngresar() {
        // Verificamos que el método ingresar no arroja ninguna excepción (implementación vacía)
        assertDoesNotThrow(() -> tarjeta.ingresar(100.0));
    }

    @Test
    void testPagoEnEstablecimiento() {
        // Verificamos que el método pagoEnEstablecimiento no arroja ninguna excepción (implementación vacía)
        assertDoesNotThrow(() -> tarjeta.pagoEnEstablecimiento("Tienda", 50.0));
    }

    @Test
    void testGetSaldo() {
        // Verificamos que el saldo devuelto es correcto según la implementación
        assertEquals(0.0, tarjeta.getSaldo());
    }
}
