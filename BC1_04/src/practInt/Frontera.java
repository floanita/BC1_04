package practInt;

import java.util.PriorityQueue;

public class Frontera{

	PriorityQueue <Nodo> frontera;
	
	public Frontera () {
		frontera = new PriorityQueue<Nodo>(new MiComparador());
	}
	
	public Frontera(PriorityQueue<Nodo> frontera) {
		this.frontera = frontera;
	}

	public PriorityQueue<Nodo> getFrontera() {
		return frontera;
	}

	public void setFrontera(PriorityQueue<Nodo> frontera) {
		this.frontera = frontera;
	}

	public void mostrarFrontera() {
		for (Nodo n : frontera) {
			System.out.println(n.toString());
		}
	}
	
	public void insertar(Nodo nodo) {
		frontera.add(nodo);
	}
	
	public Nodo eliminar() {
		return frontera.remove();
	}
	
	public Nodo cabeza() {
		return frontera.poll();
	}
}
