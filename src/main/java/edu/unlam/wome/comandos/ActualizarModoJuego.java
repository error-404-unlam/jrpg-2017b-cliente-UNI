package edu.unlam.wome.comandos;

import edu.unlam.wome.mensajeria.PaqueteModoJuego;

public class ActualizarModoJuego extends ComandosEscucha{

	@Override
	public void ejecutar() {
		PaqueteModoJuego paquete = getGson().fromJson(getCadenaLeida(), PaqueteModoJuego.class);
		getJuego().getPersonaje().setModoJuego(paquete.getModo());
	}

}
