package practInt;

import java.util.Arrays;

public class Celda {
	
	boolean [] neighbors = new boolean [4];
	int [] idCelda = new int[2];
	int value;
	boolean visitado;
	
	public Celda(){
		for (int i=0;i<neighbors.length;i++){
			this.neighbors[i] = false;
		}
		this.value = 0; //PESO
		this.visitado = false;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public void setIdCelda(int[] idCelda) {
		this.idCelda = idCelda;
	}

	public int[] getIdCelda() {
		return idCelda;
	}

	public boolean[] getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(boolean[] neighbors) {
		this.neighbors = neighbors;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Celda [neighbors=" + Arrays.toString(neighbors) + ", idCelda=" + Arrays.toString(idCelda) + ", value="
				+ value + ", visitado=" + visitado + "]";
	}


}
