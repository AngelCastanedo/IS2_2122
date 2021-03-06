package es.unican.is2.ImpuestoCirculacionMain;
import es.unican.is2.ImpuestoCirculacionBusiness.*;
import es.unican.is2.ImpuestoCirculacionCommon.*;
import es.unican.is2.ImpuestoCirculacionDAO.*;
import es.unican.is2.ImpuestoCirculacionGUI.*;

/**
 * Clase principal que construye la aplicación de tres capas y lanza su ejecución
 */
public class Runner {

	public static void main(String[] args) {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		// Componentes casa presentacion
		VistaFuncionario vista = new VistaFuncionario(negocio, negocio, negocio);
		
		// Lanza ejecución
		vista.setVisible(true);
	}

}
