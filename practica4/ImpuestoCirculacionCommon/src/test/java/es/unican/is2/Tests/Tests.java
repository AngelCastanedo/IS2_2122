package es.unican.is2.Tests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import es.unican.is2.ImpuestoCirculacionCommon.Furgoneta;
import es.unican.is2.ImpuestoCirculacionCommon.Motocicleta;
import es.unican.is2.ImpuestoCirculacionCommon.Turismo;
import es.unican.is2.ImpuestoCirculacionCommon.Vehiculo;

public class Tests {
private Vehiculo vehiculo;
	
	
	
	@Test
	public void testTurismo() {
		vehiculo = new Turismo("1234CCC", LocalDate.now(), 7);
		assertTrue(vehiculo.precioImpuesto() == 25.24);
		
		vehiculo = new Turismo("1235CCC", LocalDate.now(), 11);
		assertTrue(vehiculo.precioImpuesto() == 68.16);
		
		vehiculo = new Turismo("1234CCC", LocalDate.now(), 14);
		assertTrue(vehiculo.precioImpuesto() == 143.88);
		
		vehiculo = new Turismo("1234CCC", LocalDate.now(), 18);
		assertTrue(vehiculo.precioImpuesto() == 179.22);
		
		vehiculo = new Turismo("1234CCC", LocalDate.now(), 20);
		assertTrue(vehiculo.precioImpuesto() == 224);
		
		vehiculo = new Turismo("1234CCC", LocalDate.of(1950, 12, 12),10);
		assertTrue(vehiculo.precioImpuesto() == 0);
	}
	
	@Test
	public void testFurgoneta() {
		vehiculo = new Furgoneta("1234CCC", LocalDate.now(), 7);
		assertTrue(vehiculo.precioImpuesto() == 25.24);
		
		vehiculo = new Furgoneta("1235CCC", LocalDate.now(), 11);
		assertTrue(vehiculo.precioImpuesto() == 68.16);
		
		vehiculo = new Furgoneta("1234CCC", LocalDate.now(), 14);
		assertTrue(vehiculo.precioImpuesto() == 143.88);
		
		vehiculo = new Furgoneta("1234CCC", LocalDate.now(), 18);
		assertTrue(vehiculo.precioImpuesto() == 179.22);
		
		vehiculo = new Furgoneta("1234CCC", LocalDate.now(), 20);
		assertTrue(vehiculo.precioImpuesto() == 224);
		
		vehiculo = new Furgoneta("1234CCC", LocalDate.of(1950, 12, 12),10);
		assertTrue(vehiculo.precioImpuesto() == 0);

	}
	
	@Test
	public void testMotocicleta() {
		vehiculo = new Motocicleta("1234CCC", LocalDate.now(), 124);
		assertTrue(vehiculo.precioImpuesto() == 8.84);
		
		vehiculo = new Motocicleta("1235CCC", LocalDate.now(), 249);
		assertTrue(vehiculo.precioImpuesto() == 15.14);
		
		vehiculo = new Motocicleta("1234CCC", LocalDate.now(), 499);
		assertTrue(vehiculo.precioImpuesto() == 30.30);
		
		vehiculo = new Motocicleta("1234CCC", LocalDate.now(), 700);
		assertTrue(vehiculo.precioImpuesto() == 60.58);
		
		vehiculo = new Motocicleta("1234CCC", LocalDate.now(), 1909);
		assertTrue(vehiculo.precioImpuesto() == 121.16);
		
		vehiculo = new Motocicleta("1234CCC", LocalDate.of(1950, 12, 12),856);
		assertTrue(vehiculo.precioImpuesto() == 0);
	}
	
}

