package mensajeria;

import java.io.Serializable;

/**
 * Clase de final de la batalla
 *
 * @author lesanmartin
 *
 */
public class PaqueteFinalizarBatalla extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int ganadorBatalla;

	/**
	 * Constructor de la clase
	 */
	public PaqueteFinalizarBatalla() {
		setComando(Comando.FINALIZARBATALLA);
	}

	/**
	 * Retorna el id
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 *
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Retorna el id del enemigo
	 *
	 * @return idEnemigo
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Setea el id del enemigo
	 *
	 * @param idEnemigo
	 */
	public void setIdEnemigo(final int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Devuelve el ganandor de la batalla
	 *
	 * @return ganadorBatalla
	 */
	public int getGanadorBatalla() {
		return ganadorBatalla;
	}

	/**
	 * Setea el ganador de la batalla
	 *
	 * @param ganadorBatalla
	 */
	public void setGanadorBatalla(final int ganadorBatalla) {
		this.ganadorBatalla = ganadorBatalla;
	}
}
