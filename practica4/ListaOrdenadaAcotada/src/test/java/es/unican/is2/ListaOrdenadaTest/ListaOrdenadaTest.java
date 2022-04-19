package es.unican.is2.ListaOrdenadaTest;

import static org.junit.Assert.assertTrue;


import org.junit.Test;

import es.unican.is2.ListaOrdenadaAcotada.ListaOrdenadaAcotada;


public class ListaOrdenadaTest {
	ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada(4);
	ListaOrdenadaAcotada<Integer> listaMax = new ListaOrdenadaAcotada();	
	@Test
	public void test() {
		listaMax.add(12);
		lista.add(1);
		assertTrue(lista.get(0) == 1);
		assertTrue(lista.size() == 1);
		lista.add(3);
		assertTrue(lista.size() == 2);
		lista.add(2);
		assertTrue(lista.size() == 3);
		
		lista.add(0);
		try {
			lista.add(4);
		} catch (Exception e) {
			
		}
		assertTrue(lista.remove(0) == 0);
		lista.clear();
		
		try {
			lista.remove(1);
		} catch (Exception e) {
			
		}
	}
}
