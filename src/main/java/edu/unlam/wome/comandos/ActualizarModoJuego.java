package edu.unlam.wome.comandos;

import java.util.Map;

import edu.unlam.wome.mensajeria.PaqueteModoJuego;
import edu.unlam.wome.mensajeria.PaquetePersonaje;
import edu.unlam.wome.potenciados.PersonajesPotenciados;


public class ActualizarModoJuego extends ComandosEscucha{

	@Override
	public void ejecutar() {
		PaqueteModoJuego paquete = getGson().fromJson(getCadenaLeida(), PaqueteModoJuego.class);
		for (Map.Entry<Integer, PaquetePersonaje> entry : getJuego().getPersonajesConectados().entrySet()) {
		   if(paquete.getIdPersonaje() == entry.getKey()) {
			  PersonajesPotenciados.potenciados.add(new PersonajesPotenciados(entry.getKey(), paquete.getModo()));
		   }
		}
	}
}
