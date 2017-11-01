package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;

/**
 * Clase personaje
 *
 * @author lesanmartin
 */
public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel = 1;
	private int experiencia;
	private ArrayList<Item> items = new ArrayList<Item>();
	private int puntosNoAsignados;
	private int puntosAsignadosFuerza;
	private int puntosAsignadosDestreza;
	private int puntosAsignadosInteligencia;

	/**
	 * Setea el estado
	 *
	 * @throws IOException lanza excepcion
	 */
	public PaquetePersonaje() throws IOException {
		estado = Estado.getEstadoOffLine();
	}

	/**
	 * Retorna el estado
	 *
	 * @return estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Setea el estado
	 *
	 * @param estado parametro estado
	 */
	public void setEstado(final int estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el mapa
	 *
	 * @return idMapa
	 */
	public int getMapa() {
		return idMapa;
	}

	/**
	 * Setea el mapa
	 *
	 * @param mapa parametro mapa
	 */
	public void setMapa(final int mapa) {
		idMapa = mapa;
	}

	/**
	 * Retorna el nivel
	 *
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Setea el nivel
	 *
	 * @param nivel parametro nivel
	 */
	public void setNivel(final int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Retorna la experiencia
	 *
	 * @return experiencia
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Setea la experiencia
	 *
	 * @param experiencia parametro experiencia
	 */
	public void setExperiencia(final int experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * Retorna el id
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 *
	 * @param id parametro id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * Retorna la casta
	 *
	 * @return casta
	 */
	public String getCasta() {
		return casta;
	}

	/**
	 * setea la casta
	 *
	 * @param casta parametro casta
	 */
	public void setCasta(final String casta) {
		this.casta = casta;
	}

	/**
	 * Retorna el nombre
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre
	 *
	 * @param nombre parametro nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna la raza
	 *
	 * @return raza
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Setea la raza
	 *
	 * @param raza parametro raza
	 */
	public void setRaza(final String raza) {
		this.raza = raza;
	}

	/**
	 * Retorna la salud tope
	 *
	 * @return saludTope
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Setea la salud tope
	 *
	 * @param saludTope parametro saludoTope
	 */
	public void setSaludTope(final int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * Retorna la energia tope
	 *
	 * @return energiaTope
	 */
	public int getEnergiaTope() {
		return energiaTope;
	}

	/**
	 * Setea la energia tope
	 *
	 * @param energiaTope parametro energia tope
	 */
	public void setEnergiaTope(final int energiaTope) {
		this.energiaTope = energiaTope;
	}

	/**
	 * Retorna la fuerza
	 *
	 * @return fuerza
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Setea la fuerza
	 *
	 * @param fuerza parametro fuerza
	 */
	public void setFuerza(final int fuerza) {
		this.fuerza = fuerza;
	}

	/**
	 * Retorna la destreza
	 *
	 * @return destreza
	 */
	public int getDestreza() {
		return destreza;
	}

	/**
	 * Setea la destreza
	 *
	 * @param destreza parametro destreza
	 */
	public void setDestreza(final int destreza) {
		this.destreza = destreza;
	}

	/**
	 * Retorna la inteligencia
	 *
	 * @return inteligencia
	 */
	public int getInteligencia() {
		return inteligencia;
	}

	/**
	 * Setea la inteligencia
	 *
	 * @param inteligencia parametro inteligencia
	 */
	public void setInteligencia(final int inteligencia) {
		this.inteligencia = inteligencia;
	}

	/**
	 * Clona el objeto
	 *
	 * @return obj
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	/**
	 * añade item
	 *
	 * @param i parametro i
	 */
	public final void anadirItem(final Item i) {
		items.add(i);
	}

	/**
	 * Elimina item
	 *
	 * @param i parametro i
	 */
	public final void removerItem(final Item i) {
		items.remove(i);
	}

	/**
	 * Retorna los items
	 *
	 * @return array list de items
	 */
	public ArrayList<Item> getItems() {
		return new ArrayList<Item>(items);
	}

	/**
	 * Setea los items
	 *
	 * @param items parametro items
	 */
	public final void setItems(final ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * Retorna el id del item
	 *
	 * @param index parametro index
	 * @return items.get(index).getIdItem
	 */
	public final int getItemID(final int index) {
		return items.get(index).getIdItem();
	}

	/**
	 * Añade item
	 *
	 * @param idItem parametro idItem
	 * @param nombre parametro nombre
	 * @param wearLocation parametro wearLocation
	 * @param bonusSalud parametro bonusSalud
	 * @param bonusEnergia parametro bonusEnergia
	 * @param bonusAtaque parametro bonusAtaque
	 * @param bonusDefensa parametro bonusDefensa
	 * @param bonusMagia parametro bonusMagia
	 * @param foto parametro foto
	 * @param fotoEquipado parametro fotoEquipado
	 */
	public final void anadirItem(
			final int idItem, final String nombre, final int wearLocation, 
			final int bonusSalud, final int bonusEnergia,
			final int bonusAtaque, final int bonusDefensa,
			final int bonusMagia, final String foto,
			final String fotoEquipado) {
		try {
			items.add(
					new Item(
							idItem, nombre, wearLocation,
							bonusSalud, bonusEnergia,
							bonusAtaque, bonusDefensa,
							bonusMagia, foto, fotoEquipado));
			useBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al añadir item");

		}
	}

	/**
	 * Remueve bonus
	 */
	public final void removerBonus() {
		int i = 0;
		while (i < items.size()) {
			sacarBonus(
					items.get(i).getBonusSalud(),
					items.get(i).getBonusEnergia(),
					items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(),
					items.get(i).getBonusInteligencia());
			i++;
		}
	}

	/**
	 * Saca el bonus
	 *
	 * @param bonusSalud parametro bonusSalud
	 * @param bonusEnergia parametro bonusEnergia
	 * @param bonusAtaque parametro bonusAtaque
	 * @param bonusDefensa parametro bonusDefensa
	 * @param bonusMagia parametro bonusMagia
	 */
	public final void sacarBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque,
			final int bonusDefensa, final int bonusMagia) {
		saludTope -= bonusSalud;
		energiaTope -= bonusEnergia;
		fuerza -= bonusAtaque;
		destreza -= bonusDefensa;
		inteligencia -= bonusMagia;
	}

	/**
	 * Poner bonus
	 */
	public final void ponerBonus() {
		int i = 0;
		while (i < items.size()) {
			useBonus(
					items.get(i).getBonusSalud(),
					items.get(i).getBonusEnergia(),
					items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(),
					items.get(i).getBonusInteligencia());
			i++;
		}
	}

	/**
	 * Usar bonus
	 *
	 * @param bonusSalud parametro bonusSalud
	 * @param bonusEnergia parametro bonusEnergia
	 * @param bonusAtaque parametro bonusAtaque
	 * @param bonusDefensa parametro bonusDefensa
	 * @param bonusMagia parametro bonusMagia
	 */
	public void useBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque,
			final int bonusDefensa, final int bonusMagia) {
		saludTope += bonusSalud;
		energiaTope += bonusEnergia;
		fuerza += bonusAtaque;
		destreza += bonusDefensa;
		inteligencia += bonusMagia;
	}

	/**
	 * Retorna la cantidad de items
	 *
	 * @return items.size
	 */
	public int getCantItems() {
		return items.size();
	}

	/**
	 * Añade items
	 *
	 * @param idItem parametro idItem
	 */
	public void anadirItem(final int idItem) {
		try {
			items.add(new Item(idItem, null, 0, 0, 0, 0, 0, 0, null, null));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al añadir item");
		}

	}

	/**
	 * Iterator
	 *
	 * @return items.iterator
	 */
	public Iterator<Item> getIterator() {
		return items.iterator();
	}

	/**
	 * Remueve ultimo item
	 */
	public void removerUltimoItem() {
		items.remove(items.size() - 1);

	}

	/**
	 * Retorna nuevo item
	 *
	 * @return nuevo item
	 */
	public boolean nuevoItem() {
		return items.get(items.size() - 1).getNombre() == null;
	}

	/**
	 * Pone el bonus
	 *
	 * @param cantItems parametro cantItems
	 */
	public void ponerBonus(final int cantItems) {
		int i = 0;
		while (i < cantItems) {
			useBonus(
					items.get(i).getBonusSalud(),
					items.get(i).getBonusEnergia(),
					items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(),
					items.get(i).getBonusInteligencia());
			i++;
		}
	}

	/**
	 * Saca el ultimo item
	 */
	public void sacarUltimoItem() {
		int i = items.size() - 1;
		if (i >= 0) {
			sacarBonus(
					items.get(i).getBonusSalud(),
					items.get(i).getBonusEnergia(),
					items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(),
					items.get(i).getBonusInteligencia());
		}
	}

	/**
	 * Pone el ultimo item
	 */
	public void ponerUltimoItem() {
		int i = items.size() - 1;
		if (i >= 0) {
			useBonus(
					items.get(i).getBonusSalud(),
					items.get(i).getBonusEnergia(),
					items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(),
					items.get(i).getBonusInteligencia());
		}
	}

	/**
	 * Elimina items
	 */
	public void eliminarItems() {
		items.removeAll(items);
	}

	/**
	 * Actualiza trueque
	 * @param itemsT parametro items
	 */
	public void actualizarTrueque(final ArrayList<Item> itemsT) {
		this.items.removeAll(this.items);
		for (Item item : itemsT) {
			this.items.add(item);
		}
	}

	/**
	 * Retorna los puntos no asignados
	 *
	 * @return puntos no asignados
	 */
	public int getPuntosNoAsignados() {
		return puntosNoAsignados;
	}

	/**
	 * Setea los puntos no asignados
	 *
	 * @param puntosNoAsignados parametro puntos no asignados
	 */
	public void setPuntosNoAsignados(final int puntosNoAsignados) {
		this.puntosNoAsignados = puntosNoAsignados;
	}

	/**
	 * Retorna los puntos asignados a fuerza
	 *
	 * @return puntosAsignadosFuerza
	 */
	public int getPuntosAsignadosFuerza() {
		return puntosAsignadosFuerza;
	}

	/**
	 * Setea los puntos asignados a fuerza
	 *
	 * @param puntosAsignadosFuerza parametro puntosAsignadosFuerza
	 */
	public void setPuntosAsignadosFuerza(final int puntosAsignadosFuerza) {
		this.puntosAsignadosFuerza = puntosAsignadosFuerza;
	}

	/**
	 * Retorna los puntos Asignados a destreza
	 *
	 * @return puntosAsignadosDestreza
	 */
	public int getPuntosAsignadosDestreza() {
		return puntosAsignadosDestreza;
	}

	/**
	 * Setea los puntos asignados a destreza
	 *
	 * @param puntosAsignadosDestreza parametros puntos asignados a destreza
	 */
	public void setPuntosAsignadosDestreza(final int puntosAsignadosDestreza) {
		this.puntosAsignadosDestreza = puntosAsignadosDestreza;
	}

	/**
	 * Retorna los puntos asignados a inteligencia
	 *
	 * @return puntos asignados a inteligencuia
	 */
	public int getPuntosAsignadosInteligencia() {
		return puntosAsignadosInteligencia;
	}

	/**
	 * Setea los puntos asignados a inteligencia
	 *
	 * @param puntosAsignadosInteligencia parametros de puntos asignados a inteligencia
	 */
	public void setPuntosAsignadosInteligencia(final int puntosAsignadosInteligencia) {
		this.puntosAsignadosInteligencia = puntosAsignadosInteligencia;
	}
}
