package mensajeria;

import java.io.Serializable;
/**
 * clase PaqueteMovimiento
 *
 * @author lesanmartin
 *
 */
public class PaqueteMovimiento extends Paquete implements Serializable, Cloneable {

	private int id;
	private float posX;
	private float posY;
	private int direccion;
	private int frame;

	/**
	 * Constructor de la clase 
	 *
	 */
	public PaqueteMovimiento() {
		setComando(Comando.MOVIMIENTO);
	}
	
	/**
	 * Constructor parametrizado de la clase
	 *
	 * @param idPersonaje
	 */
	public PaqueteMovimiento(final int idPersonaje) {
		id = idPersonaje;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Constructor parametrizado de la clase
	 *
	 * @param idPersonaje
	 * @param posX
	 * @param posY
	 */
	public PaqueteMovimiento(final int idPersonaje, final float posX, final float posY) {
		this.id = idPersonaje;
		this.posX = posX;
		this.posY = posY;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Retorna el id del personaje
	 *
	 * @return
	 */
	public int getIdPersonaje() {
		return id;
	}

	/**
	 * Setea el id del personaje
	 *
	 * @param idPersonaje
	 */
	public void setIdPersonaje(final int idPersonaje) {
		this.id = idPersonaje;
	}

	/**
	 * Retorna la posicion x 
	 *
	 * @return posx
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * Setea la posicion x 
	 *
	 * @param posX
	 */
	public void setPosX(final float posX) {
		this.posX = posX;
	}

	/**
	 * Retorna la posicion de Y
	 *
	 * @return posY
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * Setea la posicion Y 
	 *
	 * @param posY
	 */
	public void setPosY(final float posY) {
		this.posY = posY;
	}

	/**
	 * Retorna la direccion 
	 *
	 * @return direccion
	 */
	public int getDireccion() {
		return direccion;
	}

	/**
	 * Setea la direccion
	 *
	 * @param direccion
	 */
	public void setDireccion(final int direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna el Frame
	 *
	 * @return frame
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * Setea el Frame
	 *
	 * @param frame
	 */
	public void setFrame(final int frame) {
		this.frame = frame;
	}

	/**
	 * Clona objeto
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
