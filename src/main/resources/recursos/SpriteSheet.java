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
	 * @param sprite
	 */
	public SpriteSheet(final BufferedImage sprite) {
		this.sprite = sprite;
	}

	/**
	 * Return Tile
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @return imagen
	 */
	public BufferedImage getTile(final int x,final int y,final int ancho,final int alto) {
		return sprite.getSubimage(x, y, ancho, alto);
	}
}
