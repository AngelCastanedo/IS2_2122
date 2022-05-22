package es.unican.is2.gestiontiendarefsonar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que representa una tienda con un conjunto de vendedores.
 * Gestiona las ventas realizadas y las comisiones asignadas a cada
 * vendedor. Los datos de la tienda se almacenan en un fichero de texto
 * que se pasa como parámetro al crear la tienda
 */
public class Tienda {

	private static final String NOMBRE2 = "  Nombre: ";
	private static final String ID = " Id: ";
	private static final String DNI = " DNI: ";
	private static final String TOTAL_VENTAS_MES = " TotalVentasMes: ";
	private static final String PRACTICAS = "Prácticas";
	private static final String JUNIOR = "Junior";
	private LinkedList<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {   //+1WMC
		this.datos = datos;
	}

	/**
	 * Retorna la dirección de la tienda
	 * @return Dirección de la tienda
	 */
	public String direccion() {   //+1WMC
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {   //+1WMC
		return nombre;
	}

	/**
	 * Añade un nuevo vendedor a la tienda
	 * 
	 * @param nuevoVendedor
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya había un vendedor con el mismo id
	 */
	public boolean anhade(Vendedor nuevoVendedor) throws IOException {   //+1WMC
		Vendedor v = buscaVendedor(nuevoVendedor.getId());
		if (v != null) {   //+1WMC +1CCog
			return false;
		}
		lista.add(nuevoVendedor);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo dni se pasa como parámetro
	 * 
	 * @param id
	 * @return true si se elimina el vendedor 
	 *         false si no existe ningún vendedor con el id indicado
	 */
	public boolean eliminaVendedor(String id) throws IOException {   //+1WMC
		Vendedor v = buscaVendedor(id);
		if (v == null) {   //+1WMC +1CCog
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Añade una venta a un vendedor
	 * @param id Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se añade la venta 
	 *         false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws IOException {   //+1WMC
		Vendedor v = buscaVendedor(id);
		if (v == null) {   //+1WMC +1CCog
			return false;
		}
		double importeFinal = importe;
		if (v instanceof VendedorEnPlantilla) {   //+1WMC +1CCog
			importeFinal = v.switchAnhade(importeFinal);
		}
		v.anhade(importeFinal);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese id o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) {   //+1WMC

		lista = new LinkedList<Vendedor>();
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals(JUNIOR)) {   //+2WMC +2CCog

				String nom = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nom, idIn, dni, TipoVendedor.SENIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals(PRACTICAS)) {   //+2WMC +2CCog
				String nom = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nom, idIn, dni, TipoVendedor.JUNIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			while (in.hasNext()) {   //+1WMC +1CCog
				in.next();
				String nom = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nom, idIn, dni);
				ven.setT(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {   //+1CCog
		} finally {
			if (in != null) {   //+1WMC +1CCog
				in.close();
			}
		} // try

		for (Vendedor v : lista) {   //+1WMC +1CCog
			if (v.getId().equals(id)) {   //+1WMC +2CCog
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() {   //+1WMC
		lista = new LinkedList<Vendedor>();

		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals(JUNIOR)) {   //+2WMC +2CCog

				String nom = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nom, idIn, dni, TipoVendedor.SENIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals(PRACTICAS)) {   //+2WMC +2CCog
				String nom = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nom, idIn, dni, TipoVendedor.JUNIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			while (in.hasNext()) {   //+1WMC +1CCog
				in.next();
				String nom = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPracticas(nom, idIn, dni);
				ven.setT(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) { //+1CCog

		} finally {
			if (in != null) {   //+1WMC +1CCog
				in.close();
			}
		} // try

		return lista;

	}

	/**
	 * Método que actualiza el fichero datosTienda.txt 
	 * con los datos actualizados de los vendedores
	 */
	private void vuelcaDatos() throws IOException {   //+1WMC
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) {   //+1WMC +1CCog
			forVuelcaDatos(senior, junior, practicas, v);
		}

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) {   //+1WMC +1CCog
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println(NOMBRE2 + v1.getNombre() + ID + v1.getId() + DNI+ v1.getDni()+TOTAL_VENTAS_MES
						+ v1.getTotalVentas());
			}
			out.println();
			out.println(JUNIOR);
			for (Vendedor v : junior) {   //+1WMC +1CCog
				VendedorEnPlantilla v2 = (VendedorEnPlantilla) v;
				out.println(NOMBRE2 + v2.getNombre() + ID + v2.getId() + DNI+ v2.getDni()+TOTAL_VENTAS_MES
						+ v2.getTotalVentas());
			}
			out.println();
			out.println(PRACTICAS);
			for (Vendedor v : practicas) {   //+1WMC +1CCog
				Vendedor v3 = v;
				out.println(NOMBRE2 + v3.getNombre() + ID + v3.getId() + DNI+ v3.getDni()+TOTAL_VENTAS_MES
						+ v3.getTotalVentas());
			}

		} finally {
			if (out != null)   //+1WMC +1CCog
				out.close();
		}
	}

	private void forVuelcaDatos(List<Vendedor> senior, List<Vendedor> junior, List<Vendedor> practicas, Vendedor v) {  //+1WMC
		if (v instanceof VendedorEnPracticas) {   //+1WMC +1CCog
			practicas.add(v);
		} else if (v instanceof VendedorEnPlantilla) {   //+2WMC +1CCog
			VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
			anhadeVuelcaDatos(senior, junior, vp);
		}
	}

	private void anhadeVuelcaDatos(List<Vendedor> senior, List<Vendedor> junior, VendedorEnPlantilla vp) { //+1WMC
		if (vp.tipo().equals(TipoVendedor.JUNIOR))   //+1WMC +1CCog
			junior.add(vp);
		else   //+1WMC +1CCog
			senior.add(vp);
	}

}
