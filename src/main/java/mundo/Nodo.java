package mundo;

/**
 * Clase nodo
 * @author lesanmartin
 *
 */
public class Nodo {

	private int x;
	private int y;
	private int indice;
	private int cantidadDeAdyacentes;
	private Nodo[] nodosAdyacentes;

	/**
	 * Constructor de la clase
	 * @param indice
	 * @param x
	 * @param y
	 */
	public Nodo(final int indice,final int x,final int y) {
		this.x = x;
		this.y = y;
		this.indice = indice;
		cantidadDeAdyacentes = 0;
		nodosAdyacentes = new Nodo[8];
	}

	/**
	 * Retorna x
	 * @return x
	 */
	public int obtenerX() {
		return x;
	}

	/**
	 * Retorna y
	 * @return y
	 */
	public int obtenerY() {
		return y;
	}

	/**
	 * Retorna el indice
	 * @return indice
	 */
	public int obtenerIndice() {
		return indice;
	}

	/**
	 * Retorna los nodos adyacentes
	 * @return nodosAdyacentes
	 */
	public Nodo[] obtenerNodosAdyacentes() {
		return nodosAdyacentes;
	}

	/**
	 * Agrega nodos adyacentes
	 * @param nodo
	 */
	public void agregarAdyacente(final Nodo nodo) {
		nodosAdyacentes[cantidadDeAdyacentes++] = nodo;
	}

	/**
	 * Obtiene la cantidad de nodos adyacentes
	 * @return cantidadDeAdyacentes
	 */
	public int obtenerCantidadDeAdyacentes() {
		return cantidadDeAdyacentes;
	}
}
