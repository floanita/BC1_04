package practInt;

import java.util.Comparator;

public class MiComparador implements Comparator<Nodo>{

	public int compare (Nodo n1, Nodo n2) {
		if(n1.getValor() < n2.getValor()) { // ordena por valor
			return -1;
		} else if (n1.getValor() > n2.getValor()) {
			return 1;
		} else { // si mismo valor, ordena por fila
			if(n1.getEstado()[0] < n2.getEstado()[0]) {
				return -1;
			} else if (n1.getEstado()[0] > n2.getEstado()[0]) {
				return 1;
			} else { // si misma fila, ordena por columna
				if(n1.getEstado()[1] < n2.getEstado()[1]) {
					return -1;
				} else if (n1.getEstado()[1] > n2.getEstado()[1]) {
					return 1;
				} else { // si misma columna, ordena por ID
					if(n1.getIdNodo() < n2.getIdNodo()) { // ordena por valor
						return -1;
					} else {
						return 1;
					}
				}
			}
		}
	}

}
