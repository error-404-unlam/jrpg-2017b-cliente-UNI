package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Clase encargada de la graficacion de los menues.
 * @author Miguel
 */
public class MenuInfoPersonaje {

	private static final int ANCHO_PERSONAJE = 128;
	private static final BufferedImage MENU = Recursos.getMenuEnemigo();
	public static final int MENU_BATALLAR = 0;
	public static final int MENU_INFORMACION = 1;
	public static final int MENU_SUBIR_NIVEL = 2;
	public static final int MENU_GANAR_BATALLA = 3;
	public static final int MENU_PERDER_BATALLA = 4;
	public static final int MENU_GANAR_ITEM = 5;
	public static final int MENU_COMERCIAR = 6;
	private static final String[] LEYENDA_BOTON = {
			"Batallar", "Volver",
			"Aceptar", "Aceptar",
			"Aceptar", "Aceptar",
			"Comerciar" };

	private int x;
	private int y;
	private PaquetePersonaje personaje;
	private final int offsetXMinBoton = 50;
	private final int offsetXMaxBoton = 250;
	private final int offsetYMinBoton = 380;
	private final int offsetYMaxBoton = 405;
	private final int offsetXMinCerrar = 24;
	private final int offsetXMaxCerrar = 4;
	private final int offsetYMinCerrar = 12;
	private final int offsetYMaxCerrar = 36;
	private final int offsetXMinMenuAsigSkill = 50;
	private final int offsetXMaxMenuAsigSkill = 250;
	private final int offsetYMinMenuAsigSkill = 410;
	private final int offsetYMaxMenuAsigSkill = 430;
	/**
	 * Offsets del menu comerciar e Informacion
	 * Offset = desplazamiento de forma absoluta desde un punto
	 */
	private final int offsetYCentrarString = 200;
	private final int posXCasta = 30;
	private final int posXNivel = 30;
	private final int posXExperiencia = 30;
	private final int tamFuenteMenu = 20;
	private final int offsetXCasta = 100;
	private final int offsetYCasta = 260;
	private final int offsetXNivel = 100;
	private final int offsetYNivel = 290;
	private final int offsetXExperiencia = 150;
	private final int offsetYExperiencia = 320;

	/**
	 * Offsets de graficar menu item
	 * Offset = desplazamiento de forma absoluta desde un punto
	 */
	private final int offsetYPrimerCenterString = 200;
	private final int offsetYSegundoCenterString = 240;
	private final int offsetYTercerCenterString = 270;
	private final int offsetYCuartoCenterString = 325;
	private final int tamFuentePrimerCenterString = 18;
	private final int tamFuenteSegundoCenterString = 62;
	/**
	 * Offsets de Graficar Menu Subir Nivel
	 */
	private final int offsetYHasSubidoDeNivel = 200;
	private final int offsetYFelicitaciones = 240;
	private final int offsetYNuevoNivel = 270;
	private final int offsetYGetNivel = 325;
	private final int tamFuenteHasSubidoDeNivel = 18;
	private final int tamFuenteNuevoNivel = 62;

