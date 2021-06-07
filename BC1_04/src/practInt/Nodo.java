package practInt;

import java.util.Arrays;
import java.util.Comparator;

public class Nodo{

	int idNodo;
	int [] estado;
	double valor;
	int profundidad;
	double costo_acumulado;
	int heuristica;
	int accion;
	Nodo nodoPadre;
	
	public Nodo() {
		
	}
	
	public Nodo (int idNodo, int[] estado, double valor, int profundidad, double costo_acumulado, Nodo nodoPadre, int accion) {
		this.idNodo = idNodo;
		this.estado = estado;
		this.valor = valor;
		this.profundidad = profundidad;
		this.costo_acumulado = costo_acumulado;
		this.accion = accion;
		this.nodoPadre = nodoPadre;
	}
	public Nodo(int idNodo, int[] estado, double valor, int profundidad, double costo_acumulado, int heuristica, char accion,
			Nodo nodoPadre) {
		this.idNodo = idNodo;
		this.estado = estado;
		this.valor = valor;
		this.profundidad = profundidad;
		this.costo_acumulado = costo_acumulado;
		this.heuristica = heuristica;
		this.accion = accion;
		this.nodoPadre = nodoPadre;
	}
	
	public int getIdNodo() {
		return idNodo;
	}
	public void setIdNodo(int idNodo) {
		this.idNodo = idNodo;
	}
	public int[] getEstado() {
		return estado;
	}
	public void setEstado(int[] estado) {
		this.estado = estado;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public double getCosto_acumulado() {
		return costo_acumulado;
	}
	public void setCosto_acumulado(double d) {
		this.costo_acumulado = d;
	}
	public int getHeuristica() {
		return heuristica;
	}
	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}
	public int getAccion() {
		return accion;
	}
	public void setAccion(int accion) {
		this.accion = accion;
	}
	public Nodo getNodoPadre() {
		return nodoPadre;
	}
	public void setNodoPadre(Nodo nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	@Override
	public String toString() {
		return "Nodo [idNodo=" + idNodo + ", estado=" + Arrays.toString(estado) + ", valor=" + valor + ", profundidad="
				+ profundidad + ", costo_acumulado=" + costo_acumulado + ", heuristica=" + heuristica + ", accion="
				+ accion + ", nodoPadre=" + nodoPadre + "]";
	}
}
