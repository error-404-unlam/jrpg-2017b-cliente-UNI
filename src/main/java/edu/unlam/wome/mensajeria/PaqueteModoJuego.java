package edu.unlam.wome.mensajeria;

import java.io.Serializable;

/**
 * Clase que se encarga de contener toda la informacion referente a 
 * los trucos ingresados por los personajes
 * @author Federico
 *
 */
public class PaqueteModoJuego extends Paquete implements Serializable{

	// Modo normal no hace nada
	public static final int NORMAL = 0;
	
	// Modo dios no recibe daño
	public static final int MODO_DIOS = 1;
	
	// Traspaza los muros
	public static final int MODO_MUROS = 2;
	
	// Se hace invisible
	public static final int MODO_INV = 3;
	
	private static final long serialVersionUID = 1L;
	private int modo; // Truco Seleccionado
	private int idPersonaje; // Personaje que realizo el truco
	
	
	/**
	 * Por defecto el jugador no tiene truco
	 */
	public PaqueteModoJuego() {
		this(NORMAL);
	}
	
	/** 
	 * Constructor personaje con truco
	 * @param modo
	 */
	public PaqueteModoJuego(int modo) {
		this.modo = modo;
	}

	/**
	 * Devuelve el modo seleccionado
	 * @return modo
	 */
	public int getModo() {
		return modo;
	}

	/**
	 * Permite configurar un modo 
	 * @param modo
	 */
	public void setModo(int modo) {
		this.modo = modo;
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
}
