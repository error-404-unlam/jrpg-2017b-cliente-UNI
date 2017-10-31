package recursos;

import java.awt.image.BufferedImage;

/**
 * Clase SpriteSheet de imagen
 * @author lesanmartin
 *
 */
public class SpriteSheet {

	private BufferedImage sprite;

	/**
	 * Constructor de la clase
	 * @param sprite Sprite
	 */
	public SpriteSheet(final BufferedImage sprite) {
		this.sprite = sprite;
	}

	/**
	 * Devuelve el tile
	 * @param x posicion X
	 * @param y posicion Y
	 * @param ancho Ancho
	 * @param alto Alto
	 * @return BufferedImage
	 */
	public BufferedImage getTile(final int x, final int y, final int ancho, final int alto) {
		return sprite.getSubimage(x, y, ancho, alto);
	}
}