	/**
	 * Offsets Menu Ganar Batalla
	 * Se llaman 	Pri por primer linea
	 * 				Seg por segunda linea
	 * 				Ter por tercer linea
	 * 				Cua por cuarta linea
	 * 				Qui por quinta linea
	 * 				Sex por sexta linea
	 * Hacen referencia al numero de renglon.
	 */
	private final int tamFuenteGanarBatalla = 14;
	private final int offsetYPriLinea = 200;
	private final int offsetYSegLinea = 230;
	private final int offsetYTerLinea = 270;
	private final int offsetYCuaLinea = 290;
	private final int offsetYQuiLinea = 310;
	private final int offsetYSexLinea = 330;
	/**
	 * Offsets Menu Perder Batalla
	 * Se llaman 	Pri por primer linea
	 * 				Seg por segunda linea
	 * 				Ter por tercer linea
	 * 				Cua por cuarta linea
	 * 				Qui por quinta linea
	 * Hacen referencia al numero de renglon.
	 */
	private final int tamFuentePerderBatalla = 14;
	private final int offsetYPriLineaPerder = 200;
	private final int offsetYSegLineaPerder = 250;
	private final int offsetYTerLineaPerder = 270;
	private final int offsetYCuaLineaPerder = 290;
	private final int offsetYQuiLineaPerder = 310;
	/**
	 * Offsets Graficar
	 */
	private final int tamFuenteBoton = 20;
	private final int offsetYLeyendaBoton = 380;
	private final int offsetYAsignarSkill = 410;
	private final int offsetXBoton = 50;
	private final int anchoBoton = 200;
	private final int altoBoton = 25;
	private final int posRaza = 6;
	private final int offsetYDrawImageGraficar = 70;
	private final int tamFuenteGraficar = 20;
	private final int offsetYGraficar = 15;
	private final int anchoDrawImageGraficar = 128;
	private final int altoDrawImageGraficar = 128;
	/**
	 * Constructor
	 * @param x Personaje
	 * @param y Personaje
	 * @param personaje Paquete Personaje
	 */
	public MenuInfoPersonaje(
			final int x,
			final int y,
			final PaquetePersonaje personaje) {
		this.x = x;
		this.y = y;
		this.personaje = personaje;
	}

	/**
	 * Grafica el menu pasado por parametro
	 * @param g Graphics
	 * @param tipoMenu NUmero de menu
	 */
	public void graficar(final Graphics g, final int tipoMenu) {

		// Dibujo el menu
		g.drawImage(MENU, x, y, null);

		// Dibujo el personaje
		g.drawImage(
				Recursos.getPersonaje().get(
						personaje.getRaza()).get(posRaza)[0],
				x + MENU.getWidth() / 2 - ANCHO_PERSONAJE / 2,
				y + offsetYDrawImageGraficar, anchoDrawImageGraficar,
				altoDrawImageGraficar, null);
		// Muestro el nombre
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", 1, tamFuenteGraficar));
		Pantalla.centerString(g, new Rectangle(
				x,
				y + offsetYGraficar,
				MENU.getWidth(), 0),
				personaje.getNombre());
		// Grafico la leyenda segun el tipo de menu
		switch (tipoMenu) {
		case MENU_BATALLAR:
			graficarMenuInformacion(g);
			break;
		case MENU_INFORMACION:
			graficarMenuInformacion(g);
			break;
		case MENU_SUBIR_NIVEL:
			graficarMenuSubirNivel(g);
			break;
		case MENU_GANAR_BATALLA:
			graficarMenuGanarBatalla(g);
			break;
		case MENU_PERDER_BATALLA:
			graficarMenuPerderBatalla(g);
			break;
		case MENU_GANAR_ITEM:
			graficarMenuItem(g);
			break;
		case MENU_COMERCIAR:
			graficarMenuComerciar(g);
			break;
		default:
			break;
		}

		// Muestro los botones
		g.setFont(new Font("Book Antiqua", 1, tamFuenteBoton));
		g.drawImage(Recursos.getBotonMenu(),
				x + offsetXBoton,
				y + offsetYLeyendaBoton,
				anchoBoton, altoBoton, null);
		g.setColor(Color.WHITE);
		Pantalla.centerString(g, new Rectangle(
				x + offsetXBoton,
				y + offsetYLeyendaBoton,
				anchoBoton, altoBoton),
				LEYENDA_BOTON[tipoMenu]);

		// Agrego el botón "Asignar Skills"
		if (tipoMenu == 2) {
			g.drawImage(Recursos.getBotonMenu(),
					x + offsetXBoton,
					y + offsetYAsignarSkill,
					anchoBoton, altoBoton, null);
			Pantalla.centerString(g, new Rectangle(
					x + offsetXBoton,
					y + offsetYAsignarSkill,
					anchoBoton, altoBoton), "Asignar Skills");
		}
	}

