package edu.unlam.wome.potenciados;

import java.util.LinkedList;

public class PersonajesPotenciados {
	public static LinkedList<PersonajesPotenciados> potenciados = new LinkedList<>();
	
	private int idPersonaje;
	private int modoJuego;

	public PersonajesPotenciados(int idPersonaje, int modoJuego) {
		this.idPersonaje = idPersonaje;
		this.modoJuego = modoJuego;
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public int getModoJuego() {
		return modoJuego;
	}
}
