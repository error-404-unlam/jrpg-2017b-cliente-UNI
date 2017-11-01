package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Esta clase es la responsable de graficar a los personajes
 * @author Marvix
 */
public final class EstadoDePersonaje {

	private static final int ANCHOBARRA = 122;
	private static final int ALTOSALUD = 14;
	private static final int ALTOENERGIA = 14;
	private static final int ALTOEXPERIENCIA = 6;
	private static final int ALTOMINIATURA = 64;
	private static final int ANCHOMINIATURA = 64;

	private static final int OFFSET_X_MINIATURA_PERSONAJE = 10;
	private static final int OFFSET_Y_MINIATURA_PERSONAJE = 9;

	private static final int FONT_SIZE_10 = 10;
	private static final int FONT_SIZE_8 = 8;

	private static final int OFFSET_X_BARRA_SALUD_ENERGIA = 80;
	private static final int OFFSET_Y_BARRA_SALUD = 26;
	private static final int OFFSET_Y_BARRA_ENERGIA = 42;

	private static final int OFFSET_X_BARRA_SALUD_ENERGIA_TOPE = 132;
	private static final int OFFSET_Y_BARRA_SALUD_TOPE = 37;
	private static final int OFFSET_Y_BARRA_ENERGIA_TOPE = 52;

	private static final int OFFSET_X_BARRA_EXP = 77;
	private static final int OFFSET_Y_BARRA_EXP = 65;

	private static final int OFFSET_X_TABLA_NIVELES = 132;
	private static final int OFFSET_Y_NIVELES = 70;
	private static final int OFFSET_X_NIVELES = 59;

	/**
	 * Constructor de EstadoDePersonaje
	 */
	private EstadoDePersonaje() { }

	/**
	 * @param g  	graficador
	 * @param x		posicion x del personaje
	 * @param y		posicion y del personaje
	 * @param personaje		la informacion del personaje
	 * @param miniaturaPersonaje	imagen del personaje
	 */
	public static void dibujarEstadoDePersonaje(final Graphics g, final int x,
			final int y, final Personaje personaje, final BufferedImage miniaturaPersonaje) {

		int drawBarra = 0;

		g.drawImage(Recursos.getEstadoPersonaje(), x, y, null);

		g.drawImage(miniaturaPersonaje, x + OFFSET_X_MINIATURA_PERSONAJE, y
				+ OFFSET_Y_MINIATURA_PERSONAJE, ANCHOMINIATURA, ALTOMINIATURA, null);

		if (personaje.getSalud() == personaje.getSaludTope()) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getSalud() * ANCHOBARRA) / personaje.getSaludTope();
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE_10));
		g.drawImage(Recursos.getBarraSalud(), x + OFFSET_X_BARRA_SALUD_ENERGIA,
				y + OFFSET_Y_BARRA_SALUD, drawBarra, ALTOSALUD, null);
		g.drawString(String.valueOf(personaje.getSalud()) + " / "
				+ String.valueOf(personaje.getSaludTope()), x + OFFSET_X_BARRA_SALUD_ENERGIA_TOPE,
				y + OFFSET_Y_BARRA_SALUD_TOPE);

		if (personaje.getEnergia() == personaje.getEnergiaTope()) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getEnergia() * ANCHOBARRA) / personaje.getEnergiaTope();
		}

		g.drawImage(Recursos.getBarraEnergia(), x + OFFSET_X_BARRA_SALUD_ENERGIA,
				y + OFFSET_Y_BARRA_ENERGIA, drawBarra, ALTOENERGIA, null);
		g.drawString(String.valueOf(personaje.getEnergia()) + " / "
				+ String.valueOf(personaje.getEnergiaTope()), x + OFFSET_X_BARRA_SALUD_ENERGIA_TOPE,
				y + OFFSET_Y_BARRA_ENERGIA_TOPE);

		if (personaje.getExperiencia() == Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
					/ Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
		}

		g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE_8));
		g.drawImage(Recursos.getBarraExperiencia(), x + OFFSET_X_BARRA_EXP,
				y + OFFSET_Y_BARRA_EXP, drawBarra, ALTOEXPERIENCIA, null);
		g.drawString(String.valueOf(personaje.getExperiencia()) + " / "
				+ String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
				x + OFFSET_X_TABLA_NIVELES, y + OFFSET_Y_NIVELES);
		g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE_10));
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(personaje.getNivel()), x + OFFSET_X_NIVELES,
				y + OFFSET_Y_NIVELES);

	}

	/**
	 * @param g  	graficador
	 * @param x		posicion x del personaje
	 * @param y		posicion y del personaje
	 * @param personaje		la informacion del personaje
	 * @param miniaturaPersonaje	imagen del personaje
	 */
	public static void dibujarEstadoDePersonaje(final Graphics g, final int x, final int y,
			final PaquetePersonaje personaje, final BufferedImage miniaturaPersonaje) {

		int drawBarra = 0;

		g.drawImage(Recursos.getEstadoPersonaje(), x, y, null);

		g.drawImage(miniaturaPersonaje, x + OFFSET_X_MINIATURA_PERSONAJE,
				y + OFFSET_Y_MINIATURA_PERSONAJE, ANCHOMINIATURA, ALTOMINIATURA, null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE_10));
		g.drawImage(Recursos.getBarraSalud(), x + OFFSET_X_BARRA_SALUD_ENERGIA,
				y + OFFSET_Y_BARRA_SALUD, ANCHOBARRA, ALTOSALUD, null);
		g.drawString(String.valueOf(personaje.getSaludTope()) + " / "
				+ String.valueOf(personaje.getSaludTope()), x + OFFSET_X_BARRA_SALUD_ENERGIA_TOPE,
				y + OFFSET_Y_BARRA_SALUD_TOPE);

		g.drawImage(Recursos.getBarraEnergia(), x + OFFSET_X_BARRA_SALUD_ENERGIA,
				y + OFFSET_Y_BARRA_ENERGIA, ANCHOBARRA, ALTOENERGIA, null);
		g.drawString(String.valueOf(personaje.getEnergiaTope()) + " / "
				+ String.valueOf(personaje.getEnergiaTope()), x + OFFSET_X_BARRA_SALUD_ENERGIA_TOPE,
				y + OFFSET_Y_BARRA_ENERGIA_TOPE);

		if (personaje.getExperiencia() == Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
					/ Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
		}

		g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE_8));
		g.drawImage(Recursos.getBarraExperiencia(), x + OFFSET_X_BARRA_EXP,
				y + OFFSET_Y_BARRA_EXP, drawBarra, ALTOEXPERIENCIA, null);
		g.drawString(String.valueOf(personaje.getExperiencia()) + " / "
		+ String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
				x + OFFSET_X_TABLA_NIVELES, y + OFFSET_Y_NIVELES);
		g.setFont(new Font("Tahoma", Font.PLAIN, FONT_SIZE_10));
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(personaje.getNivel()), x + OFFSET_X_NIVELES, y + OFFSET_Y_NIVELES);
	}
}
