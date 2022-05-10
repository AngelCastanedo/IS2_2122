package es.unican.is2.gestionTiendaRef;


public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {   //+1WMC
		super(nombre, id, dni);
		this.tipo = tipo;
	}
	
	public TipoVendedor tipo() {   //+1WMC
		return tipo;
	}
	
	
	@Override
	public boolean equals(Object obj) {   //+1WMC
		if (!(obj instanceof VendedorEnPlantilla))    //+1WMC
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //+1CCog
	}
}
