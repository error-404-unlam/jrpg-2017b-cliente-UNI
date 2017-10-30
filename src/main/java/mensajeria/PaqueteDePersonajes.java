package mensajeria;

import java.io.Serializable;
import java.util.Map;
/**
 * Clase de paquete de personajes
 * @author lesanmartin
 */
public class PaqueteDePersonajes extends Paquete implements Serializable, Cloneable {

	private Map<Integer, PaquetePersonaje> personajes;
	/**
	 * Constructor no utilizado
	 */
	public PaqueteDePersonajes() {

	}

	/**
	 * Asigna personajes
	 * @param personajes
	 */
	public PaqueteDePersonajes(final Map<Integer, PaquetePersonaje> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve el objeto personajes
	 * @return personajes
	 */
	public Map<Integer, PaquetePersonaje> getPersonajes() {
		return personajes;
	}

	/**
	 * Clona objeto
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}
