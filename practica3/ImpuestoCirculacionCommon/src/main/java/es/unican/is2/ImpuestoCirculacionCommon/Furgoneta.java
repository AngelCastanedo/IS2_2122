package es.unican.is2.ImpuestoCirculacionCommon;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Furgoneta
    extends Turismo implements Serializable
{
    
    private double potencia;
    private boolean comercial;
    
   public Furgoneta(String string, LocalDate minusMonths, double i) {
		super(string, minusMonths, i);
		this.potencia = i;
	}

/**
    * Retorna el valor del atributo comercial
    * @return true si la furgoneta es de uso comercial
    *         false si no es de uso comercial
    */
    public boolean getComercial() {
    	return comercial;
    }
    
    /**
	 * Retorna la potencia de la furgoneta
	 * @return potencia en caballos fiscales
	 */
    public double getPotencia() {
        return potencia;
    }
    
  
	@Override
    public double precioImpuesto() {
		double d = 1;
		if(comercial) {
			d = 0.8;
		}
    	return super.precioImpuesto()*d;
    }
}
