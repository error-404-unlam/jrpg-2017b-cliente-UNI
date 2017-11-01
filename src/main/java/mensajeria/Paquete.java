package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Clase Paquete
 *
 * @author lesanmartin
 *
 */
public class Paquete implements Serializable, Cloneable {

	private static String msjExito = "1";
	private static String msjFracaso = "0";

	private String mensaje;
	private String ip;
	private int comando;

	/**
	 * Constructor de la clase
	 */
	public Paquete() {
	}

	/**
	 * Constructor de la clase parametrizado
	 *
	 * @param mensaje parametros mensaje
	 * @param nick parametros nick
	 * @param ip parametros ip
	 * @param comando parametro comando
	 */
	public Paquete(final String mensaje, final String nick, final String ip, final int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}

	/**
	 * Constructor de la clase parametrizado
	 *
	 * @param mensaje parametro mensaje
	 * @param comando parametro comando
	 */
	public Paquete(final String mensaje, final int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	/**
	 * Constructor de la clase parametrizado
	 *
	 * @param comando parametro comando
	 */
	public Paquete(final int comando) {
		this.comando = comando;
	}

	/**
	 * Setea el mensaje
	 *
	 * @param mensaje parametro mensaje
	 */
	public void setMensaje(final String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Setea la ip
	 *
	 * @param ip parametro ip
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * Setea el comando
	 *
	 * @param comando parametro comando
	 */
	public void setComando(final int comando) {
		this.comando = comando;
	}

	/**
	 * Retorna el mensaje
	 *
	 * @return mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Retorna la ip
	 *
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Retorna el comando
	 *
	 * @return comando
	 */
	public int getComando() {
		return comando;
	}

	/**
	 * Clona el objeto
	 *
	 * @return obj
	 */
	@Override
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			JOptionPane.showMessageDialog(null, "Error al clonar");
		}
		return obj;
	}

	/**
	 * Retorna el objeto
	 *
	 * @param nombrePaquete parametro nombrePaquete
	 * @return c
	 */
	public Comando getObjeto(final String nombrePaquete) {
		Comando c = null;
		try {
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMES[comando]).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Retorna el objeto seteado
	 *
	 * @param nombrePaquete parametro nombrePaquete
	 * @param accion parametro accion
	 * @return c
	 */
	public static Comando getObjetoSet(final String nombrePaquete, final int accion) {
		Comando c = null;
		try {
			c = (Comando) Class.forName(nombrePaquete + "." + Comando.CLASSNAMESBIS[accion]).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * @return msjExito
	 */
	public static String getMsjExito() {
		return msjExito;
	}

	/**
	 * @param msjExito a setear
	 */
	public static void setMsjExito(final String msjExito) {
		Paquete.msjExito = msjExito;
	}

	/**
	 * @return msjFracaso
	 */
	public static String getMsjFracaso() {
		return msjFracaso;
	}

	/**
	 * @param msjFracaso a setear
	 */
	public static void setMsjFracaso(final String msjFracaso) {
		Paquete.msjFracaso = msjFracaso;
	}
}
