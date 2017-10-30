package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.Item;

/**
 * Clase Paquete Comerciar
 *
 * @author lesanmartin
 *
 */
public class PaqueteComerciar extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idEnemigo;
	private int listo = 0;
	private ArrayList<Item> itemsADar = new ArrayList<Item>();
	private ArrayList<Item> itemsAObtener = new ArrayList<Item>();
	private boolean solicitudDeComercio;

	/**
	 * Constructor de la clase
	 */
	public PaqueteComerciar() {
		setComando(Comando.COMERCIO);
		solicitudDeComercio = true;
	}

	/**
	 * Retorna si es solicitud de comercio 
	 *
	 * @return
	 */
	public boolean isSolicitudDeComercio() {
		return solicitudDeComercio;
	}

	/**
	 * Setea la solicitud de comercio
	 *
	 * @param solicitudDeComercio
	 */
	public void setSolicitudDeComercio(final boolean solicitudDeComercio) {
		this.solicitudDeComercio = solicitudDeComercio;
	}

	/**
	 * Retorna los items a dar
	 *
	 * @return
	 */
	public ArrayList<Item> getItemsADar() {
		return itemsADar;
	}

	/**
	 * Setea los items a dar
	 *
	 * @param itemsADar
	 */
	public void setItemsADar(final ArrayList<Item> itemsADar) {
		this.itemsADar = itemsADar;
	}

	/**
	 * Retorna el id
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 *
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Retorna el id enemigo
	 *
	 * @return
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Setea el id enemigo
	 *
	 * @param idEnemigo
	 */
	public void setIdEnemigo(final int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Retorna si esta listo
	 *
	 * @return
	 */
	public int getListo() {
		return listo;
	}
	
	/**
	 * Aumenta la variable listo
	 */
	public void aumentarListo() {
		this.listo++;
	}

	/**
	 * Disminuye la variable listo
	 */
	public void disminuirListo() {
		this.listo--;
	}
	
	/**
	 * Retorna los items a obtener
	 *
	 * @return
	 */
	public ArrayList<Item> getItemsAObtener() {
		return itemsAObtener;
	}

	/**
	 * Setea los items a obtener
	 *
	 * @param itemsAObtener
	 */
	public void setItemsAObtener(final ArrayList<Item> itemsAObtener) {
		this.itemsAObtener = itemsAObtener;
	}
}
