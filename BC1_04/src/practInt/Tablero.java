package practInt;

import java.util.*;

public class Tablero {

	int rows, cols;
	Celda [][] tablero;


	public Tablero(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		tablero = new Celda [rows][cols];
	}
	
	public Tablero() {
		
	}

	public void crearTablero(int rows, int cols) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				int [] ids = new int [2];
				tablero [i][j] = new Celda();
				ids[0]=i;
				ids[1]=j;
				tablero[i][j].setIdCelda(ids);
			}
		}
	}
	
	public void pintarTablero(int rows, int cols) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				System.out.print(tablero[i][j]+" ");	
			}
			System.out.println();
		}
	}
	
	public static void actualizarNoVisitadas(Tablero tablero , ArrayList <int[]> noVisitadas) {
		for(int i=0; i<tablero.getrows(); i++) {
			for(int j=0; j<tablero.getcols(); j++) {
				if(tablero.tablero[i][j].isVisitado()==false)
					noVisitadas.add(tablero.tablero[i][j].getIdCelda());
			}
		}
		
						
		// DE ESTE IDCELDA TENDREMOS QUE COGER LA POSICION (I,J) PARA EMPEZAR DESDE AHÍ
	}
	
	
	public int getrows() {
		return rows;
	}

	public void setrows(int rows) {
		this.rows = rows;
	}

	public int getcols() {
		return cols;
	}

	public void setcols(int cols) {
		this.cols = cols;
	}

	public Celda[][] getTablero() {
		return tablero;
	}

	public void setTablero(Celda[][] tablero) {
		this.tablero = tablero;
	}
	
	@Override
	public String toString() {
		return "Tablero [rows=" + rows + ", cols=" + cols + ", tablero=" + Arrays.toString(tablero) + "]";
	}



	
}
