package test;

import modelo.Credito;
import modelo.Cuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditoUnitTest {

    private Credito credito;
    private Cuenta cuentaMock;

    @BeforeEach
    void setUp() {
        cuentaMock = Mockito.mock(Cuenta.class);
        credito = new Credito("123456789", "Titular Prueba", new Date(), 4000.0);
        credito.setCuenta(cuentaMock);
    }

    @Test
    void testRetirar_Success() throws Exception {
        // Mockear getSaldo() para evitar problemas con el saldo
        when(cuentaMock.getSaldo()).thenReturn(5000.0);

        // Verificar que no lanza excepción cuando hay crédito disponible
        credito.retirar(1000);

        // Verificar que el movimiento fue registrado
        assertEquals(1, credito.getMovimientos().size());
    }

    @Test
    void testRetirar_CreditoInsuficiente() {
        when(cuentaMock.getSaldo()).thenReturn(100.0);

        Exception exception = assertThrows(Exception.class, () -> credito.retirar(1000));
        
        // Si no es necesario validar el mensaje, puedes omitir esta línea
        assertTrue(exception.getMessage().contains("Crédito insuficiente"));
    }


    @Test
    void testIngresar() throws Exception {
        // Test para ingresar dinero en la cuenta
        credito.ingresar(500.0);

        // Verificamos que el movimiento fue agregado
        assertEquals(1, credito.getMovimientos().size());

        // Verificamos que se llamó al método ingresar de la cuenta asociada
        verify(cuentaMock, times(1)).ingresar(500.0);
    }

    @Test
    void testPagoEnEstablecimiento() throws Exception {
        // Test para simular un pago en un establecimiento
        credito.pagoEnEstablecimiento("Supermercado", 100.0);

        // Verificamos que el movimiento fue agregado
        assertEquals(1, credito.getMovimientos().size());
    }

    @Test
    void testGetCreditoDisponible() {
        when(cuentaMock.getSaldo()).thenReturn(1000.0);
        assertEquals(4000.0, credito.getCreditoDisponible());

        when(cuentaMock.getSaldo()).thenReturn(3000.0);
        assertEquals(2000.0, credito.getCreditoDisponible());
    }

}

