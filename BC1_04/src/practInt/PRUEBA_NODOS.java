package practInt;

import java.util.Random;

public class PRUEBA_NODOS {

	public static void main (String [] args) {
		Frontera front = new Frontera();
		Frontera frontOrdenada = new Frontera();
		Random r = new Random();
		
		for (int i = 0; i < 20; i++) {
			int [] estado = {0,0};
			estado [0] = r.nextInt(25);
			estado [1] = r.nextInt(25);
			Nodo n1 = new Nodo (i, estado, r.nextInt(25), r.nextInt(25), r.nextInt(25), null, r.nextInt(25));
			front.insertar(n1);
		}
		while (!front.getFrontera().isEmpty()) {
            frontOrdenada.insertar(front.getFrontera().poll());
		}
		frontOrdenada.mostrarFrontera();
	}	
}
