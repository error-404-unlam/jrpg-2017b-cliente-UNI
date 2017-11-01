package recursos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;

import frames.MenuCarga;
import frames.MenuMapas;
import mundo.Tile;

/**
 * Clase encargada de la gestion de los recursos.
 * 
 * @author Miguel
 */
public class Recursos {

	private static final int ELEMENTOS = 65;
	private static final int ANCHOBARRA = 345;

	private static int ancho; // Ancho del frame a obtener
	private static int alto; // Alto del frame a obtener

	// Inicio Personajes
	// Hash de imagenes para los personajes (humano, ogro, elfo)
	private static Map<String, LinkedList<BufferedImage[]>> personaje = new HashMap<>();

	private static SpriteSheet spriteHumano;
	private static LinkedList<BufferedImage[]> humano = new LinkedList<>();
	private static BufferedImage[] humanoIzq;
	private static BufferedImage[] humanoArribaIzq;
	private static BufferedImage[] humanoArriba;
	private static BufferedImage[] humanoArribaDer;
	private static BufferedImage[] humanoDer;
	private static BufferedImage[] humanoAbajoDer;
	private static BufferedImage[] humanoAbajo;
	private static BufferedImage[] humanoAbajoIzq;

	private static SpriteSheet spriteOgro;
	private static LinkedList<BufferedImage[]> orco = new LinkedList<>();
	private static BufferedImage[] orcoIzq;
	private static BufferedImage[] orcoArribaIzq;
	private static BufferedImage[] orcoArriba;
	private static BufferedImage[] orcoArribaDer;
	private static BufferedImage[] orcoDer;
	private static BufferedImage[] orcoAbajoDer;
	private static BufferedImage[] orcoAbajo;
	private static BufferedImage[] orcoAbajoIzq;

	private static SpriteSheet spriteElfo;
	private static LinkedList<BufferedImage[]> elfo = new LinkedList<>();
	private static BufferedImage[] elfoIzq;
	private static BufferedImage[] elfoArribaIzq;
	private static BufferedImage[] elfoArriba;
	private static BufferedImage[] elfoArribaDer;
	private static BufferedImage[] elfoDer;
	private static BufferedImage[] elfoAbajoDer;
	private static BufferedImage[] elfoAbajo;
	private static BufferedImage[] elfoAbajoIzq;
	// Fin Personajes

	// Entorno
	private static SpriteSheet trees;
	private static BufferedImage cesped;
	private static BufferedImage roca;
	private static BufferedImage background;
	private static BufferedImage marco;
	private static BufferedImage botonMenu;
	private static BufferedImage menuEnemigo;
	private static BufferedImage greenTree;
	private static BufferedImage nievePiso1;
	private static BufferedImage iceBlock;
	// Fin Entorno

	// Batalla
	private static BufferedImage barraSpells;
	private static BufferedImage estadoPersonaje;
	private static BufferedImage barraSalud;
	private static BufferedImage barraEnergia;
	private static BufferedImage barraExperiencia;
	private static BufferedImage menuBatalla;
	private static BufferedImage menuBatallaDeshabilitado;
	private static BufferedImage noItem;
	private static BufferedImage mochila;
	private static BufferedImage menu;
	private static BufferedImage chat;
	private static Map<String, BufferedImage> habilidades = new HashMap<>();
	// Fin Batalla
	private static final int X_MAPA_AUBENOR = 64;
	private static final int Y_MAPA_AUBENOR = 64;
	private static final int ANCHO_MAPA_AUBENOR = 64;
	private static final int ALTO_MAPA_AUBENOR = 64;
	private static final int LIMITE_X_AUBENOR = 10;
	private static final int LIMITE_Y_AUBENOR = 8;
	private static final int X_MAPA_ARIS = 64;
	private static final int Y_MAPA_ARIS = 64;
	private static final int ANCHO_MAPA_ARIS = 64;
	private static final int ALTO_MAPA_ARIS = 64;
	private static final int LIMITE_X_ARIS = 10;
	private static final int LIMITE_Y_ARIS = 8;
	private static final int ANCHO_TILE = 64;
	private static final int ALTO_TILE = 64;
	private static final int ID_X_AUBENOR = 1;
	private static final int ID_Y_AUBENOR = 10;
	private static final int ID_X_ARIS = 1;
	private static final int ID_Y_ARIS = 10;
	private static final int TAM_TILE_ARRAY = 81;
	private static final int X_TILE_TREE = 0;
	private static final int Y_TILE_TREE = 0;
	private static final int ANCHO_TILE_TREE = 42;
	private static final int ALTO_TILE_TREE = 50;
	private static final int TAM_VECTOR_ORCO = 4;
	private static final int TAM_VECTOR_HUMANO = 4;
	private static final int TAM_VECTOR_ELFO = 4;
	private static final int ALTO_VECTOR_2 = 2;
	private static final int ALTO_VECTOR_3 = 3;
	private static final int ALTO_VECTOR_4 = 4;
	private static final int ALTO_VECTOR_5 = 5;
	private static final int ALTO_VECTOR_6 = 6;
	private static final int ALTO_VECTOR_7 = 7;
	private static final int ANCHO_CARGA = 256;
	private static final int ALTO_CARGA = 256;

