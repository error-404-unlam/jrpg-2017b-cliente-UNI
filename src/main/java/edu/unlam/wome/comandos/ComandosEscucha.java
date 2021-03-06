package edu.unlam.wome.comandos;

import edu.unlam.wome.juego.Juego;
import edu.unlam.wome.mensajeria.Comando;


/**
 * Clase Comandos Escucha
 * @author Miguel
 */
public abstract class ComandosEscucha extends Comando {
	private Juego juego;

	/**
	 * Setea el Juego que se recibe por parametro
	 * @param juego a setear
	 */
	public void setJuego(final Juego juego) {
		this.juego = juego;
	}

	/**
	 * Devuelve el Juego
	 * @return Juego
	 */
	public Juego getJuego() {
		return juego;
	}

}
