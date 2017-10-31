package mensajeria;

import java.io.Serializable;
import java.util.Map;
/**
 * Clase de paquete de personajes
 *
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
	 * Paquete de personajes
	 *
	 * @param personajes
	 */
	public PaqueteDePersonajes(final Map<Integer, PaquetePersonaje> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve el objeto personajes
	 *
	 * @return personajes
	 */
	public Map<Integer, PaquetePersonaje> getPersonajes() {
		return personajes;
	}

	@Override
	/**
	 * Retorna el objeto
	 *
	 * @return obj
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

}
