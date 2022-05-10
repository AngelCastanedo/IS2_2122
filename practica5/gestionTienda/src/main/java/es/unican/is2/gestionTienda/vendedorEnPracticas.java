package es.unican.is2.gestionTienda;


public class vendedorEnPracticas extends Vendedor {
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en prácticas
	 * @param nombre
	 * @param dni
	 */
	public vendedorEnPracticas(String nombre, String id, String dni) {   //+1WMC
		super(nombre, id);
		this.dni= dni;
	}
	
	public String getDni() {   //+1WMC
		return dni;
	}

	@Override
	public boolean equals(Object obj) {   //+1WMC
		if (!(obj instanceof vendedorEnPracticas))    //+1WMC 1CCog
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //1CCog
	}
}
