package es.unican.is2.VistaFuncionarioTest;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.ImpuestoCirculacionBusiness.GestionImpuestoCirculacion;
import es.unican.is2.ImpuestoCirculacionDAO.ContribuyentesDAO;
import es.unican.is2.ImpuestoCirculacionDAO.VehiculosDAO;
import es.unican.is2.ImpuestoCirculacionGUI.VistaFuncionario;

public class VistaFuncionarioTest {
private FrameFixture demo;
	
	
	@Before
	public void setUp() {
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		// Componentes casa presentacion
		VistaFuncionario gui = new VistaFuncionario(negocio, negocio, negocio);
		
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	@After
	public void tearDown() {
		demo.cleanUp();
	}
	@Test
	public void test() {
		demo.textBox("txtDniContribuyente").enterText("11111111A");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Pepe López Martínez");
		demo.list("listMatriculasVehiculos").requireItemCount(2);
		demo.textBox("txtTotalContribuyente").requireText("448.0");
		
		demo.textBox("txtDniContribuyente").setText("22222222B");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Ana Pérez López");
		demo.list("listMatriculasVehiculos").requireItemCount(2);
		demo.textBox("txtTotalContribuyente").requireText("8.84");
		
		demo.textBox("txtDniContribuyente").setText("33333333C");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("Luis Toca Pérez");
		demo.list("listMatriculasVehiculos").requireItemCount(2);
		demo.textBox("txtTotalContribuyente").requireText("249.24");
		
		demo.textBox("txtDniContribuyente").setText("3333333C");
		demo.button("btnBuscar").click();
		demo.textBox("txtNombreContribuyente").requireText("DNI No Válido");
		demo.list("listMatriculasVehiculos").requireItemCount(0);
		demo.textBox("txtTotalContribuyente").requireText("0");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
