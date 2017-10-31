package mensajeria;

import java.io.Serializable;
/**
 * Clase Paquete Usuario
 *
 * @author lesanmartin
 *
 */
public class PaqueteUsuario extends Paquete implements Serializable, Cloneable {

	private int idPj;
	private String username;
	private String password;
	private boolean inicioSesion;

	/**
	 * Constructor de la clase
	 */
	public PaqueteUsuario() {

	}

	/**
	 * Constructor parametrizado de la clase 
	 *
	 * @param pj
	 * @param user
	 * @param pw
	 */
	public PaqueteUsuario(final int pj, final String user, final String pw) {
		idPj = pj;
		username = user;
		password = pw;
		inicioSesion = false;
	}

	/**
	 * Retorna el id
	 *
	 * @return idPj
	 */
	public int getIdPj() {
		return idPj;
	}

	/**
	 * Setea el id
	 *
	 * @param idPj
	 */
	public void setIdPj(final int idPj) {
		this.idPj = idPj;
	}

	/**
	 * Devuelve el nombre de usuario
	 *
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setea el nombre de usuario
	 *
	 * @param username
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Retorna el password
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setea el password
	 *
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Verifica si es inicio de sesion
	 *
	 * @return
	 */
	public boolean isInicioSesion() {
		return inicioSesion;
	}

	/**
	 * Setea el inicio de la sesion
	 *
	 * @param inicioSesion
	 */
	public void setInicioSesion(final boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
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
