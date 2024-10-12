package test;

import java.util.Iterator;
import java.util.Vector;

import modelo.Cuenta;
import modelo.Movimiento;

import org.junit.Before;
import org.junit.Test;
//Para acceder a estos assert mas especificos escribir assert. y el IDE ofrecerá metodos mas complejos
import static org.junit.Assert.*;

public class CuentaTest 
{
	private Cuenta cuenta;
	private Vector movimientos;

	@Before
	public void setUp() throws Exception
	{
		cuenta = new Cuenta("20356298-33", "García Enrique");
		movimientos=null;
	}

	@Test
	public void testIngresar() 
	{
		double saldoAnterior = cuenta.getSaldo();
		try {
			cuenta.ingresar(1000);
			assertTrue(cuenta.getSaldo()==saldoAnterior+1000.0);		
			
		}
		catch (Exception e) 
		{
			fail("No debería haber fallado");
		}
	}
	
	@Test
	public void testIngresarMontoNegativo() 
	{
		double saldoAnterior = cuenta.getSaldo();
		try {
			cuenta.ingresar(-1000);
		}
		catch (Exception e) 
		{
			assertTrue("Fallo - Permitio ingresar importe negativo",cuenta.getSaldo()==saldoAnterior);
		}
	}
	
	@Test
	public void testNoPermiteRetirarConFondosInsuficientes() 
	{
		double saldoAnterior =cuenta.getSaldo();
		try 
		{
			cuenta.retirar(1000);
		}
		catch (Exception e) 
		{
			assertTrue("Permitio retirar habiendo fondos insuficientes",saldoAnterior==cuenta.getSaldo());
		}
		
	}
	
	@Test
	public void testIngresosGeneranMovimientos() 
	{
		Movimiento mov1=null;
		Movimiento mov2=null;
		Movimiento mov3=null;
		
		try 
		{
			cuenta.ingresar(500.00);
			cuenta.ingresar(1000.00);
			cuenta.retirar(1000);
			
			movimientos = cuenta.getMovimientos();
			
			mov1= (Movimiento) movimientos.get(0);
			mov2= (Movimiento) movimientos.get(1);
			mov3= (Movimiento) movimientos.get(2);
			
			assertTrue(mov1.getImporte() == 500);
			assertTrue(mov2.getImporte() == 1000);
			assertTrue(mov3.getImporte() == -1000);
		}
		catch (Exception e) 
		{
			fail("Error al ingresar importe");
		}
		
	}
	
	
}