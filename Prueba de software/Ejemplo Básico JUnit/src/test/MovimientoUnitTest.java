package test;

import modelo.Movimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class MovimientoUnitTest {

    private Movimiento movimiento;

    @BeforeEach
    void setUp() {
        // Inicializamos un nuevo movimiento antes de cada prueba
        movimiento = new Movimiento();
    }

    @Test
    void testGetSetImporte() {
        // Establecemos un importe
        movimiento.setImporte(150.0);
        // Verificamos que el importe devuelto sea correcto
        assertEquals(150.0, movimiento.getImporte());
    }

    @Test
    void testGetSetConcepto() {
        // Establecemos un concepto
        String concepto = "Pago de servicio";
        movimiento.setConcepto(concepto);
        // Verificamos que el concepto devuelto sea correcto
        assertEquals(concepto, movimiento.getConcepto());
    }

    @Test
    void testGetSetFecha() {
        // Establecemos una fecha específica
        Date fecha = new Date(2024, 10, 10);  // Fecha de prueba
        movimiento.setFecha(fecha);
        // Verificamos que la fecha devuelta sea correcta
        assertEquals(fecha, movimiento.getFecha());
    }

    @Test
    void testFechaPorDefecto() {
        // Verificamos que el constructor de Movimiento establezca la fecha por defecto a la actual
        Date ahora = new Date();
        assertNotNull(movimiento.getFecha());
        assertTrue(movimiento.getFecha().before(ahora) || movimiento.getFecha().equals(ahora));
    }

    @Test
    void testModificarImporte() {
        // Modificamos el importe varias veces y verificamos que los cambios se reflejan
        movimiento.setImporte(50.0);
        assertEquals(50.0, movimiento.getImporte());
        movimiento.setImporte(200.0);
        assertEquals(200.0, movimiento.getImporte());
    }
}
