package es.unican.is2.gestiontiendarefsonar;


/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private static final double SENIOR_PLUS = 0.01;
	private static final double JUNIOR_PLUS = 0.005;
	private String id;
	private String nombre;
	
	
	
	// Valor total de las ventas mensuales realizadas por el vendedor
	private double t;
	private String dni;
	
	protected Vendedor(String nombre, String id, String dni) {   //+1WMC
		this.nombre = nombre;
		this.id = id;
		this.dni =dni;
	}
	

	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {   //+1WMC
		return nombre;
	}
	
	/**
	 * Retorna el dni del vendedor
	 * @return dni
	 */
	public String getId() {   //+1WMC
		return id;
	}
	
	
	/**
	 * Retorna el total de ventas acumuladas por el vendedor
	 * @return Total de ventas
	 */
	public double getTotalVentas() {   //+1WMC
		return t;
	}
	
	/**
	 * Asigna el total de ventas acumuladas por el vendedor
	 * Se utiliza para poder cargar los datos desde fichero
	 * @param Total de ventas
	 */
	public void setT(double totalVentas) {   //+1WMC
		this.t = totalVentas;
	}
	
	/**
	 * Anhade una nueva venta al vendedor, actualizando su comision
	 * @param importe de la venta
	 */
	public void anhade(double importe){   //+1WMC
		t += importe;
	}


	double switchAnhade(double importeFinal) {  //+1WMC
		if(((VendedorEnPlantilla)this).tipo().equals(TipoVendedor.JUNIOR)){
			importeFinal += importeFinal * JUNIOR_PLUS;
		} else {
			importeFinal += importeFinal * SENIOR_PLUS;
		}
		return importeFinal;
	}


	public String getDni() {   //+1WMC
		return dni;
	}


	
	
}
