package edu.unlam.wome.cliente.mundo;
/**
 * Clase grafo
 * @author lesanmartin
 *
 */
public class Grafo {

	private int cantidadDeNodos;
	private int cantidadDeNodosTotal;
	private Nodo[] nodos;

	/**
	 * Constructor de la clase
	 *
	 * @param cantidadDeNodosTotal parametros cantidaDeNodosTotal
	 */
	public Grafo(final int cantidadDeNodosTotal) {
		cantidadDeNodos = 0;
		nodos = new Nodo[cantidadDeNodosTotal];
		this.cantidadDeNodosTotal = cantidadDeNodosTotal;
	}

	/**
	 * Agrega un nodo
	 *
	 * @param nodo parametros nodo
	 */
	public void agregarNodo(final Nodo nodo) {
		nodos[cantidadDeNodos++] = nodo;
	}

	/**
	 * Agrega nodos adyacentes
	 *
	 * @param nodoUno parametros nodoUno
	 * @param nodoDos parametros nodoDos
	 */
	public void agregarAdyacentes(final Nodo nodoUno, final Nodo nodoDos) {
		nodoUno.agregarAdyacente(nodoDos);
	}

	/**
	 * Devuelve los nodos
	 *
	 * @return nodos
	 */
	public Nodo[] obtenerNodos() {
		return nodos;
	}

	/**
	 * Retorna la cantidad de nodos
	 *
	 * @return cantidadDeNodos
	 */
	public int obtenerCantidadDeNodos() {
		return cantidadDeNodos;
	}

	/**
	 * Retorna la cantidad total de nodos
	 *
	 * @return cantidadDeNodosTotal
	 */
	public int obtenerCantidadDeNodosTotal() {
		return cantidadDeNodosTotal;
	}

}
