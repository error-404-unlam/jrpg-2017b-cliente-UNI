package edu.unlam.wome.mundo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.unlam.wome.cliente.recursos.Recursos;
import edu.unlam.wome.estados.Estado;
import edu.unlam.wome.juego.Juego;
import edu.unlam.wome.juego.Pantalla;
import edu.unlam.wome.mensajeria.PaqueteMovimiento;
import edu.unlam.wome.mensajeria.PaquetePersonaje;

/**
 * Clase encargada de gestionar el Mundo
 * @author Miguel
 */
public class Mundo {
	private Juego juego;
	private int ancho;
	private int alto;
	private int spawnX;
	private int spawnY;
	private int xOffset;
	private int yOffset;

	private float[] iso = new float[2];
	private int[][] tiles;
	private int[][] tilesInv;

	private int xMinimo;
	private int xMaximo;
	private int yMinimo;
	private int yMaximo;

	private Grafo grafoNoClip;
	private Grafo grafoDeTilesNoSolidos;
	private final int offsetUtilitariasAncho = 0;
	private final int offsetUtilitariasAlto = 1;
	private final int offsetUtilitariasSpawnX = 2;
	private final int offsetUtilitariasSpawnY = 3;
	private final int offsetUtilitariasTiles = 4;
	private final int tileAubenor = 1;
	private final int tileAris = 2;
	private final int tileAubenorBase = 3;
	private final int anchoPersonaje = 64;
	private final int altoPersonaje = 64;
	private final int anchoRectangulo = 0;
	private final int altoRectangulo = 10;
	private final int offsetXCenterString = 32;
	private final int offsetYCenterString = 20;
	private final int anchoBaldosa = 64;
	private final int altoBaldosa = 64;
	private final int offsetYBaldosa = 32;
	private final int offsetXSuelo = 30;
	private final int offsetYSuelo = 60;
	private final int tamLetra = 15;
	private final int direccion4 = 4;
	private final int direccion5 = 5;
	private final int direccion6 = 6;
	private final int direccion7 = 7;
	/**
	 * Constructor
	 * @param juego Actual
	 * @param pathMap Ruta Mapa
	 * @param pathObstac Ruta Obstaculos
	 */
	public Mundo(final Juego juego, final String pathMap, final String pathObstac) {
		this.juego = juego;
		cargarMundo(pathMap, pathObstac);
		mundoAGrafo();
		mundoAGrafoNoClip();
	}

	/**
	 * actualizar
	 */
	public void actualizar() {

	}