	public Recursos() {
		//no utilizado
	}

	// Se cargan todos los recursos del juego una sola vez al inicio

	/**
	 * Metodo cargar
	 * 
	 * @param menuCarga
	 *            menu a cargar
	 * @throws IOException
	 *             excepcion entrada salida
	 */
	public static void cargar(final MenuCarga menuCarga) throws IOException {

		int elementosCargados = 0;

		ancho = ANCHO_CARGA;
		alto = ALTO_CARGA;
		// Items

		noItem = ImageIO.read(new File("recursos//noItem.png"));
		mochila = ImageIO.read(new File("recursos//mochila.png"));
		menu = ImageIO.read(new File("recursos//menu.png"));
		chat = ImageIO.read(new File("recursos//chat.png"));

		// Inicio humano
		spriteHumano = new SpriteSheet(CargadorImagen.cargarImagen("/Humano.png"));

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		humanoIzq = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoArribaIzq = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoArriba = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoArribaDer = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoDer = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoAbajoDer = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoAbajo = new BufferedImage[TAM_VECTOR_HUMANO];
		humanoAbajoIzq = new BufferedImage[TAM_VECTOR_HUMANO];

		for (int i = 0; i < humanoIzq.length; i++) {
			humanoIzq[i] = spriteHumano.getTile(ancho * i, 0, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoArribaIzq.length; i++) {
			humanoArribaIzq[i] = spriteHumano.getTile(ancho * i, alto, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoArriba.length; i++) {
			humanoArriba[i] = spriteHumano.getTile(ancho * i, alto * ALTO_VECTOR_2, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoArribaDer.length; i++) {
			humanoArribaDer[i] = spriteHumano.getTile(ancho * i, alto * ALTO_VECTOR_3, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoDer.length; i++) {
			humanoDer[i] = spriteHumano.getTile(ancho * i, alto * ALTO_VECTOR_4, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoAbajoDer.length; i++) {
			humanoAbajoDer[i] = spriteHumano.getTile(ancho * i, alto * ALTO_VECTOR_5, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoAbajo.length; i++) {
			humanoAbajo[i] = spriteHumano.getTile(ancho * i, alto * ALTO_VECTOR_6, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < humanoAbajoIzq.length; i++) {
			humanoAbajoIzq[i] = spriteHumano.getTile(ancho * i, alto * ALTO_VECTOR_7, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		humano.add(humanoIzq);
		humano.add(humanoArribaIzq);
		humano.add(humanoArriba);
		humano.add(humanoArribaDer);
		humano.add(humanoDer);
		humano.add(humanoAbajoDer);
		humano.add(humanoAbajo);
		humano.add(humanoAbajoIzq);
		// Fin humano

		// Inicio Ogro
		spriteOgro = new SpriteSheet(CargadorImagen.cargarImagen("/Ogro.png"));

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		orcoIzq = new BufferedImage[TAM_VECTOR_ORCO];
		orcoArribaIzq = new BufferedImage[TAM_VECTOR_ORCO];
		orcoArriba = new BufferedImage[TAM_VECTOR_ORCO];
		orcoArribaDer = new BufferedImage[TAM_VECTOR_ORCO];
		orcoDer = new BufferedImage[TAM_VECTOR_ORCO];
		orcoAbajoDer = new BufferedImage[TAM_VECTOR_ORCO];
		orcoAbajo = new BufferedImage[TAM_VECTOR_ORCO];
		orcoAbajoIzq = new BufferedImage[TAM_VECTOR_ORCO];

		for (int i = 0; i < orcoIzq.length; i++) {
			orcoIzq[i] = spriteOgro.getTile(ancho * i, 0, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoArribaIzq.length; i++) {
			orcoArribaIzq[i] = spriteOgro.getTile(ancho * i, alto, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoArriba.length; i++) {
			orcoArriba[i] = spriteOgro.getTile(ancho * i, alto * ALTO_VECTOR_2, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoArribaDer.length; i++) {
			orcoArribaDer[i] = spriteOgro.getTile(ancho * i, alto * ALTO_VECTOR_3, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoDer.length; i++) {
			orcoDer[i] = spriteOgro.getTile(ancho * i, alto * ALTO_VECTOR_4, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoAbajoDer.length; i++) {
			orcoAbajoDer[i] = spriteOgro.getTile(ancho * i, alto * ALTO_VECTOR_5, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoAbajo.length; i++) {
			orcoAbajo[i] = spriteOgro.getTile(ancho * i, alto * ALTO_VECTOR_6, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < orcoAbajoIzq.length; i++) {
			orcoAbajoIzq[i] = spriteOgro.getTile(ancho * i, alto * ALTO_VECTOR_7, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		orco.add(orcoIzq);
		orco.add(orcoArribaIzq);
		orco.add(orcoArriba);
		orco.add(orcoArribaDer);
		orco.add(orcoDer);
		orco.add(orcoAbajoDer);
		orco.add(orcoAbajo);
		orco.add(orcoAbajoIzq);

		// Fin Ogro

		// Inicio Elfo
		spriteElfo = new SpriteSheet(CargadorImagen.cargarImagen("/elfo2.png"));

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		elfoIzq = new BufferedImage[TAM_VECTOR_ELFO];
		elfoArribaIzq = new BufferedImage[TAM_VECTOR_ELFO];
		elfoArriba = new BufferedImage[TAM_VECTOR_ELFO];
		elfoArribaDer = new BufferedImage[TAM_VECTOR_ELFO];
		elfoDer = new BufferedImage[TAM_VECTOR_ELFO];
		elfoAbajoDer = new BufferedImage[TAM_VECTOR_ELFO];
		elfoAbajo = new BufferedImage[TAM_VECTOR_ELFO];
		elfoAbajoIzq = new BufferedImage[TAM_VECTOR_ELFO];
		for (int i = 0; i < elfoIzq.length; i++) {
			elfoIzq[i] = spriteElfo.getTile(ancho * i, 0, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoArribaIzq.length; i++) {
			elfoArribaIzq[i] = spriteElfo.getTile(ancho * i, alto, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoArriba.length; i++) {
			elfoArriba[i] = spriteElfo.getTile(ancho * i, alto * 2, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoArribaDer.length; i++) {
			elfoArribaDer[i] = spriteElfo.getTile(ancho * i, alto * ALTO_VECTOR_3, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoDer.length; i++) {
			elfoDer[i] = spriteElfo.getTile(ancho * i, alto * ALTO_VECTOR_4, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoAbajoDer.length; i++) {
			elfoAbajoDer[i] = spriteElfo.getTile(ancho * i, alto * ALTO_VECTOR_5, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoAbajo.length; i++) {
			elfoAbajo[i] = spriteElfo.getTile(ancho * i, alto * ALTO_VECTOR_6, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		for (int i = 0; i < elfoAbajoIzq.length; i++) {
			elfoAbajoIzq[i] = spriteElfo.getTile(ancho * i, alto * ALTO_VECTOR_7, ancho, alto);
		}

		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		elfo.add(elfoIzq);
		elfo.add(elfoArribaIzq);
		elfo.add(elfoArriba);
		elfo.add(elfoArribaDer);
		elfo.add(elfoDer);
		elfo.add(elfoAbajoDer);
		elfo.add(elfoAbajo);
		elfo.add(elfoAbajoIzq);

		// Fin Elfo

		// Agrego los pj al hash
		personaje.put("Humano", humano);
		personaje.put("Orco", orco);
		personaje.put("Elfo", elfo);

		// Inicio Entorno
		cesped = CargadorImagen.cargarImagen("/Cesped.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		roca = CargadorImagen.cargarImagen("/rock.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		background = CargadorImagen.cargarImagen("/background.jpg");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		marco = CargadorImagen.cargarImagen("/marco.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		botonMenu = CargadorImagen.cargarImagen("/botonMenu.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		menuEnemigo = CargadorImagen.cargarImagen("/MenuEnemigo.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		trees = new SpriteSheet(CargadorImagen.cargarImagen("/trees.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		greenTree = trees.getTile(X_TILE_TREE, Y_TILE_TREE, ANCHO_TILE_TREE, ALTO_TILE_TREE);
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		nievePiso1 = CargadorImagen.cargarImagen("/nieve piso.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		iceBlock = CargadorImagen.cargarImagen("/nieve cubo.png");

		// Mapa
		if (MenuMapas.getNumberMap() == 1) {
			SpriteSheet mapaAubenor = new SpriteSheet(CargadorImagen.cargarImagen("/Aubenor.png"));
			Tile.setAubenor(new Tile[TAM_TILE_ARRAY]);
			boolean[][] solidezAubenor = { { true, true, false, true, false, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true },
					{ true, false, false, false, false, false, false, false, true, true },
					{ false, false, false, false, false, false, false, false, true, true },
					{ false, true, true, true, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true } };
			for (int y = 0; y < LIMITE_Y_AUBENOR; y++) {
				for (int x = 0; x < LIMITE_X_AUBENOR; x++) {
					Tile.getAubenor()[y * ID_Y_AUBENOR + x + ID_X_AUBENOR] = new Tile(
							mapaAubenor.getTile(x * X_MAPA_AUBENOR, y * Y_MAPA_AUBENOR, ANCHO_MAPA_AUBENOR,
									ALTO_MAPA_AUBENOR),
							y * ID_Y_AUBENOR + x + ID_X_AUBENOR, solidezAubenor[y][x], ANCHO_TILE, ALTO_TILE);
				}
			}
		} else {
			SpriteSheet mapaAris = new SpriteSheet(CargadorImagen.cargarImagen("/Aris.png"));
			Tile.setAris(new Tile[TAM_TILE_ARRAY]);
			boolean[][] solidezAris = { { true, false, false, false, false, false, false, true, true, true },
					{ false, false, false, false, false, false, false, false, true, true },
					{ false, false, false, false, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true },
					{ false, true, true, true, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true },
					{ true, true, true, true, true, true, true, true, true, true } };
			for (int y = 0; y < LIMITE_Y_ARIS; y++) {
				for (int x = 0; x < LIMITE_X_ARIS; x++) {
					Tile.getAris()[y * ID_Y_ARIS + x + ID_X_ARIS] = new Tile(
							mapaAris.getTile(x * X_MAPA_ARIS, y * Y_MAPA_ARIS, ANCHO_MAPA_ARIS, ALTO_MAPA_ARIS),
							y * ID_Y_ARIS + x + ID_X_ARIS, solidezAris[y][x], ANCHO_TILE, ALTO_TILE);
				}
			}
		}

		// Fin Entorno

		// Inicio Batalla
		barraSpells = CargadorImagen.cargarImagen("/BarraSpells.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		estadoPersonaje = CargadorImagen.cargarImagen("/EstadoPersonaje.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		barraSalud = CargadorImagen.cargarImagen("/BarraDeSalud.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		barraEnergia = CargadorImagen.cargarImagen("/BarraDeEnergia.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		barraExperiencia = CargadorImagen.cargarImagen("/BarraDeExperiencia.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Level", CargadorImagen.cargarImagen("/Golpe Level.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ataque Bosque", CargadorImagen.cargarImagen("/Ataque Bosque.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Defensa", CargadorImagen.cargarImagen("/Golpe Defensa.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Mordisco de Vida", CargadorImagen.cargarImagen("/Mordisco de Vida.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Incentivar", CargadorImagen.cargarImagen("/Incentivar.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Fatal", CargadorImagen.cargarImagen("/Golpe Fatal.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ataque Doble", CargadorImagen.cargarImagen("/Ataque Doble.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Aumentar Defensa", CargadorImagen.cargarImagen("/Aumentar Defensa.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ignorar Defensa", CargadorImagen.cargarImagen("/Ignorar Defensa.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Bola de Fuego", CargadorImagen.cargarImagen("/Bola de Fuego.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Curar Aliado", CargadorImagen.cargarImagen("/Curar Aliado.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Robar Energia y Salud", CargadorImagen.cargarImagen("/Robar Energia y Salud.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Golpe Critico", CargadorImagen.cargarImagen("/Golpe Critico.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Aumentar Evasion", CargadorImagen.cargarImagen("/Aumentar Evasion.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Robar", CargadorImagen.cargarImagen("/Robar.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		habilidades.put("Ser Energizado", CargadorImagen.cargarImagen("/Ser Energizado.png"));
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		menuBatalla = CargadorImagen.cargarImagen("/MenuBatalla.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);

		menuBatallaDeshabilitado = CargadorImagen.cargarImagen("/MenuBatallaDeshabilitado.png");
		actualizarBarraDeCarga(++elementosCargados, menuCarga);
		// Fin Batalla
	}

	/**
	 * Actualiza la barra de carga
	 * 
	 * @param elementosCargados
	 *            elementos cargados
	 * @param menuCarga
	 *            menu de carga
	 */
	private static void actualizarBarraDeCarga(final int elementosCargados, final MenuCarga menuCarga) {
		menuCarga.setBarraCargando(elementosCargados * ANCHOBARRA / ELEMENTOS);
	}

	/**
	 * @return personaje
	 */
	public static Map<String, LinkedList<BufferedImage[]>> getPersonaje() {
		return personaje;
	}

	/**
	 * @return spriteHumano
	 */
	public static SpriteSheet getSpriteHumano() {
		return spriteHumano;
	}

	/**
	 * @return humano
	 */
	public static LinkedList<BufferedImage[]> getHumano() {
		return humano;
	}

	/**
	 * @return humanoIzq
	 */
	public static BufferedImage[] getHumanoIzq() {
		return humanoIzq;
	}

	/**
	 * @return humanoArribaIzq
	 */
	public static BufferedImage[] getHumanoArribaIzq() {
		return humanoArribaIzq;
	}

	/**
	 * @return humanoArriba
	 */
	public static BufferedImage[] getHumanoArriba() {
		return humanoArriba;
	}

	/**
	 * @return humanoArribaDer
	 */
	public static BufferedImage[] getHumanoArribaDer() {
		return humanoArribaDer;
	}

	/**
	 * @return humanoDer
	 */
	public static BufferedImage[] getHumanoDer() {
		return humanoDer;
	}

	/**
	 * @return humanoAbajoDer
	 */
	public static BufferedImage[] getHumanoAbajoDer() {
		return humanoAbajoDer;
	}

	/**
	 * @return humanoAbajo
	 */
	public static BufferedImage[] getHumanoAbajo() {
		return humanoAbajo;
	}

	/**
	 * @return humanoAbajoIzq
	 */
	public static BufferedImage[] getHumanoAbajoIzq() {
		return humanoAbajoIzq;
	}

	/**
	 * @return spriteOgro
	 */
	public static SpriteSheet getSpriteOgro() {
		return spriteOgro;
	}

	/**
	 * @return orco
	 */
	public static LinkedList<BufferedImage[]> getOrco() {
		return orco;
	}

	/**
	 * @return orcoIzq
	 */
	public static BufferedImage[] getOrcoIzq() {
		return orcoIzq;
	}

	/**
	 * @return orcoArribaIzq
	 */
	public static BufferedImage[] getOrcoArribaIzq() {
		return orcoArribaIzq;
	}

	/**
	 * @return orcoArriba
	 */
	public static BufferedImage[] getOrcoArriba() {
		return orcoArriba;
	}

	/**
	 * @return orcoArribaDer
	 */
	public static BufferedImage[] getOrcoArribaDer() {
		return orcoArribaDer;
	}

	/**
	 * @return orcoDer
	 */
	public static BufferedImage[] getOrcoDer() {
		return orcoDer;
	}

	/**
	 * @return orcoAbajoDer
	 */
	public static BufferedImage[] getOrcoAbajoDer() {
		return orcoAbajoDer;
	}

	/**
	 * @return orcoAbajo
	 */
	public static BufferedImage[] getOrcoAbajo() {
		return orcoAbajo;
	}

	/**
	 * @return orcoAbajoIzq
	 */
	public static BufferedImage[] getOrcoAbajoIzq() {
		return orcoAbajoIzq;
	}

	/**
	 * @return spriteElfo
	 */
	public static SpriteSheet getSpriteElfo() {
		return spriteElfo;
	}

	/**
	 * @return elfo
	 */
	public static LinkedList<BufferedImage[]> getElfo() {
		return elfo;
	}

	/**
	 * @return elfoIzq
	 */
	public static BufferedImage[] getElfoIzq() {
		return elfoIzq;
	}

	/**
	 * @return elfoArribaIzq
	 */
	public static BufferedImage[] getElfoArribaIzq() {
		return elfoArribaIzq;
	}

	/**
	 * @return elfoArriba
	 */
	public static BufferedImage[] getElfoArriba() {
		return elfoArriba;
	}

	/**
	 * @return elfoArribaDer
	 */
	public static BufferedImage[] getElfoArribaDer() {
		return elfoArribaDer;
	}

	/**
	 * @return elfoDer
	 */
	public static BufferedImage[] getElfoDer() {
		return elfoDer;
	}

	/**
	 * @return elfoAbajoDer
	 */
	public static BufferedImage[] getElfoAbajoDer() {
		return elfoAbajoDer;
	}

	/**
	 * @return elfoAbajo
	 */
	public static BufferedImage[] getElfoAbajo() {
		return elfoAbajo;
	}

	/**
	 * @return elfoAbajoIzq
	 */
	public static BufferedImage[] getElfoAbajoIzq() {
		return elfoAbajoIzq;
	}

	/**
	 * @return trees
	 */
	public static SpriteSheet getTrees() {
		return trees;
	}

	/**
	 * @return cesped
	 */
	public static BufferedImage getCesped() {
		return cesped;
	}

	/**
	 * @return roca
	 */
	public static BufferedImage getRoca() {
		return roca;
	}

	/**
	 * @return background
	 */
	public static BufferedImage getBackground() {
		return background;
	}

	/**
	 * @return marco
	 */
	public static BufferedImage getMarco() {
		return marco;
	}

	/**
	 * @return botonMenu
	 */
	public static BufferedImage getBotonMenu() {
		return botonMenu;
	}

	/**
	 * @return menuEnemigo
	 */
	public static BufferedImage getMenuEnemigo() {
		return menuEnemigo;
	}

	/**
	 * @return greenTree
	 */
	public static BufferedImage getGreenTree() {
		return greenTree;
	}

	/**
	 * @return nievePiso1
	 */
	public static BufferedImage getNievePiso1() {
		return nievePiso1;
	}

	/**
	 * @return iceBlock
	 */
	public static BufferedImage getIceBlock() {
		return iceBlock;
	}

	/**
	 * @return barraSpells
	 */
	public static BufferedImage getBarraSpells() {
		return barraSpells;
	}

	/**
	 * @return estadoPersonaje
	 */
	public static BufferedImage getEstadoPersonaje() {
		return estadoPersonaje;
	}

	/**
	 * @return barraSalud
	 */
	public static BufferedImage getBarraSalud() {
		return barraSalud;
	}

	/**
	 * @return barraEnergia
	 */
	public static BufferedImage getBarraEnergia() {
		return barraEnergia;
	}

	/**
	 * @return barraExperiencia
	 */
	public static BufferedImage getBarraExperiencia() {
		return barraExperiencia;
	}

	/**
	 * @return menuBatalla
	 */
	public static BufferedImage getMenuBatalla() {
		return menuBatalla;
	}

	/**
	 * @return menuBatallaDeshabilitado
	 */
	public static BufferedImage getMenuBatallaDeshabilitado() {
		return menuBatallaDeshabilitado;
	}

	/**
	 * @return noItem
	 */
	public static BufferedImage getNoItem() {
		return noItem;
	}

	/**
	 * @return mochila
	 */
	public static BufferedImage getMochila() {
		return mochila;
	}

	/**
	 * @return menu
	 */
	public static BufferedImage getMenu() {
		return menu;
	}

	/**
	 * @return chat
	 */
	public static BufferedImage getChat() {
		return chat;
	}

	/**
	 * @return habilidades
	 */
	public static Map<String, BufferedImage> getHabilidades() {
		return habilidades;
	}
}
