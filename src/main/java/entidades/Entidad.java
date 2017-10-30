package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import chat.VentanaContactos;
import estados.Estado;
import frames.MenuAsignarSkills;
import frames.MenuEscape;
import frames.MenuInventario;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMovimiento;
import mundo.Grafo;
import mundo.Mundo;
import mundo.Nodo;
import recursos.Recursos;

/**
 * Clase Entidad
 */
public class Entidad {

	Juego juego;

	// Tamaño de la entidad
	private int ancho;
	private int alto;

	// Posiciones
	private float x;
	private float y;
	private float dx;
	private float dy;
	private float xInicio;
	private float yInicio;
	private float xFinal;
	private float yFinal;
	private int xOffset;
	private int yOffset;
	private int drawX;
	private int drawY;
	private int[] posMouseRecorrido;
	private int[] posMouse;
	private int[] tile;

	// Movimiento actual
	private static final int horizontalDer = 4;
	private static final int horizontalIzq = 0;
	private static final int verticalSup = 2;
	private static final int verticalInf = 6;
	private static final int diagonalInfIzq = 7;
	private static final int diagonalInfDer = 5;
	private static final int diagonalSupDer = 3;
	private static final int diagonalSupIzq = 1;
	private int movimientoHacia = 6;
	private boolean enMovimiento;

	// Animaciones

	private final Animacion [] animDirDeMov;	

	private final Gson gson = new Gson();
	private int intervaloEnvio = 0;

	// Pila de movimiento
	private PilaDeTiles pilaMovimiento;
	private int[] tileActual;
	private int[] tileFinal;
	private int[] tileMoverme;

	private Mundo mundo;
	private String nombre;
	private int[] tilePersonajes;
	private int idEnemigo;

	// Ubicacion para abrir comerciar.
	private float xComercio;
	private float yComercio;
	private float[] comercio;
	

	/**
	 * Constructor de la clase Entidad
	 * 
	 * @param juego
	 *            juego con el que se instancia Entidad
	 * @param mundo
	 *            mundo con el que se instancia Entidad
	 * @param ancho
	 *            ancho
	 * @param alto
	 *            alto
	 * @param nombre
	 *            nombre del personaje
	 * @param spawnX
	 *            tile X donde spawnea
	 * @param spawnY
	 *            tile Y donde spawnea
	 * @param animaciones
	 *            animaciones del personaje
	 * @param velAnimacion
	 *            velocidad de animacion del personaje
	 */
	public Entidad(final Juego juego, final Mundo mundo, final int ancho, final int alto, final String nombre, final float spawnX, final float spawnY, final LinkedList<BufferedImage[]> animaciones, final int velAnimacion) {
		this.juego = juego;
		this.ancho = ancho;
		this.alto = alto;
		this.nombre = nombre;
		this.mundo = mundo;
		xOffset = ancho / 2;
		yOffset = alto / 2;
		x = (int) (spawnX / 64) * 64;
		y = (int) (spawnY / 32) * 32;
		
		this.animDirDeMov = new Animacion [8];
		
		for(int i=0 ; i < this.animDirDeMov.length ; i++) {
			this.animDirDeMov[i] = new Animacion(velAnimacion, animaciones.get(i));
		}

		// Informo mi posición actual
		juego.getUbicacionPersonaje().setPosX(x);
		juego.getUbicacionPersonaje().setPosY(y);
		juego.getUbicacionPersonaje().setDireccion(getDireccion());
		juego.getUbicacionPersonaje().setFrame(getFrame());
	}

	/**
	 * Actualiza el personaje
	 */
	public void actualizar() {

		if (enMovimiento) {			
			for(int i=0 ; i < this.animDirDeMov.length ; i++) {
				this.animDirDeMov[i].actualizar();
			}
		} else {
			for(int i=0 ; i < this.animDirDeMov.length ; i++) {
				this.animDirDeMov[i].reset();
			}
		}

		getEntrada();
		mover();

		juego.getCamara().Centrar(this);
	}

