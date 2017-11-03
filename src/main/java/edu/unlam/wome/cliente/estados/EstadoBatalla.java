package edu.unlam.wome.cliente.estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Orco;
import dominio.Personaje;
import edu.unlam.wome.cliente.interfaz.EstadoDePersonaje;
import edu.unlam.wome.cliente.interfaz.MenuBatalla;
import edu.unlam.wome.cliente.interfaz.MenuInfoPersonaje;
import edu.unlam.wome.cliente.juego.Juego;
import edu.unlam.wome.cliente.mensajeria.Comando;
import edu.unlam.wome.cliente.mensajeria.PaqueteAtacar;
import edu.unlam.wome.cliente.mensajeria.PaqueteBatalla;
import edu.unlam.wome.cliente.mensajeria.PaqueteFinalizarBatalla;
import edu.unlam.wome.cliente.mensajeria.PaquetePersonaje;
import edu.unlam.wome.cliente.mundo.Mundo;
import edu.unlam.wome.cliente.recursos.Recursos;

/**
 * Clase EstadoBatalla: Se encarga de gestionar la batalla
 * @author Miguel
 */
public class EstadoBatalla extends Estado {

	private Mundo mundo;
	private Personaje personaje;
	private Personaje enemigo;
	private int[] posMouse;
	private PaquetePersonaje paquetePersonaje;
	private PaquetePersonaje paqueteEnemigo;
	private PaqueteAtacar paqueteAtacar;
	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	private boolean miTurno;

	private boolean haySpellSeleccionada;
	private boolean seRealizoAccion;

	private Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;
	private BufferedImage miniaturaEnemigo;

	private MenuBatalla menuBatalla;
	private final int posXMiniaturaPersonaje = 25;
	private final int posYMiniaturaPersonaje = 5;
	private final int posXMiniaturaEnemigo = 550;
	private final int posYMiniaturaEnemigo = 5;
	private final int posXPersonaje = 0;
	private final int posYPersonaje = 175;
	private final int anchoPersonaje = 256;
	private final int altoPersonaje = 256;
	private final int posXEnemigo = 550;
	private final int posYEnemigo = 75;
	private final int anchoEnemigo = 256;
	private final int altoEnemigo = 256;
	private final int perfilPersonaje = 3;
	private final int perfilEnemigo = 7;
	private final int multiplicadorPuntos = 3;
	private final int multiplicadorExp = 40;
	private final int energiaRecibida = 10;
	private final int posicionPoderEnergizar = 6;
	private final int posicionTercerPoder = 3;
	private final int posicionCuartoPoder = 4;
	private final int posicionQuintoPoder = 5;
	private final int offsetXCamara = -350;
	private final int offsetYCamara = 150;
	private final int posMiniaturaPersonaje = 5;
	private final int posMiniaturaEnemigo = 5;

	/**
	 * Constructor de la batalla
	 * @param juego Juego Actual
	 * @param paqueteBatalla paquete batalla
	 */
	public EstadoBatalla(final Juego juego, final PaqueteBatalla paqueteBatalla) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteBatalla.isMiTurno();

		paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
		paqueteEnemigo = juego.getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());

		crearPersonajes();

		menuBatalla = new MenuBatalla(miTurno, personaje);

		miniaturaEnemigo = Recursos.getPersonaje().get(enemigo.getNombreRaza()).get(posMiniaturaEnemigo)[0];
		miniaturaPersonaje = Recursos.getPersonaje().get(
				personaje.getNombreRaza()
				).
				get(posMiniaturaPersonaje)[0];

		paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
		paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
		paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());

		// Batalla perdida por defecto
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENU_PERDER_BATALLA);

		// Limpia la acción del mouse
		juego.getHandlerMouse().setNuevoClick(false);

	}

	@Override
	public void actualizar() {

		this.getJuego().getCamara().setxOffset(offsetXCamara);
		this.getJuego().getCamara().setyOffset(offsetYCamara);

		seRealizoAccion = false;
		haySpellSeleccionada = false;

		if (miTurno) {

			if (this.getJuego().getHandlerMouse().getNuevoClick()) {
				posMouse = this.getJuego().getHandlerMouse().getPosMouse();

				if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadRaza1(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadRaza2(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1])
							== posicionTercerPoder) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta1(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1])
							== posicionCuartoPoder) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta2(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1])
							== posicionQuintoPoder) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta3(enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1])
							== posicionPoderEnergizar) {
						seRealizoAccion = true;
						personaje.serEnergizado(energiaRecibida);
						haySpellSeleccionada = true;
					}
				}

				if (haySpellSeleccionada && seRealizoAccion) {
					if (!enemigo.estaVivo()) {
						this.getJuego().getEstadoJuego().setHaySolicitud(
								true, this.getJuego().getPersonaje(),
								MenuInfoPersonaje.MENU_GANAR_BATALLA);
						if (personaje.ganarExperiencia(enemigo.getNivel() * multiplicadorExp)) {
							int nivelInicial = this.getJuego().getPersonaje().getNivel();
							this.getJuego().getPersonaje().setNivel(personaje.getNivel());
							int nivelFinal = this.getJuego().getPersonaje().getNivel();
							int nivelesSubidos = nivelFinal - nivelInicial;
							int puntosNoAsignados = paquetePersonaje.getPuntosNoAsignados();
							paquetePersonaje.setPuntosNoAsignados(
									puntosNoAsignados
									+ nivelesSubidos * multiplicadorPuntos);
							this.getJuego().getEstadoJuego().setHaySolicitud(
									true, this.getJuego().getPersonaje(),
									MenuInfoPersonaje.MENU_SUBIR_NIVEL);
						}
						paqueteFinalizarBatalla.setGanadorBatalla(
								this.getJuego().getPersonaje().
								getId());
						finalizarBatalla();
						Estado.setEstado(this.getJuego().getEstadoJuego());

					} else {
						paqueteAtacar = new PaqueteAtacar(
								paquetePersonaje.getId(),
								paqueteEnemigo.getId(),
								personaje.getSalud(),
								personaje.getEnergia(),
								enemigo.getSalud(),
								enemigo.getEnergia(),
								personaje.getDefensa(),
								enemigo.getDefensa(),
								personaje.getCasta().getProbabilidadEvitarDanio(),
								enemigo.getCasta().getProbabilidadEvitarDanio());
						enviarAtaque(paqueteAtacar);
						miTurno = false;
						menuBatalla.setHabilitado(false);
					}
				} else if (haySpellSeleccionada && !seRealizoAccion) {
					JOptionPane.showMessageDialog(
							null, "No posees la energía "
									+ "suficiente para realizar esta habilidad.");
				}

				this.getJuego().getHandlerMouse().setNuevoClick(false);
			}
		}

	}

	@Override
	public void graficar(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getJuego().getAncho(), this.getJuego().getAlto());
		mundo.graficarSuelo(g);

		g.drawImage(Recursos.getPersonaje().get(paquetePersonaje.getRaza()).get(perfilPersonaje)[0],
				posXPersonaje,
				posYPersonaje,
				anchoPersonaje,
				altoPersonaje,
				null);
		g.drawImage(Recursos.getPersonaje().get(paqueteEnemigo.getRaza()).get(perfilEnemigo)[0],
				posXEnemigo,
				posYEnemigo,
				anchoEnemigo,
				altoEnemigo,
				null);

		mundo.graficarObstaculos(g);
		menuBatalla.graficar(g);

		g.setColor(Color.GREEN);

		EstadoDePersonaje.dibujarEstadoDePersonaje(g,
				posXMiniaturaPersonaje,
				posYMiniaturaPersonaje,
				personaje,
				miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g,
				posXMiniaturaEnemigo,
				posYMiniaturaEnemigo,
				enemigo,
				miniaturaEnemigo);

	}

	/**
	 * Crea un personaje
	 */
	private void crearPersonajes() {
		String nombre = paquetePersonaje.getNombre();
		int salud = paquetePersonaje.getSaludTope();
		int energia = paquetePersonaje.getEnergiaTope();
		int fuerza = paquetePersonaje.getFuerza();
		int destreza = paquetePersonaje.getDestreza();
		int inteligencia = paquetePersonaje.getInteligencia();
		int experiencia = paquetePersonaje.getExperiencia();
		int nivel = paquetePersonaje.getNivel();
		int id = paquetePersonaje.getId();

		Casta casta = null;
		try {
			casta = (Casta) Class.forName("dominio" + "." + paquetePersonaje.getCasta()).newInstance();
			personaje = (Personaje) Class.forName(
					"dominio" + "." + paquetePersonaje.getRaza()).
					getConstructor(String.class,
							Integer.TYPE, Integer.TYPE,
							Integer.TYPE, Integer.TYPE,
							Integer.TYPE, Casta.class,
							Integer.TYPE, Integer.TYPE,
							Integer.TYPE).newInstance(
									nombre, salud, energia,
									fuerza, destreza,
									inteligencia, casta,
									experiencia, nivel,
									id);
		} catch (InstantiationException
				| IllegalAccessException
				| ClassNotFoundException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}

		nombre = paqueteEnemigo.getNombre();
		salud = paqueteEnemigo.getSaludTope();
		energia = paqueteEnemigo.getEnergiaTope();
		fuerza = paqueteEnemigo.getFuerza();
		destreza = paqueteEnemigo.getDestreza();
		inteligencia = paqueteEnemigo.getInteligencia();
		experiencia = paqueteEnemigo.getExperiencia();
		nivel = paqueteEnemigo.getNivel();
		id = paqueteEnemigo.getId();

		casta = null;
		if (paqueteEnemigo.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (paqueteEnemigo.getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (paqueteEnemigo.getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (paqueteEnemigo.getRaza().equals("Humano")) {
			enemigo = new Humano(
					nombre, salud,
					energia, fuerza, destreza,
					inteligencia, casta,
					experiencia, nivel, id);
		} else if (paqueteEnemigo.getRaza().equals("Orco")) {
			enemigo = new Orco(
					nombre, salud,
					energia, fuerza, destreza,
					inteligencia, casta,
					experiencia, nivel, id);
		} else if (paqueteEnemigo.getRaza().equals("Elfo")) {
			enemigo = new Elfo(
					nombre, salud,
					energia, fuerza, destreza,
					inteligencia, casta,
					experiencia, nivel, id);
		}
	}

	/**
	 * Envia el ataque del personaje
	 * @param paqueteAtacarP paquete atacar parametro
	 */
	public void enviarAtaque(final PaqueteAtacar paqueteAtacarP) {
		try {
			this.getJuego().getCli().getSal().writeObject(gson.toJson(paqueteAtacarP));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
		}
	}

	/**
	 * Finaliza la batalla.
	 */
	private void finalizarBatalla() {
		try {
			this.getJuego().getCli().getSal().writeObject(gson.toJson(paqueteFinalizarBatalla));

			paquetePersonaje.setSaludTope(personaje.getSaludTope());
			paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
			paquetePersonaje.setNivel(personaje.getNivel());
			paquetePersonaje.setExperiencia(personaje.getExperiencia());
			paquetePersonaje.setDestreza(personaje.getDestreza());
			paquetePersonaje.setFuerza(personaje.getFuerza());
			paquetePersonaje.setInteligencia(personaje.getInteligencia());
			paquetePersonaje.removerBonus();

			paqueteEnemigo.setSaludTope(enemigo.getSaludTope());
			paqueteEnemigo.setEnergiaTope(enemigo.getEnergiaTope());
			paqueteEnemigo.setNivel(enemigo.getNivel());
			paqueteEnemigo.setExperiencia(enemigo.getExperiencia());
			paqueteEnemigo.setDestreza(enemigo.getDestreza());
			paqueteEnemigo.setFuerza(enemigo.getFuerza());
			paqueteEnemigo.setInteligencia(enemigo.getInteligencia());
			paqueteEnemigo.removerBonus();

			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);

			this.getJuego().getCli().getSal().writeObject(gson.toJson(paquetePersonaje));
			this.getJuego().getCli().getSal().writeObject(gson.toJson(paqueteEnemigo));

		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					null, "Falló la conexión con "
							+ "el servidor al finalizar batalla.");
		}
	}

	/**
	 * Devuelve el paquete del personaje
	 * @return Paquete Personaje
	 */
	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	/**
	 * Devuelve el paquete del enemigo
	 * @return Paquete Enemigo
	 */
	public PaquetePersonaje getPaqueteEnemigo() {
		return paqueteEnemigo;
	}

	/**
	 * Setea el turno del jugador
	 * @param b boolean
	 */
	public void setMiTurno(final boolean b) {
		miTurno = b;
		menuBatalla.setHabilitado(b);
		this.getJuego().getHandlerMouse().setNuevoClick(false);
	}

	/**
	 * Devuelve el personaje
	 * @return Personaje
	 */
	public Personaje getPersonaje() {
		return personaje;
	}

	/**
	 * Devuelve el enemigo
	 * @return Personaje enemigo
	 */
	public Personaje getEnemigo() {
		return enemigo;
	}

	@Override
	public boolean esEstadoDeJuego() {
		return false;
	}

}