	/**
	 * Grafica el menu perder batalla
	 * @param g Graphics
	 */
	private void graficarMenuPerderBatalla(final Graphics g) {

		// Informo que perdió la batalla
		g.setColor(Color.BLACK);
		Pantalla.centerString(g,
				new Rectangle(x,
						y + offsetYPriLineaPerder,
						MENU.getWidth(), 0),
				"¡Has sido derrotado!");

		g.setFont(new Font(
				"Book Antiqua",
				0, tamFuentePerderBatalla));
		Pantalla.centerString(
				g, new Rectangle(
						x, y + offsetYSegLineaPerder,
						MENU.getWidth(), 0),
				"¡No te rindas! Sigue luchando");
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYTerLineaPerder,
				MENU.getWidth(), 0),
				"contra los demás personajes");
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYCuaLineaPerder,
				MENU.getWidth(), 0),
				"para aumentar tu nivel y");
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYQuiLineaPerder,
				MENU.getWidth(), 0),
				"mejorar tus atributos.");
	}

	/**
	 * Grafica el menu Ganar Batalla
	 * @param g Graphics
	 */
	private void graficarMenuGanarBatalla(final Graphics g) {

		// Informo que ganó la batalla
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + offsetYPriLinea, MENU.getWidth(), 0), "¡Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + offsetYSegLinea, MENU.getWidth(), 0), "a tu enemigo!");
		g.setFont(new Font("Book Antiqua", 0, tamFuenteGanarBatalla));
		Pantalla.centerString(g,
				new Rectangle(
						x, y + offsetYTerLinea,
						MENU.getWidth(), 0),
				"¡Felicitaciones! Has derrotado");
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYCuaLinea,
				MENU.getWidth(), 0),
				"a tu oponente, sigue así");
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYQuiLinea,
				MENU.getWidth(), 0),
				"para lograr subir de nivel");
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYSexLinea,
				MENU.getWidth(), 0),
				"y mejorar tus atributos.");

	}

	/**
	 * Grafica el menu Subir Nivel
	 * @param g Graphics
	 */
	private void graficarMenuSubirNivel(final Graphics g) {

		// Informo que subió de nivel
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(
				x, y + offsetYHasSubidoDeNivel,
				MENU.getWidth(), 0),
				"¡Has subido de nivel!");

		g.setFont(new Font("Book Antiqua", 0, tamFuenteHasSubidoDeNivel));
		Pantalla.centerString(g, new Rectangle(x, y + offsetYFelicitaciones,
				MENU.getWidth(), 0), "¡Felicitaciones!");
		Pantalla.centerString(g, new Rectangle(x, y + offsetYNuevoNivel,
				MENU.getWidth(), 0), "Nuevo Nivel");
		g.setFont(new Font("Book Antiqua", 1, tamFuenteNuevoNivel));
		Pantalla.centerString(
				g,
				new Rectangle(
						x, y + offsetYGetNivel,
						MENU.getWidth(), 0),
				String.valueOf(
						personaje.getNivel()
						));

	}

	/**
	 * Grafica el menu Informacion
	 * @param g Graphics
	 */
	public void graficarMenuInformacion(final Graphics g) {

		// Muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(
				x, y + offsetYCentrarString,
				MENU.getWidth(), 0),
				personaje.getRaza());
		g.drawString("Casta: ", x + posXCasta, y + offsetYCasta);
		g.drawString("Nivel: ", x + posXNivel, y + offsetYNivel);
		g.drawString("Experiencia: ", x + posXExperiencia, y + offsetYExperiencia);

		// Muestro los atributos
		g.setFont(new Font("Book Antiqua", 0, tamFuenteMenu));
		g.drawString(personaje.getCasta(), x + offsetXCasta, y + offsetYCasta);
		g.drawString(personaje.getNivel() + " ", x + offsetXNivel, y + offsetYNivel);
		g.drawString(
				personaje.getExperiencia()
				+ " / " + Personaje.getTablaDeNiveles()
				[personaje.getNivel() + 1],
				x + offsetXExperiencia,
				y + offsetYExperiencia);
	}

	/**
	 * Grafica el menu Item
	 * @param g Graphics
	 */
	private void graficarMenuItem(final Graphics g) {

		// Informo que subió de nivel
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x,
				y + offsetYPrimerCenterString,
				MENU.getWidth(), 0), "¡Aca iria algo!");

		g.setFont(new Font(
				"Book Antiqua", 0, tamFuentePrimerCenterString));
		Pantalla.centerString(g, new Rectangle(
				x, y + offsetYSegundoCenterString,
				MENU.getWidth(), 0), "¡Aca otra cosa!");
		Pantalla.centerString(g, new Rectangle(
				x, y + offsetYTercerCenterString,
				MENU.getWidth(), 0), "Nuevo Nivel");
		g.setFont(new Font(
				"Book Antiqua", 1, tamFuenteSegundoCenterString));
		Pantalla.centerString(
				g, new Rectangle(
						x, y + offsetYCuartoCenterString,
						MENU.getWidth(),
						0),
				String.valueOf(
						personaje.getNivel()
						));
	}

	/**
	 * Grafica el menu Comerciar
	 * @param g Graphics
	 */
	private void graficarMenuComerciar(final Graphics g) {

		// Muestro los nombres de los atributos
		g.setColor(Color.BLACK);
		Pantalla.centerString(
				g, new Rectangle(
						x,
						y + offsetYCentrarString,
						MENU.getWidth(),
						0), personaje.getRaza());
		g.drawString("Casta: ",
				x + posXCasta,
				y + offsetYCasta);
		g.drawString("Nivel: ",
				x + posXNivel,
				y + offsetYNivel);
		g.drawString("Experiencia: ",
				x + posXExperiencia,
				y + offsetYExperiencia);
		// Muestro los atributos
		g.setFont(
				new Font(
						"Book Antiqua",
						0, tamFuenteMenu));
		g.drawString(personaje.getCasta(),
				x + offsetXCasta, y + offsetYCasta);
		g.drawString(personaje.getNivel() + " ",
				x + offsetXNivel, y + offsetYNivel);
		g.drawString(
				personaje.getExperiencia()
				+ " / " + Personaje.
				getTablaDeNiveles()[personaje.getNivel() + 1],
				x + offsetXExperiencia,
				y + offsetYExperiencia);
	}
	/**
	 * Devuelve si hubo un click en Boton
	 * @param mouseX posicion X del mouse
	 * @param mouseY posicion Y del mouse
	 * @return boolean
	 */
	public boolean clickEnBoton(
			final int mouseX,
			final int mouseY) {
		return (
				mouseX >= x + offsetXMinBoton
				&& mouseX <= x + offsetXMaxBoton
				&& mouseY >= y + offsetYMinBoton
				&& mouseY <= y + offsetYMaxBoton);
	}
	/**
	 * Devuelve si hubo un click en Asignar Skills
	 * @param mouseX posicion X del mouse
	 * @param mouseY posicion Y del mouse
	 * @return boolean
	 */
	public boolean clickEnAsignarSkills(
			final int mouseX,
			final int mouseY) {
		return (mouseX >= x + offsetXMinMenuAsigSkill
				&& mouseX <= x + offsetXMaxMenuAsigSkill
				&& mouseY >= y + offsetYMinMenuAsigSkill
				&& mouseY <= y + offsetYMaxMenuAsigSkill);
	}
	/**
	 * Devuelve si hubo un click en cerrar
	 * @param mouseX posicion X del mouse
	 * @param mouseY posicion Y del mouse
	 * @return boolean
	 */
	public boolean clickEnCerrar(
			final int mouseX,
			final int mouseY) {
		return (
				mouseX >= x + MENU.getWidth() - offsetXMinCerrar
				&& mouseX <= x + MENU.getWidth() + offsetXMaxCerrar
				&& mouseY >= y + offsetYMinCerrar
				&& mouseY <= y + offsetYMaxCerrar);
	}
	/**
	 * Devuelve si hubo un click en menu
	 * @param mouseX posicion X del mouse
	 * @param mouseY posicion Y del mouse
	 * @return boolean
	 */
	public boolean clickEnMenu(
			final int mouseX,
			final int mouseY) {
		return (mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight());
	}
}