	/**
	 * Devuelve la entrada
	 */
	public void getEntrada() {
		posMouseRecorrido = juego.getHandlerMouse().getPosMouseRecorrido();
		posMouse = juego.getHandlerMouse().getPosMouse();
		if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= 738 && posMouse[0] <= 797 && posMouse[1] >= 545 && posMouse[1] <= 597) {
			if (Pantalla.menuInventario == null) {
				Pantalla.menuInventario = new MenuInventario(juego.getCli());
				Pantalla.menuInventario.setVisible(true);
			}
			juego.getHandlerMouse().setNuevoClick(false);
		}
		if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= 3 && posMouse[0] <= 105 && posMouse[1] >= 562 && posMouse[1] <= 597) {
			if (Pantalla.menuEscp == null) {
				Pantalla.menuEscp = new MenuEscape(juego.getCli());
				Pantalla.menuEscp.setVisible(true);
			}
			juego.getHandlerMouse().setNuevoClick(false);
		}
		if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= 3 && posMouse[0] <= 105 && posMouse[1] >= 524 && posMouse[1] <= 559) {
			if (Pantalla.ventContac == null) {
				Pantalla.ventContac = new VentanaContactos(juego);
				Pantalla.ventContac.setVisible(true);
			}
			juego.getHandlerMouse().setNuevoClick(false);
		}
		// Tomo el click izquierdo
		if (juego.getHandlerMouse().getNuevoClick()) {
			if (juego.getEstadoJuego().getHaySolicitud()) {

				// Pregunto si es el menú de subir de nivel
				if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.menuSubirNivel) {
					if (juego.getEstadoJuego().getMenuEnemigo().clickEnAsignarSkills(posMouse[0], posMouse[1])) {
						if (Pantalla.menuAsignar == null) {
							Pantalla.menuAsignar = new MenuAsignarSkills(juego.getCli());
						}
						Pantalla.menuAsignar.setVisible(true);
					}
				}

				if (juego.getEstadoJuego().getMenuEnemigo().clickEnMenu(posMouse[0], posMouse[1])) {
					if (juego.getEstadoJuego().getMenuEnemigo().clickEnBoton(posMouse[0], posMouse[1])) {
						// Pregunto si es el menuBatallar o menuComerciar, sino no hace falta hacer esto
						if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.menuBatallar || juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.menuComerciar) {
							// Guardo la posición del personaje con el que quiero comerciar
							xComercio = juego.getUbicacionPersonajes().get(idEnemigo).getPosX();
							yComercio = juego.getUbicacionPersonajes().get(idEnemigo).getPosY();
							comercio = Mundo.convertirISOa2D(xComercio, yComercio);
						}
						// Pregunto si el menú emergente es de batalla
						if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.menuBatallar) {
							// Me fijo si el que quiero batallar está en la zona de comercio
							if (!((int) comercio[0] >= 44 && (int) comercio[0] <= 71 && (int) comercio[1] >= 0 && (int) comercio[1] <= 29)) {
								juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.menuBatallar);
								PaqueteBatalla pBatalla = new PaqueteBatalla();

								pBatalla.setId(juego.getPersonaje().getId());
								pBatalla.setIdEnemigo(idEnemigo);

								juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.menuBatallar);

								try {
									juego.getCli().getSal().writeObject(gson.toJson(pBatalla));
								} catch (IOException e) {
									JOptionPane.showMessageDialog(null, "Falló la conexión " + "con el servidor" + "al intentar batallar");
								}
							} else {
								JOptionPane.showMessageDialog(null, "El otro usuario se encuentra " + "dentro de la zona de comercio");
							}
						} else {
							// Pregunto si el menú emergente es de tipo comercio
							if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.menuComerciar) {
								if ((int) comercio[0] >= 44 && (int) comercio[0] <= 71 && (int) comercio[1] >= 0 && (int) comercio[1] <= 29) {
									if (juego.getCli().getM1() == null) {
										juego.getCli().setPaqueteComercio(new PaqueteComerciar());
										juego.getCli().getPaqueteComercio().setId(juego.getPersonaje().getId());
										juego.getCli().getPaqueteComercio().setIdEnemigo(idEnemigo);

										try {
											juego.getCli().getSal().writeObject(gson.toJson(juego.getCli().getPaqueteComercio()));
										} catch (IOException e) {
											JOptionPane.showMessageDialog(null, "Falló la conexión " + "con el servidor" + "al intentar comerciar");
										}
									} else {
										JOptionPane.showMessageDialog(null, "¡Ya te encuentras comerciando!");
									}
								} else {
									JOptionPane.showMessageDialog(null, "El otro usuario no se encuentra " + "dentro de la zona de comercio");
								}
							}
						}
						juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.menuBatallar);

					} else if (juego.getEstadoJuego().getMenuEnemigo().clickEnCerrar(posMouse[0], posMouse[1])) {
						juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.menuBatallar);
					}
				} else {
					juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.menuBatallar);
				}
			} else {
				Iterator<Integer> it = juego.getUbicacionPersonajes().keySet().iterator();
				int key;
				int[] tileMoverme = Mundo.mouseATile(posMouse[0] + juego.getCamara().getxOffset() - xOffset, posMouse[1] + juego.getCamara().getyOffset() - yOffset);
				PaqueteMovimiento actual;

				while (it.hasNext()) {
					key = it.next();
					actual = juego.getUbicacionPersonajes().get(key);
					tilePersonajes = Mundo.mouseATile(actual.getPosX(), actual.getPosY());
					if (actual != null && actual.getIdPersonaje() != juego.getPersonaje().getId() && juego.getPersonajesConectados().get(actual.getIdPersonaje()) != null && juego.getPersonajesConectados().get(actual.getIdPersonaje()).getEstado() == Estado.getEstadoJuego()) {

						if (tileMoverme[0] == tilePersonajes[0] && tileMoverme[1] == tilePersonajes[1]) {
							idEnemigo = actual.getIdPersonaje();
							float XY[] = Mundo.convertirISOa2D(x, y);
							// Para no moverme hasta el lugar
							if (XY[0] >= 44 && XY[0] <= 71 && XY[1] >= 0 && XY[1] <= 29) {
								// Si estoy dentro de la zona de comercio, se abre el menú de comercio
								juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonajesConectados().get(idEnemigo), MenuInfoPersonaje.menuComerciar);
							} else {
								// Si estoy dentro de la zona de batalla, se abre la zona de batalla
								juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonajesConectados().get(idEnemigo), MenuInfoPersonaje.menuBatallar);
							}
							juego.getHandlerMouse().setNuevoClick(false);
						}
					}
				}
			}
		}

		if (juego.getHandlerMouse().getNuevoRecorrido() && !juego.getEstadoJuego().getHaySolicitud()) {

			tileMoverme = Mundo.mouseATile(posMouseRecorrido[0] + juego.getCamara().getxOffset() - xOffset, posMouseRecorrido[1] + juego.getCamara().getyOffset() - yOffset);

			juego.getHandlerMouse().setNuevoRecorrido(false);

			pilaMovimiento = null;

			juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.menuBatallar);
		}

		if (!enMovimiento && tileMoverme != null) {

			enMovimiento = false;

			xInicio = x;
			yInicio = y;

			tileActual = Mundo.mouseATile(x, y);

			if (tileMoverme[0] < 0 || tileMoverme[1] < 0 || tileMoverme[0] >= mundo.obtenerAncho() || tileMoverme[1] >= mundo.obtenerAlto()) {
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}

			if (tileMoverme[0] == tileActual[0] && tileMoverme[1] == tileActual[1] || mundo.getTile(tileMoverme[0], tileMoverme[1]).esSolido()) {
				tileMoverme = null;
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				return;
			}

			if (pilaMovimiento == null) {
				pilaMovimiento = caminoMasCorto(tileActual[0], tileActual[1], tileMoverme[0], tileMoverme[1]);
			}
			// Me muevo al primero de la pila
			NodoDePila nodoActualTile = pilaMovimiento.pop();

			if (nodoActualTile == null) {
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}

			tileFinal = new int[2];
			tileFinal[0] = nodoActualTile.obtenerX();
			tileFinal[1] = nodoActualTile.obtenerY();

			xFinal = Mundo.convertir2DaISO(tileFinal[0], tileFinal[1])[0];
			yFinal = Mundo.convertir2DaISO(tileFinal[0], tileFinal[1])[1];

			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = verticalSup;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = verticalInf;
			}
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = horizontalIzq;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = horizontalDer;
			}
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1]) {
				movimientoHacia = diagonalSupIzq;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1]) {
				movimientoHacia = diagonalInfDer;
			}
			if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = diagonalSupDer;
			}
			if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = diagonalInfIzq;
			}
			enMovimiento = true;
		}
	}

	/**
	 * Mueve el personaje
	 */
	public void mover() {

		dx = 0;
		dy = 0;

		double paso = 1;

		if (enMovimiento && !(x == xFinal && y == yFinal - 32)) {
			if (movimientoHacia == verticalSup) {
				dy -= paso;
			} else if (movimientoHacia == verticalInf) {
				dy += paso;
			} else if (movimientoHacia == horizontalDer) {
				dx += paso;
			} else if (movimientoHacia == horizontalIzq) {
				dx -= paso;
			} else if (movimientoHacia == diagonalInfDer) {
				dx += paso;
				dy += paso / 2;
			} else if (movimientoHacia == diagonalInfIzq) {
				dx -= paso;
				dy += paso / 2;
			} else if (movimientoHacia == diagonalSupDer) {
				dx += paso;
				dy -= paso / 2;
			} else if (movimientoHacia == diagonalSupIzq) {
				dx -= paso;
				dy -= paso / 2;
			}

			x += dx;
			y += dy;

			// Le envío la posición
			if (intervaloEnvio == 2) {
				enviarPosicion();
				intervaloEnvio = 0;
			}
			intervaloEnvio++;

			if (x == xFinal && y == yFinal - 32) {
				enMovimiento = false;
			}
		}
	}

	/**
	 * Grafica el frame del personaje
	 */
	public void graficar(final Graphics g) {
		drawX = (int) (x - juego.getCamara().getxOffset());
		drawY = (int) (y - juego.getCamara().getyOffset());
		g.drawImage(getFrameAnimacionActual(), drawX, drawY + 4, ancho, alto, null);
	}

	/**
	 * Grafica el nombre
	 */
	public void graficarNombre(final Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		Pantalla.centerString(g, new java.awt.Rectangle(drawX + 32, drawY - 20, 0, 10), nombre);
	}

	/**
	 * Obtiene el frameActual del personaje
	 */
	private BufferedImage getFrameAnimacionActual() {
		
		if(this.movimientoHacia >= 0 && this.movimientoHacia <=7 ) {
			return this.animDirDeMov[this.movimientoHacia].getFrameActual();
		}
		return Recursos.orco.get(6)[0];
	}

	/**
	 * Pide la direccion donde va
	 * 
	 * @return devuelve el movimiento hacia donde va
	 */
	private int getDireccion() {
		return movimientoHacia;
	}

	/**
	 * Obtiene el frame donde esta el personaje
	 */
	private int getFrame() {		
		if(this.movimientoHacia >= 0 && this.movimientoHacia <=7 ) {
			return this.animDirDeMov[this.movimientoHacia].getFrame();
		}
		return 0;
	}

	/**
	 * Envia la posicion del personaje
	 */
	private void enviarPosicion() {
		juego.getUbicacionPersonaje().setPosX(x);
		juego.getUbicacionPersonaje().setPosY(y);
		juego.getUbicacionPersonaje().setDireccion(getDireccion());
		juego.getUbicacionPersonaje().setFrame(getFrame());
		try {
			juego.getCli().getSal().writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló la conexión con el servidor en Entidad.enviarPosicion()");
		}
	}

	/**
	 * Busca el camino más corto a recorrer para llegar a una posición
	 * 
	 * @param xInicial
	 *            ubicacion en X inicial
	 * @param yInicial
	 *            ubicacion en Y inicial
	 * @param xFinal
	 *            ubicacion en X final
	 * @param yFinal
	 *            ubicacion en Y final
	 * @return la pila de tiles a recorrer
	 */
	private PilaDeTiles caminoMasCorto(final int xInicial, final int yInicial, final int xFinal, final int yFinal) {
		Grafo grafoLibres = mundo.obtenerGrafoDeTilesNoSolidos();
		// Transformo las coordenadas iniciales y finales en índices
		int nodoInicial = (yInicial - grafoLibres.obtenerNodos()[0].obtenerY()) * (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xInicial - grafoLibres.obtenerNodos()[0].obtenerX();

		int nodoFinal = (yFinal - grafoLibres.obtenerNodos()[0].obtenerY()) * (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xFinal - grafoLibres.obtenerNodos()[0].obtenerX();

		// Hago todo
		double[] vecCostos = new double[grafoLibres.obtenerCantidadDeNodosTotal()];
		int[] vecPredecesores = new int[grafoLibres.obtenerCantidadDeNodosTotal()];
		boolean[] conjSolucion = new boolean[grafoLibres.obtenerCantidadDeNodosTotal()];
		int cantSolucion = 0;
		// Lleno la matriz de costos de números grandes
		for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
			vecCostos[i] = Double.MAX_VALUE;
		}
		// Adyacentes al nodo inicial
		conjSolucion[nodoInicial] = true;
		cantSolucion++;
		vecCostos[nodoInicial] = 0;
		Nodo[] adyacentes = grafoLibres.obtenerNodos()[nodoInicial].obtenerNodosAdyacentes();
		for (int i = 0; i < grafoLibres.obtenerNodos()[nodoInicial].obtenerCantidadDeAdyacentes(); i++) {
			if (estanEnDiagonal(grafoLibres.obtenerNodos()[nodoInicial], grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
				vecCostos[adyacentes[i].obtenerIndice()] = 1.5;
			} else {
				vecCostos[adyacentes[i].obtenerIndice()] = 1;
			}
			vecPredecesores[adyacentes[i].obtenerIndice()] = nodoInicial;
		}
		// Aplico Dijkstra
		while (cantSolucion < grafoLibres.obtenerCantidadDeNodosTotal()) {
			// Elijo W perteneciente al conjunto restante tal que el costo de W sea mínimo
			double minimo = Double.MAX_VALUE;
			int indiceMinimo = 0;
			Nodo nodoW = null;
			for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
				if (!conjSolucion[i] && vecCostos[i] < minimo) {
					nodoW = grafoLibres.obtenerNodos()[i];
					minimo = vecCostos[i];
					indiceMinimo = i;
				}
			}
			// Pongo a W en el conjunto solución
			conjSolucion[indiceMinimo] = true;
			cantSolucion++;
			// Por cada nodo I adyacente a W del conjunto restante, le sumo 1 al costo de ir hasta W y luego ir hasta su adyacente
			adyacentes = grafoLibres.obtenerNodos()[indiceMinimo].obtenerNodosAdyacentes();
			for (int i = 0; i < grafoLibres.obtenerNodos()[indiceMinimo].obtenerCantidadDeAdyacentes(); i++) {
				double valorASumar = 1;
				if (estanEnDiagonal(grafoLibres.obtenerNodos()[indiceMinimo], grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
					valorASumar = 1.5;
				}
				if (vecCostos[indiceMinimo] + valorASumar < vecCostos[adyacentes[i].obtenerIndice()]) {
					vecCostos[adyacentes[i].obtenerIndice()] = vecCostos[indiceMinimo] + valorASumar;
					vecPredecesores[adyacentes[i].obtenerIndice()] = indiceMinimo;
				}
			}
		}
		// Creo el vector de nodos hasta donde quiere llegar
		PilaDeTiles camino = new PilaDeTiles();
		while (nodoFinal != nodoInicial) {
			camino.push(new NodoDePila(grafoLibres.obtenerNodos()[nodoFinal].obtenerX(), grafoLibres.obtenerNodos()[nodoFinal].obtenerY()));
			nodoFinal = vecPredecesores[nodoFinal];
		}

		return camino;
	}

	/**
	 * Pregunta si los personajes estan en diagonal
	 * 
	 * @param nodoUno
	 *            personaje 1
	 * @param nodoDos
	 *            personaje 2
	 * @return true or false
	 */
	private boolean estanEnDiagonal(final Nodo nodoUno, final Nodo nodoDos) {
		return nodoUno.obtenerX() == nodoDos.obtenerX() || nodoUno.obtenerY() == nodoDos.obtenerY();
	}

	/**
	 * Pide el valor de X
	 * 
	 * @return devuelve la ubicacion en X
	 */
	public float getX() {
		return x;
	}

	/**
	 * Setea el valor de X
	 * 
	 * @param x
	 *            valor nuevo de la ubicacion en X
	 */
	public void setX(final float x) {
		this.x = x;
	}

	/**
	 * Pide el valor de Y
	 * 
	 * @return devuelve la ubicacion en Y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Setea el valor de Y
	 * 
	 * @param y
	 *            valor nuevo de la ubicacion en Y
	 */
	public void setY(final float y) {
		this.y = y;
	}

	/**
	 * Pide el ancho
	 * 
	 * @return devuelve el ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Setea el ancho
	 * 
	 * @param ancho
	 *            nuevo ancho a setear
	 */
	public void setAncho(final int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Pide el alto
	 * 
	 * @return devuelve el alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Setea el alto
	 * 
	 * @param alto
	 *            nuevo alto a setear
	 */
	public void setAlto(final int alto) {
		this.alto = alto;
	}

	/**
	 * Pide el offset de X
	 * 
	 * @return devuelve el offset de X
	 */
	public int getxOffset() {
		return xOffset;
	}

	/**
	 * Pide el offset de Y
	 * 
	 * @return devuelve el offset de Y
	 */
	public int getYOffset() {
		return yOffset;
	}
}
