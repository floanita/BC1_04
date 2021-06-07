package practInt;

import java.util.*;

public class Visitados {
	
	ArrayList<int[]> conjuntoEstados;
	
	public Visitados() {
		
		conjuntoEstados = new ArrayList<int[]>();
	}

	

	public ArrayList<int[]> getConjuntoEstados() {
		return conjuntoEstados;
	}

	public void setConjuntoEstados(ArrayList<int[]> conjuntoEstados) {
		this.conjuntoEstados = conjuntoEstados;
	}

	public void crearVacio() {
		conjuntoEstados.clear();
	}
	
	public void insertarEstado (int[] estado) {
		conjuntoEstados.add(estado);
	}
	
	public boolean perteneceEstado (int[] estado) {
		boolean pertenece = false;
		for (int i=0; i<conjuntoEstados.size(); i++) {
			int[] elemento = conjuntoEstados.get(i);
			if (estado[0]==elemento[0] &&estado[1]==elemento[1]) {
				pertenece = true;
			}
		}
		return pertenece;
	}
	
	
}
