package mensajeria;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Paquete a ser enviado cuando un jugador ataca
 * @author Miguel
 */
public class PaqueteAtacar extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int nuevaSaludPersonaje;
	private int nuevaEnergiaPersonaje;
	private int nuevaSaludEnemigo;
	private int nuevaEnergiaEnemigo;
	private HashMap<String, Number> mapPersonaje = new HashMap<String, Number>();
	private HashMap<String, Number> mapEnemigo = new HashMap<String, Number>();

	/**
	 * Constructor
	 * @param id id personaje personaje
	 * @param idEnemigo id del enemigo
	 * @param nuevaSalud nueva salud personaje
	 * @param nuevaEnergia nueva energia personaje
	 * @param nuevaSaludEnemigo nueva salud del enemigo
	 * @param nuevaEnergiaEnemigo nueva energia del enemigo
	 * @param nuevaDefensa nueva defensa personaje
	 * @param nuevaDefensaEnemigo nueva defensa enemigo
	 * @param probEvitarDano probabilidad de evitar danio
	 * @param probEvitarDanoEnemgio probabilidad de evitar daño del enemigo
	 */
	public PaqueteAtacar(
			final int id, final int idEnemigo,
			final int nuevaSalud, final int nuevaEnergia,
			final int nuevaSaludEnemigo, final  int nuevaEnergiaEnemigo,
			final int nuevaDefensa, final int nuevaDefensaEnemigo,
			final double probEvitarDano, final double probEvitarDanoEnemgio) {
		setComando(Comando.ATACAR);
		this.id = id;
		this.idEnemigo = idEnemigo;
		this.nuevaSaludPersonaje = nuevaSalud;
		this.nuevaEnergiaPersonaje = nuevaEnergia;
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
		mapPersonaje.put("salud", nuevaSalud);
		mapPersonaje.put("energia", nuevaEnergia);
		mapPersonaje.put("defensa", nuevaDefensa);
		mapPersonaje.put("probEvitarDanio", probEvitarDano);
		mapEnemigo.put("salud", nuevaSaludEnemigo);
		mapEnemigo.put("energia", nuevaEnergiaEnemigo);
		mapEnemigo.put("defensa", nuevaDefensaEnemigo);
		mapEnemigo.put("probEvitarDanio", probEvitarDanoEnemgio);
	}

	/**
	 * Devuelve la id del personaje
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece la id del personaje
	 * @param id id personaje
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Devuelve la id del enemigo
	 * @return int
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Establece la id del enemigo
	 * @param idEnemigo id enemigo
	 */
	public void setIdEnemigo(final int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Devuelve la salud del personaje
	 * @return int
	 */
	public int getNuevaSaludPersonaje() {
		return nuevaSaludPersonaje;
	}

	/**
	 * Establece la salud del personaje
	 * @param nuevaSaludPersonaje salud personaje
	 */
	public void setNuevaSaludPersonaje(final int nuevaSaludPersonaje) {
		this.nuevaSaludPersonaje = nuevaSaludPersonaje;
	}

	/**
	 * Devuelve la energia del personaje
	 * @return int
	 */
	public int getNuevaEnergiaPersonaje() {
		return nuevaEnergiaPersonaje;
	}

	/**
	 * Establece la energia del personaje
	 * @param nuevaEnergiaPersonaje energia personaje
	 */
	public void setNuevaEnergiaPersonaje(final int nuevaEnergiaPersonaje) {
		this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
	}

	/**
	 * Devuelve la salud del enemigo
	 * @return int
	 */
	public int getNuevaSaludEnemigo() {
		return nuevaSaludEnemigo;
	}

	/**
	 * Establece la salud del enemigo
	 * @param nuevaSaludEnemigo salud enemigo
	 */
	public void setNuevaSaludEnemigo(final int nuevaSaludEnemigo) {
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
	}

	/**
	 * Devuelve la energia del enemigo
	 * @return int
	 */
	public int getNuevaEnergiaEnemigo() {
		return nuevaEnergiaEnemigo;
	}

	/**
	 * Establece la energia del enemigo
	 * @param nuevaEnergiaEnemigo Energia enemigo
	 */
	public void setNuevaEnergiaEnemigo(final int nuevaEnergiaEnemigo) {
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
	}

	/**
	 * Devuelve el mapa del personaje
	 * @return HashMap<String, Number>
	 */
	public HashMap<String, Number> getMapPersonaje() {
		return mapPersonaje;
	}

	/**
	 * Devuelve el mapa del enemigo
	 * @return HashMap<String, Number>
	 */
	public HashMap<String, Number> getMapEnemigo() {
		return mapEnemigo;
	}

}
