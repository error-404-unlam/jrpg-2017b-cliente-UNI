package mensajeria;

import java.io.Serializable;
/**
 * Clase Paquete de Batalla
 * @author lesanmartin
 *
 */
public class PaqueteBatalla extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private boolean miTurno;

	/**
	 * Constructor de la clase
	 */
	public PaqueteBatalla() {
		setComando(Comando.BATALLA);
	}

	/**
	 * Retorna el id
	 * @return getId
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Retorna el id enemigo
	 * @return idEnemigo
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Setea el id del enemigo
	 * @param idEnemigo
	 */
	public void setIdEnemigo(final int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Devuelve si es mi turno
	 * @return miTurno
	 */
	public boolean isMiTurno() {
		return miTurno;
	}

	/**
	 * Setea mi turno
	 * @param miTurno
	 */
	public void setMiTurno(final boolean miTurno) {
		this.miTurno = miTurno;
	}
}
