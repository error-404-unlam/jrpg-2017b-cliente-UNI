package estados;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import entidades.Entidad;
import interfaz.EstadoDePersonaje;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * Clase encargada de manejar el estado del juego.
 * @author Miguel
 */
public class EstadoJuego extends Estado {

	private Entidad entidadPersonaje;
	private PaquetePersonaje paquetePersonaje;
	private Mundo mundo;
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	private Map<Integer, PaquetePersonaje> personajesConectados;
	private boolean haySolicitud;
	private int tipoSolicitud;
	private final Gson gson = new Gson();
	private final int posXMenuInfoPersonaje = 300;
	private final int posYMenuInfoPersonaje = 50;
	private final int limiteSupMapaNum = 3;
	private final int posXMochila = 738;
	private final int posYMochila = 545;
	private final int anchoMochila = 59;
	private final int altoMochila = 52;
	private final int posXMenu = 3;
	private final int posYMenu = 562;
	private final int anchoMenu = 102;
	private final int altoMenu = 35;
	private final int posXChat = 3;
	private final int posYChat = 524;
	private final int anchoChat = 102;
	private final int altoChat = 35;
	private final int xDibujarEstadoDePersonaje = 5;
	private final int yDibujarEstadoDePersonaje = 5;
	private final int nroMiniaturaPersonaje = 5;
	private BufferedImage miniaturaPersonaje;
	private final int velocidadPersonaje = 150;
	private MenuInfoPersonaje menuEnemigo;
	private final int anchoPersonaje = 64;
	private final int altoPersonaje = 64;
	/**
	 * Constructor
	 * @param juego que se recibe como parametro
	 */
	public EstadoJuego(final Juego juego) {
		super(juego);
		mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
		paquetePersonaje = juego.getPersonaje();
		entidadPersonaje = new Entidad(
				juego,
				mundo,
				anchoPersonaje, altoPersonaje,
				juego.getPersonaje().getNombre(),
				0, 0,
				Recursos.getPersonaje().get(
						juego.getPersonaje().getRaza()),
				velocidadPersonaje);
		miniaturaPersonaje = Recursos.getPersonaje().get(
				paquetePersonaje.getRaza()).
				get(nroMiniaturaPersonaje)[0];

		try {
			// Le pido al servidor que me conecte al mapa
			juego.getPersonaje().setComando(Comando.CONEXION);
			juego.getPersonaje().setEstado(Estado.getEstadoJuego());
			juego.getCli().getSal().writeObject(gson.toJson(juego.getPersonaje(), PaquetePersonaje.class));
			juego.getCli().getSal().writeObject(
					gson.toJson(
							juego.getUbicacionPersonaje(),
							PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					null, "Falló la conexión con "
							+ "el servidor al ingresar al mundo.");
		}
	}

	@Override
	public void actualizar() {
		mundo.actualizar();
		entidadPersonaje.actualizar();
	}

	@Override
	public void graficar(final Graphics g) {
		g.drawImage(
				Recursos.getBackground(),
				0, 0,
				this.getJuego().getAncho(),
				this.getJuego().getAlto(),
				null);
		mundo.graficarSuelo(g);
		mundo.graficarObstaculos(g);
		g.drawImage(Recursos.getMarco(), 0, 0, this.getJuego().getAncho(), this.getJuego().getAlto(), null);
		EstadoDePersonaje.dibujarEstadoDePersonaje(
				g,
				xDibujarEstadoDePersonaje,
				yDibujarEstadoDePersonaje,
				paquetePersonaje,
				miniaturaPersonaje);
		g.drawImage(Recursos.getMochila(), posXMochila, posYMochila, anchoMochila, altoMochila, null);
		g.drawImage(Recursos.getMenu(), posXMenu, posYMenu, anchoMenu, altoMenu, null);
		g.drawImage(Recursos.getChat(), posXChat, posYChat, anchoChat, altoChat, null);
		if (haySolicitud) {
			menuEnemigo.graficar(g, tipoSolicitud);
		}
	}

	/**
	 * Devuelve personaje
	 * @return Entidad
	 */
	public Entidad getPersonaje() {
		return entidadPersonaje;
	}

	/**
	 * Devuelve el mundo
	 * @return mundo
	 */
	private String getMundo() {
		String[] mundoM = {"Aubenor" , "Aris" , "Eodrim" };
		int mapNum = this.getJuego().getPersonaje().getMapa();
		if (mapNum >= 1 && mapNum <= limiteSupMapaNum) {
			return mundoM[mapNum - 1];
		}
		return null;
	}

	/**
	 * Setea una solicitud
	 * @param b valor de verdad de la solicitud
	 * @param enemigo enemigo
	 * @param tipoSolicitudP tipo de solicitud
	 */
	public void setHaySolicitud(final boolean b, final PaquetePersonaje enemigo, final int tipoSolicitudP) {
		haySolicitud = b;
		// Menu que mostrará al enemigo
		menuEnemigo = new MenuInfoPersonaje(
				posXMenuInfoPersonaje,
				posYMenuInfoPersonaje,
				enemigo);
		this.tipoSolicitud = tipoSolicitudP;
	}

	/**
	 * Devuelve si hay solicitud
	 * @return boolean
	 */
	public boolean getHaySolicitud() {
		return haySolicitud;
	}

	/**
	 * Actualiza personaje.
	 */
	public void actualizarPersonaje() {
		paquetePersonaje = this.getJuego().getPersonaje();
	}

	/**
	 * Devuelve el menu del enemigo
	 * @return menuEnemigo
	 */
	public MenuInfoPersonaje getMenuEnemigo() {
		return menuEnemigo;
	}

	/**
	 * Devuelve el tipo de solicitud
	 * @return tipoSolicitud
	 */
	public int getTipoSolicitud() {
		return tipoSolicitud;
	}

	@Override
	public boolean esEstadoDeJuego() {
		return true;
	}

}