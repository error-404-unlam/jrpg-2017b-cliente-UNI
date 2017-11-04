package edu.unlam.wome.juego;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import edu.unlam.wome.dominio.main.Personaje;
import edu.unlam.wome.estados.Estado;
import edu.unlam.wome.estados.EstadoBatalla;
import edu.unlam.wome.estados.EstadoJuego;
import edu.unlam.wome.mensajeria.PaqueteMovimiento;
import edu.unlam.wome.mensajeria.PaquetePersonaje;
import edu.unlam.wome.chat.MiChat;
import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.cliente.EscuchaMensajes;

/**
 * Clase encargada de gestionar el juego.
 * @author Marvix
 */
public class Juego implements Runnable {

	private Pantalla pantalla;
	private final String nombre;
	private final int ancho;
	private final int alto;

	private Thread hilo;
	private boolean corriendo;

	private BufferStrategy bs; // Estrategia para graficar mediante buffers
								// (Primero se "grafica" en el/los buffer/s y
								// finalmente en el canvas)
	private Graphics g;

	// Estados
	private Estado estadoJuego;
	private Estado estadoBatalla;

	// HandlerMouse
	private HandlerMouse handlerMouse;

	// Camara
	private Camara camara;

	// Conexion
	private Cliente cliente;
	private EscuchaMensajes escuchaMensajes;
	private PaquetePersonaje paquetePersonaje;
	private PaqueteMovimiento ubicacionPersonaje;
	private Map<Integer, PaquetePersonaje> personajesConectados;
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	private Map<String, MiChat> chatsActivos = new HashMap<>();

	private CargarRecursos cargarRecursos;

	private final int fps = 60; // Cantidad de actualizaciones por segundo que se desean
	private final int nanosegundos = 1000000000;

	//Cantidad de nanosegundos en FPS deseados
	private final double tiempoPorActualizacion = nanosegundos / fps;

	private final int fontsize = 15; //tama�o de fuente

	private final int direccionInicial = 6; //direccion en la que comienza el personaje

	private final int numBuffers = 3; //numero de buffers a crear
	/**
	 * Constructor de la clase Juego
	 * @param nombre	nombre del juego
	 * @param ancho		ancho de la ventana
	 * @param alto		alto de la ventana
	 * @param cliente	cliente
	 * @param pp		paquete del personaje
	 */
	public Juego(final String nombre, final int ancho, final int alto,
			final Cliente cliente, final PaquetePersonaje pp) {
		this.nombre = nombre;
		this.alto = alto;
		this.ancho = ancho;
		this.cliente = cliente;
		this.paquetePersonaje = pp;

		// Inicializo la ubicacion del personaje
		ubicacionPersonaje = new PaqueteMovimiento();
		ubicacionPersonaje.setIdPersonaje(paquetePersonaje.getId());
		ubicacionPersonaje.setFrame(0);
		ubicacionPersonaje.setDireccion(direccionInicial);

		// Creo el escucha de mensajes
		escuchaMensajes = new EscuchaMensajes(this);
		escuchaMensajes.start();

		handlerMouse = new HandlerMouse();

		iniciar();

		cargarRecursos = new CargarRecursos(cliente);
		cargarRecursos.start();
	}

	/**
	 * Carga lo necesario para iniciar el juego
	 */
	public void iniciar() {
		pantalla = new Pantalla(nombre, ancho, alto, cliente);

		pantalla.getCanvas().addMouseListener(handlerMouse);

		camara = new Camara(this, 0, 0);

		Personaje.cargarTablaNivel();
	}

	/**
	 * Actualiza los objetos y sus posiciones
	 */
	private void actualizar() {

		if (Estado.getEstado() != null) {
			Estado.getEstado().actualizar();
		}
	}

	/**
	 * Grafica los objetos y sus posiciones
	 */
	private void graficar() {
		bs = pantalla.getCanvas().getBufferStrategy();
		if (bs == null) { // Seteo una estrategia para el canvas en caso de que
							// no tenga una
			pantalla.getCanvas().createBufferStrategy(numBuffers);
			return;
		}

		g = bs.getDrawGraphics(); // Permite graficar el buffer mediante g

		g.clearRect(0, 0, ancho, alto); // Limpiamos la pantalla

		// Graficado de imagenes
		g.setFont(new Font("Book Antiqua", 1, fontsize));

		if (Estado.getEstado() != null) {
			Estado.getEstado().graficar(g);
		}

		// Fin de graficado de imagenes

		bs.show(); // Hace visible el proximo buffer disponible
		g.dispose();
	}

