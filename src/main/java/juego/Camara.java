package juego;

import entidades.Entidad;

/**
 * Clase Camara 
 *
 * @author lesanmartin
 *
 */
public class Camara {

	private Juego juego;
	private float yOffset;
	private float xOffset;

	/**
	 * Constructor de la clase
	 *
	 * @param juego
	 * @param xOffset
	 * @param yOffset
	 */
	public Camara(final Juego juego, final float xOffset, final float yOffset) {
		this.juego = juego;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Centra la camara
	 *
	 * @param e
	 */
	public void centrar(final Entidad e) {
		xOffset = e.getX() - juego.getAncho() / 2 + e.getAncho() / 2;
		yOffset = e.getY() - juego.getAlto() / 2 + e.getAlto() / 2;
	}

	/**
	 * Mueve la camara
	 *
	 * @param dx
	 * @param dy
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
	 * @param yOffset
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
	 * @param xOffset
	 */
	public void setxOffset(final float xOffset) {
		this.xOffset = xOffset;
	}
}
