package mensajeria;

import java.io.Serializable;

/**
 * Clase de Paquete de Mensajes
 * @author lesanmartin
 *
 */
public class PaqueteMensaje extends Paquete implements Serializable, Cloneable {

	private String userEmisor;
	private String userReceptor;
	private String msj;

	/**
	 * Constructor de la clase
	 */
	public PaqueteMensaje() {
	}

	/**
	 * Retorna el mensaje
	 */
	public String getMensaje() {
		return msj;
	}

	/**
	 * Asigna mensaje
	 */
	public void setMensaje(final String mensaje) {
		this.msj = mensaje;
	}

	/**
	 * Retorna el usuario emisor
	 * 
	 * @return userEmisor
	 */
	public String getUserEmisor() {
		return userEmisor;
	}

	/**
	 * Setea el id del usuario emisor
	 * 
	 * @param idEmisor
	 */
	public void setUserEmisor(final String idEmisor) {
		this.userEmisor = idEmisor;
	}

	/**
	 * Retorna el usuario receptor
	 * 
	 * @return userReceptor
	 */
	public String getUserReceptor() {
		return userReceptor;
	}

	/**
	 * Setea el usuario receptor
	 * 
	 * @param idReceptor
	 */
	public void setUserReceptor(final String idReceptor) {
		this.userReceptor = idReceptor;
	}

	/**
	 * Clona objeto
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
