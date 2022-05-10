package es.unican.is2.gestionTienda;


public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {   //+1WMC
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	
	public TipoVendedor tipo() {   //+1WMC
		return tipo;
	}
	
	public String getDni() {   //+1WMC
		return dni;
	}
	
	@Override
	public boolean equals(Object obj) {   //+1WMC
		if (!(obj instanceof VendedorEnPlantilla))    //+1WMC 1CCog
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //1CCog
	}
}
