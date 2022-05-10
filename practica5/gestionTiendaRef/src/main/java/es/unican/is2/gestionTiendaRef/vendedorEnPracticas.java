package es.unican.is2.gestionTiendaRef;


public class vendedorEnPracticas extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en prácticas
	 * @param nombre
	 * @param dni
	 */
	public vendedorEnPracticas(String nombre, String id, String dni) {   //+1WMC
		super(nombre, id, dni);
	}
	
	@Override
	public boolean equals(Object obj) {   //+1WMC
		if (!(obj instanceof vendedorEnPracticas))    //+1WMC
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //+1CCog 
	}
}
