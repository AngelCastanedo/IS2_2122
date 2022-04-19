package es.unican.is2.ImpuestoCirculacionBusiness;
import es.unican.is2.ImpuestoCirculacionCommon.*;
/**
 * Clase que implementa la capa de negocio de la aplicacion
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
	
	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;
	
	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}
	
	public Contribuyente altaContribuyente(Contribuyente c) {
		if(contribuyentes.contribuyente(c.getDni()) != null) {
			return null;
		}
		return contribuyentes.creaContribuyente(c);
	}

	
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		if(contribuyentes.contribuyente(dni) == null) {
			return null;
		}
		if(!contribuyentes.contribuyente(dni).getVehiculos().isEmpty()) {
			throw new OperacionNoValida("El contribuyente tiene vehiculos a su nombre");
		}
		return contribuyentes.eliminaContribuyente(dni);		
	 }
	
	public Contribuyente contribuyente(String dni) {
		return contribuyentes.contribuyente(dni);
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		if(vehiculos.vehiculo(v.getMatricula()) == null) {
			throw new OperacionNoValida("El vehiculo ya existe");
		}
		if(contribuyentes.contribuyente(dni) == null) {
			return null;
		}
		contribuyentes.contribuyente(dni).getVehiculos().add(v);
		return v;
	}

	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		if(contribuyentes.contribuyente(dni) == null || vehiculos.vehiculo(matricula) == null) {
			return null;
		}
		if(contribuyentes.contribuyente(dni).getVehiculos().contains(vehiculos.vehiculo(matricula))) {
			throw new OperacionNoValida("El vehiculo no pertenece a esa persona");
		}
		return null;
	}

	public Vehiculo vehiculo(String matricula) {
		return vehiculos.vehiculo(matricula);
	}	
}

