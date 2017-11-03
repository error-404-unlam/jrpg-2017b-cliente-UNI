package edu.unlam.wome.cliente.mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase paquete de movimientos
 *
 * @author lesanmartin
 *
 */
public class PaqueteDeMovimientos extends Paquete implements Serializable, Cloneable {
	private Map<Integer, PaqueteMovimiento> personajes;

	/**
	 * Constructor no utilizado
	 */
	public PaqueteDeMovimientos() {

	}

	/**
	 * Asigna el objeto personajes
	 * @param personajes mapa de personajes
	 */
	public PaqueteDeMovimientos(final Map<Integer, PaqueteMovimiento> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve el objeto personajes
	 *
	 * @return personajes
	 */
	public Map<Integer, PaqueteMovimiento> getPersonajes() {
		return personajes;
	}

	/**
	 * Clona objeto
	 * @return devuelve el objeto;
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}
