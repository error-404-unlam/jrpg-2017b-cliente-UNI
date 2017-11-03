package edu.unlam.wome.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import dominio.Personaje;
import edu.unlam.wome.cliente.juego.Pantalla;
import edu.unlam.wome.cliente.recursos.Recursos;

/**
 * Clase encargada de graficar la batalla.
 * @author Miguel
 */
public class MenuBatalla {

	private static final int X = 100;
	private static final int Y = 380;
	private static final int ANCHO_BOTON = 40;
	/**
	 * offsets de los poderes de la barra de batalla
	 * 1 primer poder
	 * 2 segundo poder
	 * 3 tercer poder
	 * 4 cuarto poder
	 * 5 quinto poder
	 * 6 sexto poder
	 */
	private static final int OFFSET_X_1_PODER = 48;
	private static final int OFFSET_Y_1_PODER = 72;
	private static final int OFFSET_X_2_PODER = 48;
	private static final int OFFSET_Y_2_PODER = 146;
	private static final int OFFSET_X_3_PODER = 221;
	private static final int OFFSET_Y_3_PODER = 72;
	private static final int OFFSET_X_4_PODER = 221;
	private static final int OFFSET_Y_4_PODER = 146;
	private static final int OFFSET_X_5_PODER = 394;
	private static final int OFFSET_Y_5_PODER = 72;
	private static final int OFFSET_X_6_PODER = 394;
	private static final int OFFSET_Y_6_PODER = 146;
	private static final int[][] BOTONES = {
			{X + OFFSET_X_1_PODER, Y + OFFSET_Y_1_PODER },
			{X + OFFSET_X_2_PODER, Y + OFFSET_Y_2_PODER },
			{X + OFFSET_X_3_PODER, Y + OFFSET_Y_3_PODER },
			{X + OFFSET_X_4_PODER, Y + OFFSET_Y_4_PODER },
			{X + OFFSET_X_5_PODER, Y + OFFSET_Y_5_PODER },
			{X + OFFSET_X_6_PODER, Y + OFFSET_Y_6_PODER }};
	private boolean habilitado;
	private Personaje personaje;
	private final int tamFuenteLeyenda = 14;
	private final int offsetXHabilidadRaza = 95;
	private final int offsetYPrimerHabilidadRaza = 94;
	private final int offsetYSegundaHabilidadRaza = 168;
	private final int offsetXPrimerHabilidadCasta = 268;
	private final int offsetXSegundaHabilidadCasta = 442;
	private final int offsetYPrimerHabilidadCasta = 94;
	private final int offsetYSegundaHabilidadCasta = 168;
	private final int offsetYTerceraHabilidadCasta = 94;
	private final int offsetXSerEnergizado = 442;
	private final int offsetYSerEnergizado = 168;
	private final int altoRectanguloQuienEsElTurno = 20;
	private final int posicionVectorPoder1 = 0;
	private final int posicionVectorPoder2 = 1;
	private final int posicionVectorPoder3 = 2;
	private final int posicionVectorPoder4 = 3;
	private final int posicionVectorPoder5 = 4;
	private final int posicionVectorPoder6 = 5;
	private final int offsetYQuienEsElTurno = 5;


	/**
	 * Constructor
	 * @param habilitado boolean
	 * @param personaje Personaje que pelea
	 */
	public MenuBatalla(final boolean habilitado, final Personaje personaje) {
		this.habilitado = habilitado;
		this.personaje = personaje;
	}

