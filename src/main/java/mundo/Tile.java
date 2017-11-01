package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Clase encargada del manejo de las baldosas.
 * @author Miguel
 */
public class Tile {
	protected static final int ANCHO = 64;
	protected static final int ALTO = 32;
	private static final int TAM_TILES = 256;
	private static Tile[] tiles = new Tile[TAM_TILES];
	private static Tile[] aubenor;
	private static Tile[] aris;
	private static final int AUBENORBASE = 3; // Piso por defecto
	private static final int ARISBASE = 3; // Piso por defecto

	private BufferedImage textura;
	private final int id;

	private boolean esSolido;

	private int ancho;
	private int alto;

	/**
	 * Baldosa a graficar
	 * @param textura Textura
	 * @param id Id
	 * @param esSolido es solido
	 */
	public Tile(
			final BufferedImage textura,
			final int id,
			final boolean esSolido) {
		this.textura = textura;
		this.id = id;
		tiles[id] = this;
		this.esSolido = esSolido;
	}

	/**
	 * Baldosa a graficar
	 * @param textura Textura
	 * @param id Id
	 * @param esSolido es solido
	 * @param ancho ancho
	 * @param alto alto
	 */
	public Tile(final BufferedImage textura,
			final int id,
			final boolean esSolido,
			final int ancho,
			final int alto) {
		this.textura = textura;
		this.id = id;
		tiles[id] = this;
		this.ancho = ancho;
		this.alto = alto;
		this.esSolido = esSolido;
	}

	/**
	 * Actualiza
	 */
	public void actualizar() {

	}

	/**
	 * Grafica
	 * @param g graphics
	 * @param x X
	 * @param y Y
	 */
	public void graficar(final Graphics g, final int x, final int y) {
		g.drawImage(textura, x, y, ANCHO, ALTO, null);
	}

	/**
	 * Grafica
	 * @param g graphics
	 * @param x X
	 * @param y Y
	 * @param width ancho
	 * @param height alto
	 */
	public void graficar(
			final Graphics g,
			final int x,
			final int y,
			final int width,
			final int height) {
		g.drawImage(textura, x, y, width, height, null);
	}

	/**
	 * Setea si es solido
	 * @param solidez a setear
	 */
	public void setSolido(final boolean solidez) {
		esSolido = solidez;
	}

	/**
	 * devuelve si es solido
	 * @return es solido
	 */
	public boolean esSolido() {
		return esSolido;
	}

	/**
	 * devuelve la id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * devuelve el ancho
	 * @return ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * devuelve el alto
	 * @return alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Devuelve las baldosas
	 * @return Tile[]
	 */
	public static Tile[] getTiles() {
		return tiles;
	}

	/**
	 * Devuelve la baldosa de aubenor
	 * @return Tile[]
	 */
	public static Tile[] getAubenor() {
		return aubenor;
	}

	/**
	 * Devuelve la baldosa de aris
	 * @return Tile[]
	 */
	public static Tile[] getAris() {
		return aris;
	}

	/**
	 * devuelve aubenor
	 * @return int
	 */
	public static int getAubenorBase() {
		return AUBENORBASE;
	}

	/**
	 * devuelve aris
	 * @return int
	 */
	public static int getArisBase() {
		return ARISBASE;
	}

	/**
	 * devuelve textura
	 * @return BufferedImage
	 */
	public BufferedImage getTextura() {
		return textura;
	}

	/**
	 * Devuelve si es solido
	 * @return boolean
	 */
	public boolean isEsSolido() {
		return esSolido;
	}

	/**
	 * Setea las baldosas de aubenor
	 * @param aubenor aubenor
	 */
	public static void setAubenor(final Tile[] aubenor) {
		Tile.aubenor = aubenor;
	}

	/**
	 * Setea las baldosas de aris
	 * @param aris aris
	 */
	public static void setAris(final Tile[] aris) {
		Tile.aris = aris;
	}

}
