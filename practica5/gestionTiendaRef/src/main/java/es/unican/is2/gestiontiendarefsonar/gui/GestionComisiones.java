package es.unican.is2.gestiontiendarefsonar.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.gestiontiendarefsonar.Tienda;
import es.unican.is2.gestiontiendarefsonar.Vendedor;
import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestión de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// opciones del menu
		final int NUEVA_VENTA = 0;
		final int VENDEDOR_DEL_MES = 1;
		final int VENDEDORES = 2;

		// variables auxiliares
		String dni; 
		Lectura lect; 

		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;

		// crea la tienda
		Tienda tienda = new Tienda("C:\\Temp\\datosTienda.txt");

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Añadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case NUEVA_VENTA:
				lect = new Lectura("Datos Venta");
				lect.creaEntrada("Id Vendedor", "");
				lect.creaEntrada("Importe", "");
				lect.esperaYCierra();
				dni = lect.leeString("Id Vendedor");
				double importe = lect.leeDouble("Importe");
				try {
					if (!tienda.anhadeVenta(dni, importe)) {
						mensaje("ERROR", "El vendedor no existe");
					}
				} catch (IOException e) {
					mensaje("ERROR", "No se pudo guardar el cambio");
				}
				break;

			case VENDEDOR_DEL_MES:

				vendedores = tienda.vendedores();
				resultado = new LinkedList<Vendedor>();
				double maxVentas = 0.0;
				for1(vendedores, resultado, maxVentas);

				StringBuilder bld = new StringBuilder();
				for (Vendedor vn : resultado) {
					bld.append(vn.getNombre());
					bld.append("\n");
				}
				msj = bld.toString();
				mensaje("VENDEDORES DEL MES", msj);
				break;

			
			case VENDEDORES:
	
				vendedores = tienda.vendedores();
				System.out.println(vendedores.size());
				Collections.sort(vendedores, new ComparadorVendedorVentas());	
				StringBuilder bld2 = new StringBuilder();
				for (Vendedor vn : vendedores) {
					bld2.append(vn.getNombre());
					bld2.append(" ");
					bld2.append(vn.getTotalVentas());
					bld2.append("\n");
				}
				msj = bld2.toString();
				mensaje("VENDEDORES", msj);
				break;
			default:
				break;
			}
		}
	}

	private static void for1(List<Vendedor> vendedores, List<Vendedor> resultado, double maxVentas) {
		for (Vendedor v : vendedores) {
			if (v.getTotalVentas() > maxVentas) {
				maxVentas = v.getTotalVentas();
				resultado.clear();
				resultado.add(v);
			} else if (v.getTotalVentas() == maxVentas) {
				resultado.add(v);
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
	
	public static class ComparadorVendedorVentas implements Comparator<Vendedor>  {

		public int compare(Vendedor o1, Vendedor o2) {
			if (o1.getTotalVentas()>o2.getTotalVentas())
				return -1;
			else if (o1.getTotalVentas()<o2.getTotalVentas())
				return 1;
			return 0;
		}
		
	}
	
	
}