	@Override
	public void run() { // Hilo principal del juego

		double delta = 0;
		long ahora;
		long ultimoTiempo = System.nanoTime();
		long timer = 0; // Timer para mostrar fps cada un segundo
		int actualizaciones = 0; // Cantidad de actualizaciones que se realizan
									// realmente

		while (corriendo) {
			ahora = System.nanoTime();

			// Calculo para determinar cuando realizar la actualizacion y el graficado
			delta += (ahora - ultimoTiempo) / tiempoPorActualizacion;

			// Sumo el tiempo transcurrido hasta que se acumule 1 segundo y mostrar los FPS
			timer += ahora - ultimoTiempo;

			ultimoTiempo = ahora; // Para las proximas corridas del bucle

			if (delta >= 1) {
				actualizar();
				graficar();
				actualizaciones++;
				delta--;
			}

			if (timer >= nanosegundos) { // Si paso 1 segundo muestro los FPS
				pantalla.getFrame().setTitle(nombre + " | " + "FPS: " + actualizaciones);
				actualizaciones = 0;
				timer = 0;
			}
		}

		stop();
	}

	/**
	 * Inicia el juego
	 */
	public synchronized void start() {
		if (corriendo) {
			return;
		}
		estadoJuego = new EstadoJuego(this);
		Estado.setEstado(estadoJuego);
		pantalla.mostrar();
		corriendo = true;
		hilo = new Thread(this);
		hilo.start();
	}

	/**
	 * Detiene el juego
	 */
	public synchronized void stop() {
		if (!corriendo) {
			return;
		}
		try {
			corriendo = false;
			hilo.join();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Fallo al intentar detener el juego.");
		}
	}

	/**
	 * @return el ancho de la ventana
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @return el alto de la ventana
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @return el handlerMouse
	 */
	public HandlerMouse getHandlerMouse() {
		return handlerMouse;
	}

	/**
	 * @return la camara
	 */
	public Camara getCamara() {
		return camara;
	}

	/**
	 * @return estado del juego
	 */
	public EstadoJuego getEstadoJuego() {
		return (EstadoJuego) estadoJuego;
	}

	/**
	 * @return estado de la batalla
	 */
	public EstadoBatalla getEstadoBatalla() {
		return (EstadoBatalla) estadoBatalla;
	}

	/**
	 * se establece el estado de la batalla
	 * @param estadoBatalla estado de la batalla
	 */
	public void setEstadoBatalla(final EstadoBatalla estadoBatalla) {
		this.estadoBatalla = estadoBatalla;
	}

	/**
	 * @return el cliente
	 */
	public Cliente getCli() {
		return cliente;
	}

	/**
	 * @return el escuchador de mensajes
	 */
	public EscuchaMensajes getEscuchaMensajes() {
		return escuchaMensajes;
	}

	/**
	 * @return el paquete del personaje
	 */
	public PaquetePersonaje getPersonaje() {
		return paquetePersonaje;
	}

	/**
	 * @return la ubicacion del personaje
	 */
	public PaqueteMovimiento getUbicacionPersonaje() {
		return ubicacionPersonaje;
	}

	/**
	 * Importa el paquete del personaje a la clase Juego
	 * @param paqueteDePersonaje es el paquete con todos los stats del personaje
	 */
	public void setPersonaje(final PaquetePersonaje paqueteDePersonaje) {
		this.paquetePersonaje = paqueteDePersonaje;
	}

	/**
	 * Actualiza los stats del personaje
	 */
	public void actualizarPersonaje() {
		paquetePersonaje = (PaquetePersonaje) (personajesConectados.get(paquetePersonaje.getId()).clone());
	}

	/**
	 * @return los personajes conectados
	 */
	public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
		return personajesConectados;
	}

	/**
	 * se setean los paquetes de los personajes conectados
	 * @param map contiene los personajes identificados a traves de un id
	 */
	public void setPersonajesConectados(final Map<Integer, PaquetePersonaje> map) {
		this.personajesConectados = map;
	}

	/**
	 * @return la ubicacion de todos los personajes conectados
	 */
	public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
		return ubicacionPersonajes;
	}

	/**
	 * se setean las ubicaciones de los personajes conectados
	 * @param ubicacionPersonajes contiene la ubicacion de los personajes conectados
	 */
	public void setUbicacionPersonajes(final Map<Integer, PaqueteMovimiento> ubicacionPersonajes) {
		this.ubicacionPersonajes = ubicacionPersonajes;
	}

	/**
	 * @return los personajes que estan en el chat
	 */
	public Map<String, MiChat> getChatsActivos() {
		return chatsActivos;
	}
}
