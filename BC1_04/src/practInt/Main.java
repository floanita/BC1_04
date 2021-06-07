package practInt;

import static practInt.Main.TECLADO;

import java.util.*;

public class Main {

    static Scanner TECLADO = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;        
        System.out.println("Seleccione una opcion");
        System.out.println("Opcion 1: Construir laberinto");
        System.out.println("Opcion 2: Leer JSON");
        System.out.println("Opcion 3: Solucionar enunciado desde archivo");
        System.out.println("Opción 4: Exit");
        opcion = TECLADO.nextInt();

        switch (opcion) {
            case 1:
                generarLaberinto(opcion);                
                break;
            case 2:            	
            	leerLaberinto();            	
                break;
            case 3:
            	leerLaberintoSolucion();
            	break;
            default:
                System.out.println("Finalizando ejecucion...");
                System.exit(0);
        }
    }


	public static void generarLaberinto(int opcion) {
        int rows, cols, aux1 = 0, aux2 = 0;
        Laberinto laberinto = new Laberinto();
        
        ArrayList<Integer> posibilidades = new ArrayList<Integer>();
        ArrayList<int[]> caminoPosible = new ArrayList<int[]>();

        System.out.println("Introduzca el n�mero de filas:");
        rows = TECLADO.nextInt();

        System.out.println("Introduzca el n�mero de columnas:");
        cols = TECLADO.nextInt();

        Tablero tablero = new Tablero(rows, cols);
        Tablero copiaTablero = new Tablero(rows, cols);

        tablero.crearTablero(rows, cols);
        copiaTablero.crearTablero(rows, cols);

        tablero.tablero[0][0].setVisitado(true); // marcar primera como visitada
        copiaTablero.tablero[0][0].setVisitado(true);

        copiaTablero.tablero = laberinto.crearCopia(tablero, copiaTablero);

        while (aux1 == 0 && aux2 == 0) {
            aux1 = (int) (Math.random() * tablero.getrows());
            aux2 = (int) (Math.random() * tablero.getcols());
        }

        laberinto.generarCamino(tablero, copiaTablero, aux1, aux2, caminoPosible, posibilidades, opcion);
        

    }

    public static void leerLaberinto() {
    	Problema problem = new Problema();
    	Dibujar d = new Dibujar();
    	Laberinto laberinto = new Laberinto();
        ArchivoJSON leerJSON = new ArchivoJSON();
        String nombre;
        nombre = TECLADO.next();
        problem = leerJSON.leerJSON(nombre);
        Tablero tab = problem.getTablero();

		try {
			d.dibujar(tab, tab.getrows(), tab.getcols()); //Esto pinta sin colores, habria que desactivar Pintar
        	laberinto.mostrarSucesores(tab);

		} catch (NullPointerException e) {
			System.out.println("No se ha podido crear el laberinto debido a una inconsistencia del fichero.");
			System.out.println(e);
		}
    }
    
    public static void leerLaberintoSolucion() {
        Problema problem = new Problema();
        Dibujar d = new Dibujar();
        Laberinto laberinto = new Laberinto();
        Tablero tab = problem.getTablero();
        Busqueda busqueda = new Busqueda();
        Frontera frontera = new Frontera ();
        ArchivoJSON leerJSON = new ArchivoJSON();
        ArrayList<Nodo> solucion = new ArrayList<Nodo>();
        
        System.out.println("Introduzca el archivo .json con la búsqueda que desea realizar:\n "
        		+ "Recuerde no introducir la extension del archivo (.json)");

        String nombre;
        nombre = TECLADO.next();
        problem = leerJSON.leerJSON(nombre);
        
        System.out.println("Seleccione una estrategia para resolver el problema:");
        System.out.println("Opcion 1: Anchura - BFS");
        System.out.println("Opcion 2: Profundidad Acotada - DFS");
        System.out.println("Opcion 3: Coste uniforme");
        System.out.println("Opcion 4: Voraz");
        System.out.println("Opcion 5: A*");

        boolean pasar = false;
    	int opcion;
    	while (!pasar) {
    		opcion = TECLADO.nextInt();
    		switch (opcion) {
        		case 1:
        			solucion = busqueda.busqueda(problem, opcion);        			
        			pasar = true;
        			break;
        		case 2:
        			solucion = busqueda.busqueda(problem, opcion);
        			pasar = true;
        			break;
        		case 3:
        			solucion = busqueda.busqueda(problem, opcion);
        			pasar = true;
        			break;
        		case 4:
        			solucion = busqueda.busqueda(problem, opcion);
        			pasar = true;
        			break;
        		case 5:
        			solucion = busqueda.busqueda(problem, opcion);
        			pasar = true;
        			break;
                default:
                    System.out.println("No se ha seleccionado ninguna opción, por favor inserte uno de los valores del menú de opciones.");
                    System.exit(0);
        	}
    	}

        ArrayList<Nodo> front = new ArrayList<Nodo>();
        ArrayList<Nodo> escogidos = new ArrayList<Nodo>();
        escogidos = busqueda.getNODOS_ESCOGIDOS();
        frontera = busqueda.getFFinal();
        
        while(!frontera.getFrontera().isEmpty()) {
        	front.add(frontera.cabeza());
        }
        
        tab = problem.getTablero();
        try {
        	
       	   	d.solucion(tab, tab.getrows(), tab.getcols(), front, solucion, escogidos);
            //laberinto.mostrarSucesores(tab);

        } catch (NullPointerException e) {
            System.out.println("Imposible crear el laberinto...");
            System.out.println(e);
        }
    }
   
    public static void Pintar(Tablero tablero) {
        Dibujar d = new Dibujar();
        try {
            d.pintar(tablero, tablero.getrows(), tablero.getcols());
        } catch (NullPointerException e) {
            System.out.println("Error: "+ e);
        }
    }
}