package edu.unlam.wome.estados;

import java.awt.Graphics;

import edu.unlam.wome.juego.Juego;

/**
 * Clase Estado
 * @author lesanmartin
 *
 */
public abstract class Estado {

	private static Estado estadoActual = null;

	// Tipo de estados
	private static int estadoOffline = 0;
	private static int estadoJuego = 1;
	private static int estadoBatalla = 2;

	private Juego juego;

	/**
	 * Constructor de la clase
	 * @param juego a construir
	 */
	public Estado(final Juego juego) {
		this.juego = juego;
	}

	/**
	 * Clase abstracta que actualiza
	 */
	public abstract void actualizar();

	/**
	 * Clase abstracta para graficar
	 * @param g graphics
	 */
	public abstract void graficar(Graphics g);

	/**
	 * Setea el estado
	 * @param estado estadoJuego
	 */
	public static void setEstado(final Estado estado) {
		estadoActual = estado;
	}

	/**
	 * Retorna el estado actual
	 * @return estadoActual
	 */
	public static Estado getEstado() {
		return estadoActual;
	}

	/**
	 * Retorna si es estado de juego
	 * @return boolean
	 */
	public abstract boolean esEstadoDeJuego();
	/**
	 * Retorna el estado del juego
	 * @return estadoJuego
	 */
	public static int getEstadoJuego() {
		return estadoJuego;
	}
	/**
	 * Retorna el estado offline
	 * @return estadoOffline
	 */
	public static int getEstadoOffLine() {
		return estadoOffline;
	}
	/**
	 * Retorna el estado de la batalla
	 * @return estado Batalla
	 */
	public static int getEstadoBatalla() {
		return estadoBatalla;
	}

	/**
	 * Devuelve el Juego
	 * @return Juego
	 */
	public Juego getJuego() {
		return juego;
	}
}