	/**
	 * Grafica el suelo
	 * @param g Graphics
	 */
	public void graficarSuelo(final Graphics g) {
		xOffset = juego.getEstadoJuego().getPersonaje().getxOffset();
		yOffset = juego.getEstadoJuego().getPersonaje().getYOffset();

		xMinimo = (int) (juego.getCamara().getxOffset() - xOffset - offsetXSuelo);
		xMaximo = xMinimo + juego.getAncho() + xOffset + offsetXSuelo;
		yMinimo = (int) juego.getCamara().getyOffset() + yOffset - offsetYSuelo;
		yMaximo = yMinimo + juego.getAlto() + yOffset + offsetYSuelo;

		// Grafico el el tile base
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				iso = convertir2DaISO(j, i);
				if ((iso[0] >= xMinimo
						&& iso[0] <= xMaximo)
						&& (iso[1] >= yMinimo
						&& iso[1] <= yMaximo)) {
					int map = juego.getPersonaje().getMapa();
					if (map == tileAubenor) {
						Tile.getAubenor()[Tile.getAubenorBase()].
						graficar(g,
								(int)
								(iso[0] - juego.getCamara().getxOffset()),
								(int)
								(iso[1] - juego.getCamara().getyOffset()
										- offsetYBaldosa),
								anchoBaldosa, altoBaldosa);
					} else if (map == tileAris) {
						Tile.getAris()[Tile.getArisBase()].
						graficar(g,
								(int)
								(iso[0] - juego.getCamara().getxOffset()),
								(int)
								(iso[1] - juego.getCamara().getyOffset()
										- offsetYBaldosa),
								anchoBaldosa, altoBaldosa);
					} else if (map == tileAubenorBase) {
						Tile.getAubenor()[Tile.getAubenorBase()].
						graficar(g,
								(int)
								(iso[0] - juego.getCamara().getxOffset()),
								(int)
								(iso[1] - juego.getCamara().getyOffset()
										- offsetYBaldosa),
								anchoBaldosa, altoBaldosa);
					}
					if (!getTile(j, i).esSolido()) {
						getTile(j, i).graficar(
								g,
								(int)
								(iso[0] - juego.getCamara().getxOffset()),
								(int)
								(iso[1] - juego.getCamara().getyOffset()
										- offsetYBaldosa),
								anchoBaldosa, altoBaldosa);
					}
				}
			}
		}
	}

	/**
	 * Grafica Obstaculos
	 * @param g Graphics
	 */
	public void graficarObstaculos(final Graphics g) {
		Map<Integer, PaqueteMovimiento> ubicacionPersonajes = null;
		Map<Integer, PaquetePersonaje> personajesConectados = null;
		int jPersonaje;
		int iPersonaje;
		boolean haySolidoArriba;
		boolean haySolidoAbajo;
		Tile obst;
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {

				// Se grafican los obstáculos sólidos
				iso = convertir2DaISO(j, i);
				if ((iso[0] >= xMinimo
						&& iso[0] <= xMaximo)
						&& (iso[1] >= yMinimo
						&& iso[1] <= yMaximo)
						&& getTile(j, i).esSolido()) {
					obst = getTile(j, i);
					obst.graficar(
							g,
							(int)
							(iso[0] - juego.getCamara().getxOffset()),
							(int)
							(iso[1] - juego.getCamara().getyOffset() - obst.getAlto() / 2),
							obst.getAncho(),
							obst.getAlto());
				}

				// Se grafica el personaje, teniendo en cuenta si es adyacente a
				// un obstáculo sólido
				jPersonaje = Mundo.mouseATile(
						juego.getUbicacionPersonaje().getPosX(),
						juego.getUbicacionPersonaje().getPosY())[0];
				iPersonaje = Mundo.mouseATile(
						juego.getUbicacionPersonaje().getPosX(),
						juego.getUbicacionPersonaje().getPosY())[1];

				/*
				 * Parche temporal
				 * Bug a solucionar:
				 * 			Las coordenadas del personaje no se
				 * 			actualizan apropiadamente
				 * 			luego de un movimiento
				 * Será necesario remover
				 * el parche una vez solucionado el bug
				 */
				if (juego.getUbicacionPersonaje().getDireccion() == 0) {
					iPersonaje++;
				}
				if (juego.getUbicacionPersonaje().getDireccion()
						== direccion4) {
					jPersonaje++;
				}
				if (juego.getUbicacionPersonaje().getDireccion()
						== direccion5) {
					jPersonaje++;
				}
				if (juego.getUbicacionPersonaje().getDireccion()
						== direccion6) {
					jPersonaje++;
					iPersonaje++;
				}
				if (juego.getUbicacionPersonaje().getDireccion()
						== direccion7) {
					iPersonaje++;
				}
				/*
				 * -------------------------------------------------
				 */

				try {
					haySolidoAbajo = getTile(jPersonaje + 1, iPersonaje).esSolido();
				} catch (Exception e) {
					haySolidoAbajo = false;
				}

				try {
					haySolidoArriba = getTile(jPersonaje - 1, iPersonaje).esSolido();
				} catch (Exception e) {
					haySolidoArriba = false;
				}

				if (((haySolidoAbajo == haySolidoArriba)
						&& (j == jPersonaje && i == iPersonaje))
						|| ((haySolidoAbajo && !haySolidoArriba)
								&& (j == jPersonaje
								&& i == iPersonaje - 1))
						|| ((haySolidoArriba && !haySolidoAbajo)
								&& (j == jPersonaje
								&& i == iPersonaje + 1))) {
					juego.getEstadoJuego().getPersonaje().graficar(g);
					juego.getEstadoJuego().getPersonaje().graficarNombre(g);
				}

				// Se grafican los otros personajes, teniendo en cuenta si son
				// adyacentes a un obstáculo sólido, y teniendo en cuenta si
				// están en el mismo mapa
				if (juego.getPersonajesConectados() != null) {
					try {
						personajesConectados = new HashMap<Integer, PaquetePersonaje>(
								juego.getPersonajesConectados());
						ubicacionPersonajes = new HashMap<Integer, PaqueteMovimiento>(
								juego.getUbicacionPersonajes());
					} catch (Exception e) {
						e.printStackTrace();
					}
					Iterator<Integer> it = personajesConectados.keySet().iterator();
					int key;
					PaqueteMovimiento actual;
					g.setColor(Color.WHITE);
					g.setFont(
							new Font(
									"Book Antiqua",
									Font.PLAIN,
									tamLetra));
					while (it.hasNext()) {
						key = it.next();
						actual = ubicacionPersonajes.get(key);
						if (actual != null && actual.getIdPersonaje()
								!= juego.getPersonaje().getId()
								&& personajesConectados.get(
										actual.getIdPersonaje()).
								getEstado() == Estado.getEstadoJuego()
								&& personajesConectados.get(
										actual.getIdPersonaje()).getMapa()
								== juego.getPersonaje().getMapa()) {
							jPersonaje = Mundo.mouseATile(
									actual.getPosX(), actual.getPosY())[0];
							iPersonaje = Mundo.mouseATile(
									actual.getPosX(), actual.getPosY())[1];

							/*
							 * Parche temporal
							 * Bug a solucionar:
							 * 		Las coordenadas del personaje
							 * 		no se actualizan apropiadamente
							 * 		luego de un movimiento
							 * Será necesario remover el parche
							 * una vez solucionado el bug
							 */
							if (juego.getUbicacionPersonaje().getDireccion() == 0) {
								iPersonaje++;
							}
							if (juego.getUbicacionPersonaje().getDireccion()
									== direccion4) {
								jPersonaje++;
							}
							if (juego.getUbicacionPersonaje().getDireccion()
									== direccion5) {
								jPersonaje++;
							}
							if (juego.getUbicacionPersonaje().getDireccion()
									== direccion6) {
								jPersonaje++;
								iPersonaje++;
							}
							if (juego.getUbicacionPersonaje().getDireccion()
									== direccion7) {
								iPersonaje++;
							}
							/*
							 * -------------------------------------------------
							 */

							try {
								haySolidoAbajo = getTile(
										jPersonaje + 1, iPersonaje).
										esSolido();
							} catch (Exception e) {
								haySolidoAbajo = false;
							}

							try {
								haySolidoArriba = getTile(
										jPersonaje - 1, iPersonaje).
										esSolido();
							} catch (Exception e) {
								haySolidoArriba = false;
							}

							if (((haySolidoAbajo == haySolidoArriba)
									&& (j == jPersonaje && i == iPersonaje))
									|| ((haySolidoAbajo && !haySolidoArriba)
											&& (j == jPersonaje
											&& i == iPersonaje - 1))
									|| ((haySolidoArriba && !haySolidoAbajo)
											&& (j == jPersonaje
											&& i == iPersonaje + 1))) {
								Pantalla.centerString(g, new Rectangle(
										(int)
										(actual.getPosX()
												- juego.getCamara().
												getxOffset()
												+ offsetXCenterString),
										(int)
										(actual.getPosY()
												- juego.getCamara().
												getyOffset()
												- offsetYCenterString),
										anchoRectangulo, altoRectangulo),
										personajesConectados.get(
												actual.getIdPersonaje()
												).
										getNombre());
								g.drawImage(
										Recursos.getPersonaje().get(
												personajesConectados.
												get(actual.getIdPersonaje()).
												getRaza()).
										get(actual.getDireccion())
										[actual.getFrame()],
										(int)
										(actual.getPosX() - juego.getCamara().
												getxOffset()),
										(int)
										(actual.getPosY() - juego.getCamara().
												getyOffset()),
										anchoPersonaje, altoPersonaje, null);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Devuelve la baldosa
	 * @param x baldosa
	 * @param y baldosa
	 * @return Tile
	 */
	public Tile getTile(final int x, final int y) {
		Tile t = Tile.getTiles()[tiles[x][y]];
		if (t == null) {
			int map = juego.getPersonaje().getMapa();
			if (map == tileAubenor) {
				return Tile.getAubenor()[Tile.getAubenorBase()];
			} else if (map == tileAris) {
				return Tile.getAris()[Tile.getArisBase()];
			} else if (map == tileAubenorBase) {
				return Tile.getAubenor()[Tile.getAubenorBase()];
			}
		}
		return t;
	}

	/**
	 * Carga el mundo
	 * @param pathMapa ruta del mapa
	 * @param pathObstaculos ruta obstaculos
	 */
	private void cargarMundo(final String pathMapa, final String pathObstaculos) {
		String archivo = Utilitarias.archivoAString(pathMapa);
		String[] tokens = archivo.split("\\s+");
		ancho = Utilitarias.parseInt(
				tokens[offsetUtilitariasAncho]);
		alto = Utilitarias.parseInt(
				tokens[offsetUtilitariasAlto]);
		spawnX = Utilitarias.parseInt(
				tokens[offsetUtilitariasSpawnX]);
		spawnY = Utilitarias.parseInt(
				tokens[offsetUtilitariasSpawnY]);

		tiles = new int[ancho][alto];
		tilesInv = new int[alto][ancho];
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				tiles[x][y] = Utilitarias.parseInt(
						tokens[
						       (x + y * ancho + offsetUtilitariasTiles)
						       ]);
				tilesInv[y][x] = tiles[x][y];
			}
		}
	}

	/**
	 * Convierte el mundo a grafo
	 */
	private void mundoAGrafo() {
		// Creo una matriz de nodos
		Nodo[][] nodos = new Nodo[ancho][alto];
		int indice = 0;
		// Lleno la matriz con los nodos
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				nodos[y][x] = new Nodo(indice++, x, y);
			}
		}
		// Variables finales
		int xFinal = ancho;
		int yFinal = alto;
		// Uno cada nodo con sus adyacentes
		for (int x = 0; x < yFinal; x++) {
			for (int y = 0; y < xFinal; y++) {
				if (!Tile.getTiles()[tilesInv[x][y]].esSolido()) {
					// Si no es la ultima fila y el tile de abajo es no solido,
					// lo uno
					if (y < yFinal - 1 && !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
						nodos[x][y].agregarAdyacente(nodos[x][y + 1]);
						nodos[x][y + 1].agregarAdyacente(nodos[x][y]);
					}
					// Si no es la ultima columna
					if (x < xFinal - 1) {
						// Si el de arriba a la derecha no es un tile solido
						// Y ademas el de arriba ni el de la derecha lo son, lo
						// uno
						// Tiene que ser a partir de la segunda fila
						if (y > 0
								&& !Tile.getTiles()[tilesInv[x + 1][y - 1]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x][y - 1]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y - 1]);
							nodos[x + 1][y - 1].agregarAdyacente(nodos[x][y]);
						}
						// Si el de la derecha no es un tile solido lo uno
						if (!Tile.getTiles()[tilesInv[x + 1][y]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y]);
							nodos[x + 1][y].agregarAdyacente(nodos[x][y]);
						}
						// Si el de abajo a la derecha no es un tile solido
						// Y ademas el de abajo ni el de la derecha lo son, lo
						// uno
						// Debe ser antes de la ultima fila
						if (y < yFinal - 1
								&& !Tile.getTiles()[tilesInv[x + 1][y + 1]].esSolido()
								&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
								&& !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y + 1]);
							nodos[x + 1][y + 1].agregarAdyacente(nodos[x][y]);
						}
					}
				}
			}
		}
		// Creo un grafo para almacenar solo los tiles no solidos
		grafoDeTilesNoSolidos = new Grafo(ancho * alto);
		indice = 0;
		// Paso la matriz a un array
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				grafoDeTilesNoSolidos.agregarNodo(nodos[i][j]);
			}
		}
	}

	/**
	 * Devuelve el grafo de tiles no solidos
	 * @return Grafo
	 */
	public Grafo obtenerGrafoDeTilesNoSolidos() {
		return grafoDeTilesNoSolidos;
	}
	
	private void mundoAGrafoNoClip() {
		// Creo una matriz de nodos
		Nodo[][] nodos = new Nodo[ancho][alto];
		int indice = 0;
		// Lleno la matriz con los nodos
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				nodos[y][x] = new Nodo(indice++, x, y);
			}
		}
		// Variables finales
		int xFinal = ancho;
		int yFinal = alto;
		// Uno cada nodo con sus adyacentes
		for (int x = 0; x < yFinal; x++) {
			for (int y = 0; y < xFinal; y++) {
				 
					// Si no es la ultima fila
					if (y < yFinal - 1) {
						nodos[x][y].agregarAdyacente(nodos[x][y + 1]);
						nodos[x][y + 1].agregarAdyacente(nodos[x][y]);
					}
					// Si no es la ultima columna
					if (x < xFinal - 1) {
						
						// Tiene que ser a partir de la segunda fila
						if (y > 0) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y - 1]);
							nodos[x + 1][y - 1].agregarAdyacente(nodos[x][y]);
						}
						
						nodos[x][y].agregarAdyacente(nodos[x + 1][y]);
						nodos[x + 1][y].agregarAdyacente(nodos[x][y]);
						
						// Debe ser antes de la ultima fila
						if (y < yFinal - 1) {
							nodos[x][y].agregarAdyacente(nodos[x + 1][y + 1]);
							nodos[x + 1][y + 1].agregarAdyacente(nodos[x][y]);
						}
					}	
			}
		}
		// Creo un grafo para almacenar solo los tiles
		grafoNoClip = new Grafo(ancho * alto);
		indice = 0;
		// Paso la matriz a un array
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				grafoNoClip.agregarNodo(nodos[i][j]);
			}
		}
	}
	
	public Grafo obtenerGrafoNoClip() {
		return grafoNoClip;
	}
	

	/**
	 * Devuelve el ancho
	 * @return int
	 */
	public int obtenerAncho() {
		return ancho;
	}

	/**
	 * Devuelve el alto
	 * @return int
	 */
	public int obtenerAlto() {
		return alto;
	}

	/**
	 * Convierte Isometrica a 2d
	 * @param x X Isometrica
	 * @param y Y Isometrica
	 * @return float[]
	 */
	public static float[] convertirISOa2D(final float x, final float y) {
		float[] dosD = new float[2];

		dosD[0] = (x / (Tile.ANCHO / 2) + y / (Tile.ALTO / 2)) / 2;
		dosD[1] = (y / (Tile.ALTO / 2) - (x / (Tile.ANCHO / 2))) / 2;

		return dosD;
	}

	/**
	 * Convierte 2d a Isometrica
	 * @param x X
	 * @param y Y
	 * @return float[]
	 */
	public static float[] convertir2DaISO(final float x, final float y) {
		float[] iso = new float[2];

		iso[0] = (x - y) * (Tile.ANCHO / 2);
		iso[1] = (x + y) * (Tile.ALTO / 2);

		return iso;
	}

	/**
	 * Convierte el mouse a Tile
	 * @param x Mouse
	 * @param y Mouse
	 * @return int
	 */
	public static int[] mouseATile(final float x, final float y) {
		int []tile = new int[2];

		tile[0] = (int) Math.floor((y / Tile.ALTO) + (x / Tile.ANCHO)) + 1;
		tile[1] = (int) Math.floor((-x / Tile.ANCHO) + (y / Tile.ALTO)) + 1;

		return tile;
	}
}