	/**
	 * Grafica el menu
	 * @param g Graphics
	 */
	public void graficar(final Graphics g) {

		if (habilitado) {
			g.drawImage(Recursos.getMenuBatalla(), X, Y, null);
		} else {
			g.drawImage(Recursos.getMenuBatallaDeshabilitado(), X, Y, null);
		}

		// Dibujo los botones
		g.drawImage(Recursos.getHabilidades().
				get(personaje.getHabilidadesRaza()[0]),
				BOTONES[posicionVectorPoder1][0],
				BOTONES[posicionVectorPoder1][1],
				ANCHO_BOTON,
				ANCHO_BOTON,
				null);
		g.drawImage(Recursos.getHabilidades().
				get(personaje.getHabilidadesRaza()[1]),
				BOTONES[posicionVectorPoder2][0],
				BOTONES[posicionVectorPoder2][1],
				ANCHO_BOTON,
				ANCHO_BOTON,
				null);
		g.drawImage(Recursos.getHabilidades().
				get(personaje.getHabilidadesCasta()[0]),
				BOTONES[posicionVectorPoder3][0],
				BOTONES[posicionVectorPoder3][1],
				ANCHO_BOTON,
				ANCHO_BOTON,
				null);
		g.drawImage(
				Recursos.getHabilidades().
				get(personaje.getHabilidadesCasta()[1]),
				BOTONES[posicionVectorPoder4][0],
				BOTONES[posicionVectorPoder4][1],
				ANCHO_BOTON,
				ANCHO_BOTON,
				null);
		g.drawImage(
				Recursos.getHabilidades().
				get(personaje.getHabilidadesCasta()[2]),
				BOTONES[posicionVectorPoder5][0],
				BOTONES[posicionVectorPoder5][1],
				ANCHO_BOTON,
				ANCHO_BOTON,
				null);
		g.drawImage(
				Recursos.getHabilidades().get("Ser Energizado"),
				BOTONES[posicionVectorPoder6][0],
				BOTONES[posicionVectorPoder6][1],
				ANCHO_BOTON,
				ANCHO_BOTON,
				null);

		// Dibujo las leyendas
		g.setFont(new Font("Book Antiqua", 1, tamFuenteLeyenda));
		g.drawString(personaje.getHabilidadesRaza()[0],
				X + offsetXHabilidadRaza,
				Y + offsetYPrimerHabilidadRaza);
		g.drawString(personaje.getHabilidadesRaza()[1],
				X + offsetXHabilidadRaza,
				Y + offsetYSegundaHabilidadRaza);
		g.drawString(personaje.getHabilidadesCasta()[0],
				X + offsetXPrimerHabilidadCasta,
				Y + offsetYPrimerHabilidadCasta);
		g.drawString(personaje.getHabilidadesCasta()[1],
				X + offsetXPrimerHabilidadCasta,
				Y + offsetYSegundaHabilidadCasta);
		g.drawString(personaje.getHabilidadesCasta()[2],
				X + offsetXSegundaHabilidadCasta,
				Y + offsetYTerceraHabilidadCasta);
		g.drawString("Ser energizado",
				X + offsetXSerEnergizado,
				Y + offsetYSerEnergizado);

		// Dibujo de quién es el turno
		g.setColor(Color.WHITE);
		if (habilitado) {
			Pantalla.centerString(
					g, new Rectangle(
							X, Y + offsetYQuienEsElTurno,
							Recursos.getMenuBatalla().getWidth(),
							altoRectanguloQuienEsElTurno),
					"Mi Turno");
		} else {
			Pantalla.centerString(
					g, new Rectangle(
							X, Y + offsetYQuienEsElTurno,
							Recursos.getMenuBatalla().getWidth(),
							altoRectanguloQuienEsElTurno),
					"Turno Rival");
		}
	}

	/**
	 * Detecta el boton clickeado
	 * @param mouseX posicion x del mouse
	 * @param mouseY posicion y del mouse
	 * @return int
	 */
	public int getBotonClickeado(final int mouseX, final int mouseY) {
		if (!habilitado) {
			return 0;
		}
		for (int i = 0; i < BOTONES.length; i++) {
			if (mouseX >= BOTONES[i][0]
					&& mouseX <= BOTONES[i][0] + ANCHO_BOTON
					&& mouseY >= BOTONES[i][1]
					&& mouseY <= BOTONES[i][1] + ANCHO_BOTON) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * Detecta si hubo click en el menu
	 * @param mouseX Posicion X del Mouse
	 * @param mouseY Posicion Y del Mouse
	 * @return boolean
	 */
	public boolean clickEnMenu(final int mouseX, final int mouseY) {
		if (mouseX >= X
				&& mouseX <= X + Recursos.getMenuBatalla().getWidth()
				&& mouseY >= Y
				&& mouseY <= Y + Recursos.getMenuBatalla().getHeight()) {
			return habilitado;
		}
		return false;
	}

	/**
	 * Setea si esta habilitado para batalla
	 * @param b estado
	 */
	public void setHabilitado(final boolean b) {
		habilitado = b;
	}
}
