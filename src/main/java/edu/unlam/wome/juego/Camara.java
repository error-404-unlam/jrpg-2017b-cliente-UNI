package edu.unlam.wome.juego;

import edu.unlam.wome.entidades.Entidad;

/**
 * Clase Camara
 * @author lesanmartin
 */
public class Camara {

	private Juego juego;
	private float yOffset;
	private float xOffset;

	/**
	 * Constructor de la clase
	 *
	 * @param juego juego
	 * @param xOffset	desplazamiento en x
	 * @param yOffset	desplazamiento en y
	 */
	public Camara(final Juego juego, final float xOffset, final float yOffset) {
		this.juego = juego;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Centra la camara
	 *
	 * @param e entidad
	 */
	public void centrar(final Entidad e) {
		xOffset = e.getX() - juego.getAncho() / 2 + e.getAncho() / 2;
		yOffset = e.getY() - juego.getAlto() / 2 + e.getAlto() / 2;
	}

	/**
	 * Mueve la camara
	 *
	 * @param dx	direccion x
	 * @param dy	direccion y
	 */
	public void mover(final float dx, final float dy) {
		xOffset += dx;
		yOffset += dy;
	}

	/**
	 * Devuelve la coordenada y
	 *
	 * @return yOffset
	 */
	public float getyOffset() {
		return yOffset;
	}

	/**
	 * Setea la variable Y
	 *
	 * @param yOffset	desplazamiento en y
	 */
	public void setyOffset(final float yOffset) {
		this.yOffset = yOffset;
	}

	/**
	 * Retorna el valor de x
	 *
	 * @return xOffset
	 */
	public float getxOffset() {
		return xOffset;
	}

	/**
	 * Setea el valor de x
	 *
	 * @param xOffset	desplazamiento en x
	 */
	public void setxOffset(final float xOffset) {
		this.xOffset = xOffset;
	}
}
