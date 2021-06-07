package practInt;

import java.util.ArrayList;
import java.util.Arrays;

public class Problema {

	int[] initial;
	int[] objetive;
	Tablero tablero;
	
	public Problema(int[] initial, int[] objetive, Tablero tablero) {
		this.initial = initial;
		this.objetive = objetive;
		this.tablero = tablero;
	}
	
	public Problema() {
	}

	public int[] getInitial() {
		return initial;
	}

	public void setInitial(int[] inicial) {
		this.initial = inicial;
	}

	public int[] getObjetive() {
		return objetive;
	}

	public void setObjetive(int[] objetivo) {
		this.objetive = objetivo;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public ArrayList<Sucesores> sucesores (int [] estado, Tablero tablero) {
		ArrayList<Sucesores> sucesores = new ArrayList<Sucesores>();
		if (estaDentro(tablero, estado, 'N') && tablero.getTablero()[estado[0]][estado[1]].getNeighbors()[0]) {
			Sucesores sucesorN = new Sucesores();
			int [] estadoN = { estado[0] - 1, estado[1] };
			sucesorN.setMovimiento('N');
			sucesorN.setEstado(estadoN);
			sucesorN.setCoste(tablero.getTablero()[estado[0] - 1][estado[1]].getValue() + 1);
			sucesores.add(sucesorN);
		}
		if (estaDentro(tablero, estado, 'E') && tablero.getTablero()[estado[0]][estado[1]].getNeighbors()[1]) {
			Sucesores sucesorE = new Sucesores();
			int [] estadoE = { estado[0] , estado[1] + 1 };
			sucesorE.setMovimiento('E');
			sucesorE.setEstado(estadoE);
			sucesorE.setCoste(tablero.getTablero()[estado[0]][estado[1] + 1].getValue() + 1);
			sucesores.add(sucesorE);
		}
		if (estaDentro(tablero, estado, 'S') && tablero.getTablero()[estado[0]][estado[1]].getNeighbors()[2]) {
			Sucesores sucesorS = new Sucesores();
			int [] estadoS = { estado[0] + 1 , estado[1] };
			sucesorS.setMovimiento('S');
			sucesorS.setEstado(estadoS);
			sucesorS.setCoste(tablero.getTablero()[estado[0] + 1][estado[1]].getValue() + 1);
			sucesores.add(sucesorS);
		}
		if (estaDentro(tablero, estado, 'O') && tablero.getTablero()[estado[0]][estado[1]].getNeighbors()[3]) {
			Sucesores sucesorO = new Sucesores();
			int [] estadoO = { estado[0], estado[1] - 1};
			sucesorO.setMovimiento('O');
			sucesorO.setEstado(estadoO);
			sucesorO.setCoste(tablero.getTablero()[estado[0]][estado[1] - 1].getValue() + 1);
			sucesores.add(sucesorO);
		}
		
		return sucesores;
	}
	
	public static boolean estaDentro (Tablero tablero, int[] estado, char dir) {
		if (dir=='N' && estado[0] - 1 > -1) {
			return true;
		} else if(dir=='E' && estado[1] + 1 < tablero.getcols()) {
			return true;
		} else if(dir=='S' && estado[0] + 1 < tablero.getrows()) {
			return true;
		} else if (dir == 'O' && estado[1] - 1 > -1) {
			return true;
		} else {
			return false;
		}	
	}
	
	public boolean comprobarObjetivo(int[] estado){
		boolean valor = false;
		if (estado[0]==objetive[0] && estado[1]==objetive[1]) {
			valor=true;
		}
		return valor;
	}
	
	@Override
	public String toString() {
		return "Problema [inicial = " + Arrays.toString(initial) + ", objetivo = " + Arrays.toString(objetive)
				+ ", laberinto = Abra la ventana emergente...]";
	}
}
