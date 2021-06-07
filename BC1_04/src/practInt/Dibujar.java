package practInt;
import java.util.*;

public class Dibujar {
	private int cols; 
	private int filas;
	private boolean[][] norte; 
	private boolean[][] este;
	private boolean[][] sur;
	private boolean[][] oeste;

	public Dibujar(int cols, int filas, Tablero tab) {
		this.cols = cols;
		this.filas = filas;
		StdDraw.setXscale(0, cols + 2);
		StdDraw.setYscale(0, filas + 2);
		inicializar();

	}

	public Dibujar() {
		super();
	}

	private void inicializar() {
		norte = new boolean[cols + 2][filas + 2];
		este = new boolean[cols + 2][filas + 2];
		sur = new boolean[cols + 2][filas + 2];
		oeste = new boolean[cols + 2][filas + 2];
		for (int x = 0; x < cols + 2; x++)
			for (int y = 0; y < filas + 2; y++)
				norte[x][y] = este[x][y] = sur[x][y] = oeste[x][y] = true;
	}

	public void borrar(int x, int y, char d) {

		if ((d == 'N') && (y < filas)) {

			norte[x][y] = sur[x][y + 1] = false;
		} else if ((d == 'O') && (x > 1)) {

			oeste[x][y] = este[x - 1][y] = false;
		} else if ((d == 'S') && (y > 1)) {

			sur[x][y] = norte[x][y - 1] = false;
		} else if ((d == 'E') && (x < cols)) {

			este[x][y] = oeste[x + 1][y] = false;
		}
	}

	//VALORES DE TIERRA, ASFALTO, HIERBA Y AGUA
	public void colorear(int x, int y, int valor) {
		if (valor==0) {
			StdDraw.setPenColor(155, 155, 155);
			StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
		}
		if (valor == 1) {
			StdDraw.setPenColor(78, 59, 49);
			StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
		}
		if (valor == 2) {
			StdDraw.setPenColor(53, 104, 45);
			StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
		}
		if (valor == 3) {
			StdDraw.setPenColor(0, 170, 228);
			StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.007);

	}

	public void colorearSolucion(int x, int y, int valor, ArrayList<Nodo> frontera, ArrayList<Nodo> solucion,
			ArrayList<Nodo> escogidos, int rows, int cols) {
		
		colorear(x,y,valor);

		for (int i = 0; i < frontera.size(); i++) {
			if (frontera.get(i).getEstado()[0] == rows && frontera.get(i).getEstado()[1] == cols) {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);

			}
		}

		for (int i = 0; i < escogidos.size(); i++) {
			if (escogidos.get(i).getEstado()[0] == rows && escogidos.get(i).getEstado()[1] == cols) {
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);

			}
		}

		for (int i = 0; i < solucion.size(); i++) {
			if (solucion.get(i).getEstado()[0] == rows && solucion.get(i).getEstado()[1] == cols) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);

			}
		}

		StdDraw.setPenColor(StdDraw.BLACK);

	}

	public void dibujar() {
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int x = 1; x <= cols; x++) {
			for (int y = 1; y <= filas; y++) {
				if (sur[x][y])
					StdDraw.line(x, y, x + 1, y);
				if (norte[x][y])
					StdDraw.line(x, y + 1, x + 1, y + 1);
				if (oeste[x][y])
					StdDraw.line(x, y, x, y + 1);
				if (este[x][y])
					StdDraw.line(x + 1, y, x + 1, y + 1);
			}
		}
		StdDraw.show(0);
	}

	public void dibujar(Tablero tab, int filas, int cols) {
		
		Dibujar miLaberinto = new Dibujar(cols, filas, tab);
		int contador = 1;
		int filas_lab = filas - 1;
		StdDraw.show(0);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < cols; j++) {
				//miLaberinto.colorear((j + 1), contador, lab.getCeldas()[filas_lab][j].getValor());
				

				if (tab.getTablero()[filas_lab][j].getNeighbors()[0] == true) {
					miLaberinto.borrar((j + 1), contador, 'N');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[1] == true) {
					miLaberinto.borrar((j + 1), contador, 'E');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[2] == true) {
					miLaberinto.borrar((j + 1), contador, 'S');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[3] == true) {
					miLaberinto.borrar((j + 1), contador, 'O');
				}
			}

			filas_lab = filas_lab - 1;
			contador++;
		}

		miLaberinto.dibujar();
	}
	
	public void pintar(Tablero tab, int filas, int cols) {
		
		Dibujar miLaberinto = new Dibujar(cols, filas, tab);
		int contador = 1;
		int filas_lab =filas - 1;
		StdDraw.show(0);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < cols; j++) {
				miLaberinto.colorear((j + 1), contador, tab.getTablero()[filas_lab][j].getValue());
				

				if (tab.getTablero()[filas_lab][j].getNeighbors()[0] == true) {
					miLaberinto.borrar((j + 1), contador, 'N');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[1] == true) {
					miLaberinto.borrar((j + 1), contador, 'E');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[2] == true) {
					miLaberinto.borrar((j + 1), contador, 'S');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[3] == true) {
					miLaberinto.borrar((j + 1), contador, 'O');
				}
			}

			filas_lab = filas_lab - 1;
			contador++;
		}

		miLaberinto.dibujar();
	}


	public void solucion(Tablero tab, int filas, int cols, ArrayList<Nodo> front, ArrayList<Nodo> solucion,
			ArrayList<Nodo> escogidos) {

		
		Dibujar miLaberinto = new Dibujar(cols, filas, tab);
		int contador = 1;
		int filas_lab = filas - 1;
		StdDraw.show(0);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < cols; j++) {
				
				miLaberinto.colorearSolucion((j + 1), contador, tab.getTablero()[filas_lab][j].getValue(), front, solucion, escogidos, filas_lab, j);

				if (tab.getTablero()[filas_lab][j].getNeighbors()[0] == true) {
					miLaberinto.borrar((j + 1), contador, 'N');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[1] == true) {
					miLaberinto.borrar((j + 1), contador, 'E');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[2] == true) {
					miLaberinto.borrar((j + 1), contador, 'S');
				}
				if (tab.getTablero()[filas_lab][j].getNeighbors()[3] == true) {
					miLaberinto.borrar((j + 1), contador, 'O');
				}
			}

			filas_lab = filas_lab - 1;
			contador++;
		}

		miLaberinto.dibujar();
	}
}