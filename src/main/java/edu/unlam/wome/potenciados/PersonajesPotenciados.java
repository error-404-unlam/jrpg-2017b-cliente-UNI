package edu.unlam.wome.potenciados;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Clase para gestionar a todos los personajes que realizan trucos
 * 
 *	
 */
public class PersonajesPotenciados implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// Listado de personajes truqueados
	public static LinkedList<PersonajesPotenciados> potenciados = new LinkedList<>();
	
	private int idPersonaje; // idPersonaje con truco
	private int modoJuego;  // Truco seleccionado

	public PersonajesPotenciados(int idPersonaje, int modoJuego) {
		this.idPersonaje = idPersonaje;
		this.modoJuego = modoJuego;
	}

	/**
	 * Devuelve el id del personaje
	 * @return idPersonaje
	 */
	public int getIdPersonaje() {
		return idPersonaje;
	}

	/**
	 * Devuelve el truco seleccionado
	 * @return
	 */
	public int getModoJuego() {
		return modoJuego;
	}
}
