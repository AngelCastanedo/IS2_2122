package es.unican.is2.gestiontiendarefsonar;


public class VendedorEnPracticas extends Vendedor {
	
	/**
	 * Retorna un nuevo vendedor en prácticas
	 * @param nombre
	 * @param dni
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) {   //+1WMC
		super(nombre, id, dni);
	}
	
	@Override
	public boolean equals(Object obj) {   //+1WMC
		if (!(obj instanceof VendedorEnPracticas))    //+1WMC
			return false;
		VendedorEnPracticas v = (VendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //+1CCog 
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
}
