package practInt;

import java.util.Arrays;

public class Sucesores {

	
	int movimiento;
	int [] estado;
	double coste;

	public Sucesores(int movimiento,  int[] estado, double coste) {
		this.movimiento = movimiento;
		this.estado = estado;
		this.coste = coste;
	}
	
	public Sucesores() {
		
	}
	
	public int getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(char movimiento) {
		this.movimiento = movimiento;
	}

	public int[] getEstado() {
		return estado;
	}
	public void setEstado(int[] estado) {
		this.estado = estado;
	}
	
	public double getCoste() {
		return coste;
	}
	public void setCoste(int coste) {
		this.coste = coste;
	}

	@Override
	public String toString() {
		return "('" + movimiento + "', " + Arrays.toString(estado) + ", " + coste + ")";
	}
	
	
}
