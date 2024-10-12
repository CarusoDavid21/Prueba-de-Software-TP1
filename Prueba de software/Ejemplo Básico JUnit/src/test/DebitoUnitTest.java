package test;

import modelo.Cuenta;
import modelo.Debito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

class DebitoUnitTest {

    private Debito tarjetaDebito;
    private Cuenta cuentaMock;

    @BeforeEach
    void setUp() {
        // Mockeamos una cuenta asociada
        cuentaMock = mock(Cuenta.class);
        // Creamos la tarjeta de débito y le asignamos la cuenta mockeada
        tarjetaDebito = new Debito("123456789", "Titular de prueba", new Date());
        tarjetaDebito.setCuenta(cuentaMock);
    }

    @Test
    void testRetirar() throws Exception {
        // Simulamos que la cuenta tiene saldo suficiente
        doNothing().when(cuentaMock).retirar(anyString(), anyDouble());
        
        // Realizamos una operación de retiro
        tarjetaDebito.retirar(100);
        
        // Verificamos que se haya llamado al método retirar de la cuenta asociada
        verify(cuentaMock).retirar(eq("Retirada en cajero automático"), eq(100.0));
    }

    @Test
    void testIngresar() throws Exception {
        // Simulamos que la cuenta recibe el ingreso
        doNothing().when(cuentaMock).ingresar(anyString(), anyDouble());
        
        // Realizamos una operación de ingreso
        tarjetaDebito.ingresar(50);
        
        // Verificamos que se haya llamado al método ingresar de la cuenta asociada
        verify(cuentaMock).retirar(eq("Ingreso en cajero automático"), eq(50.0));
    }

    @Test
    void testPagoEnEstablecimiento() throws Exception {
        // Simulamos que la cuenta tiene saldo suficiente para un pago
        doNothing().when(cuentaMock).retirar(anyString(), anyDouble());
        
        // Realizamos un pago en un establecimiento
        tarjetaDebito.pagoEnEstablecimiento("Tienda de prueba", 75);
        
        // Verificamos que se haya llamado al método retirar de la cuenta asociada
        verify(cuentaMock).retirar(eq("Compra en :Tienda de prueba"), eq(75.0));
    }

    @Test
    void testGetSaldo() {
        // Simulamos que la cuenta tiene un saldo de 500
        when(cuentaMock.getSaldo()).thenReturn(500.0);
        
        // Verificamos que el saldo devuelto sea el esperado
        assertEquals(500.0, tarjetaDebito.getSaldo());
    }

    @Test
    void testSaldoInsuficiente() throws Exception {
        // Simulamos que no hay saldo suficiente para una operación
        doThrow(new Exception("Saldo insuficiente")).when(cuentaMock).retirar(anyString(), eq(200.0));
        
        // Verificamos que se lance la excepción cuando se intenta retirar más del saldo disponible
        Exception exception = assertThrows(Exception.class, () -> tarjetaDebito.retirar(200));
        assertEquals("Saldo insuficiente", exception.getMessage());
    }
}
