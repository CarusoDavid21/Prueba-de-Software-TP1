package test;

import modelo.Cuenta;
import modelo.Movimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuentaUnitTest {

    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("ES1234567890", "Titular Test");
    }

    @Test
    public void testIngresarDinero() throws Exception {
        cuenta.ingresar(100.0);
        assertEquals(100.0, cuenta.getSaldo(), "El saldo debería ser 100 tras ingresar esa cantidad.");
    }

    @Test
    public void testIngresarDineroNegativoDebeFallar() {
        Exception exception = assertThrows(Exception.class, () -> cuenta.ingresar(-50.0));
        assertEquals("No se puede ingresar una cantidad negativa", exception.getMessage());
    }

    @Test
    public void testRetirarDineroSuficiente() throws Exception {
        cuenta.ingresar(200.0); // Aseguramos que haya saldo suficiente
        cuenta.retirar(100.0);
        assertEquals(100.0, cuenta.getSaldo(), "El saldo debería ser 100 tras retirar 100.");
    }

    @Test
    public void testRetirarDineroInsuficienteDebeFallar() {
        Exception exception = assertThrows(Exception.class, () -> cuenta.retirar(150.0));
        assertEquals("Saldo insuficiente", exception.getMessage());
    }

    @Test
    public void testAddMovimiento() {
        Movimiento mockMovimiento = Mockito.mock(Movimiento.class);
        cuenta.addMovimiento(mockMovimiento);
        
        assertTrue(cuenta.getMovimientos().contains(mockMovimiento), "El movimiento debería estar registrado en la cuenta.");
    }
}
