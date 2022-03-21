package es.unican.is2.ImpuestoCirculacionCommon;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;

    public Motocicleta(String string, LocalDate minusDays, int i) {
		super(string, minusDays);
		cilindrada = i;
	}


	/**
     * Retorna la cilindrada de la motocicleta
     * @return cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }
    
  
	@Override
    public double precioImpuesto() {
		if(getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25))) {
			return 0;
		}
		if(cilindrada <= 125) {
			return 8.84;
		} else if (cilindrada <= 250) {
			return 15.14;
		} else if (cilindrada <= 500) {
			return 30.3;
		} else if (cilindrada <= 1000) {
			return 60.58;
		}
		return 121.16;
    }
}
