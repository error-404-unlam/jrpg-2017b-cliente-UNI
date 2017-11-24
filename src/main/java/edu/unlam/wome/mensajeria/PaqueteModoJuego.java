package edu.unlam.wome.mensajeria;

import java.io.Serializable;

public class PaqueteModoJuego extends Paquete implements Serializable{

	public static final int NORMAL = 0;
	public static final int MODO_DIOS = 1;
	public static final int MODO_MUROS = 2;
	public static final int MODO_INV = 3;
	
	private static final long serialVersionUID = 1L;
	private int modo;
	private int idPersonaje;
	
	
	
	public PaqueteModoJuego() {
		this(NORMAL);
	}
	
	public PaqueteModoJuego(int modo) {
		this.modo = modo;
	}

	public int getModo() {
		return modo;
	}

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
