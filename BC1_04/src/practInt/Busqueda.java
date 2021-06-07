package practInt;

import java.util.*;
public class Busqueda {

	ArrayList<Nodo> NODOS_ESCOGIDOS = new ArrayList<Nodo>();
	Frontera FFinal = new Frontera();
	int contador = 0;

	public ArrayList<Nodo> busqueda (Problema problema, int estrategia) {
		ArrayList<Nodo> escogidos = new ArrayList<Nodo>();
		Frontera frontera = new Frontera ();
		Visitados visitado = new Visitados();
		boolean solucion;
		Nodo nodo = new Nodo();
		ArrayList<Nodo> hijos = new ArrayList<Nodo>();
		int[] objetivo = problema.getObjetive();
		
		int profundidad = 1000000;
		nodo.setNodoPadre(null);
		nodo.setEstado(problema.getInitial());
		int heuristica = calcularHeuristica(nodo.getEstado(), problema.getObjetive());
		nodo.setCosto_acumulado(0);
		nodo.setProfundidad(0);
		nodo.setAccion(0);
		nodo.setHeuristica(heuristica);
		nodo.setValor(calcularEstrategiaNodo(estrategia, nodo));
		
		//insertar nodo en la frontera
		frontera.insertar(nodo);
		solucion=false;
		
		while(!frontera.getFrontera().isEmpty() && solucion==false) {
			nodo = frontera.cabeza();
			
			if (problema.comprobarObjetivo(nodo.getEstado())) {
				solucion=true;
			}
			else if (!visitado.perteneceEstado(nodo.getEstado()) && nodo.getProfundidad() < profundidad) {
				visitado.insertarEstado(nodo.getEstado());
				escogidos.add(nodo);
				hijos=expandirNodo(problema, nodo, estrategia);
				for (int i =0; i<hijos.size(); i++) {
					frontera.insertar(hijos.get(i));
					
				}
			}
			
		}
		
		crearArbol(frontera,escogidos);
		
		if (solucion) {
			ArrayList<Nodo> camino = new ArrayList<Nodo>();
			camino = camino (nodo);
			for (int i=0; i<camino.size()-1; i++) {
				System.out.println("["+camino.get(i).getIdNodo()+"]" + " [" + camino.get(i).getCosto_acumulado()+", "+ "("+camino.get(i).getEstado()[0] + "," +
						+camino.get(i).getEstado()[1]+"), "+camino.get(i).getNodoPadre().getIdNodo() +", " +camino.get(i).getAccion() +", "+camino.get(i).getAccion()
						+", "+camino.get(i).getProfundidad()+", "+ camino.get(i).getHeuristica() +", "+camino.get(i).getValor()+"]");
				
			}
			int fin = camino.size()-1;
			System.out.println("["+camino.get(fin).getIdNodo()+"]" + " [" + camino.get(fin).getCosto_acumulado()+", "+ "("+camino.get(fin).getEstado()[0]+ "," +
					+camino.get(fin).getEstado()[1]+"), "+"null" +", " +"null" +", " +camino.get(fin).getAccion()
					+", "+camino.get(fin).getProfundidad()+", "+ camino.get(fin).getHeuristica() +", "+camino.get(fin).getValor()+"]");
			return camino;
		}
		else {
			System.out.println("Lo sentimos, no existe soluci�n");
			return null;
		}
		
	}
	
	private void crearArbol(Frontera frontera, ArrayList<Nodo> escogidos) {
		
		while(!frontera.getFrontera().isEmpty()) {
			boolean pertenece = false;
			Nodo nodo = frontera.cabeza();
			int [] estado = nodo.getEstado();
			for (int i=0; i<escogidos.size();i++) {
				if(escogidos.get(i).getEstado()[0]==estado[0] && escogidos.get(i).getEstado()[1]==estado[1])
					pertenece = true;
			}
			if(!pertenece)
				FFinal.insertar(nodo);
		}
		
		
		for (int i = 0; i<escogidos.size();i++)
			NODOS_ESCOGIDOS.add(escogidos.get(i));
	}

	private ArrayList<Nodo> camino(Nodo nodo) {
		ArrayList<Nodo> camino = new ArrayList<Nodo>();
		
		camino.add(nodo);
		while(nodo.getNodoPadre() != null){
			nodo =nodo.getNodoPadre();
			camino.add(nodo);
		}
		
		return camino;
	}

	private ArrayList<Nodo> expandirNodo(Problema problema, Nodo nodo, int estrategia) {
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();
		Tablero tablero = new Tablero();
		tablero = problema.getTablero();
		for (int i=0; i<problema.sucesores(nodo.getEstado(), tablero).size(); i++) {
			Nodo nodoHijo = new Nodo();
			Sucesores sucesor = problema.sucesores(nodo.getEstado(), tablero).get(i);
			nodoHijo.setIdNodo(contador++);
			nodoHijo.setEstado(sucesor.getEstado());
			nodoHijo.setNodoPadre(nodo);
			nodoHijo.setAccion(sucesor.getMovimiento());
			nodoHijo.setCosto_acumulado(nodo.getCosto_acumulado() + sucesor.getCoste());
			nodoHijo.setProfundidad(nodo.getProfundidad()+1);
			nodoHijo.setHeuristica(calcularHeuristica(sucesor.getEstado(), problema.getObjetive()));
			nodoHijo.setValor(calcularEstrategiaNodo(estrategia, nodoHijo));
			nodos.add(nodoHijo);
		}
		return nodos;
	}

	public static int calcularHeuristica (int[] estado, int[] objetivo) {
		
		int heuristica = Math.abs(estado[0]-objetivo[0])+ Math.abs(estado[1]-objetivo[1]);
		return heuristica;
	}

	public static double calcularEstrategiaNodo(int estrategia, Nodo nodo) {
		double valor = 0;	
		if (estrategia == 1) {
			valor = nodo.getProfundidad();
		} else if (estrategia == 2) {
			
			valor = redondeo((double) 1 / (nodo.getProfundidad() + 1));
		} else if (estrategia == 3) {
			valor = nodo.getCosto_acumulado();
			
		} else if (estrategia == 4) {
			valor = nodo.getHeuristica();
			
			
			
		} else if (estrategia == 5) {
			valor = nodo.getCosto_acumulado() + nodo.getHeuristica();
		}			
		return valor;
	}
	
	private static double redondeo(double d) {
		return Math.round(d * Math.pow(10, 17)) / Math.pow(10, 17);
	}

	public ArrayList<Nodo> getNODOS_ESCOGIDOS() {
		return NODOS_ESCOGIDOS;
	}

	public Frontera getFFinal() {
		return FFinal;
	}
	
}
