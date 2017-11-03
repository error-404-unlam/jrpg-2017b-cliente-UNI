package edu.unlam.wome.cliente.comandos;

import comandos.ComandosEscucha;
import edu.unlam.wome.cliente.estados.Estado;
import edu.unlam.wome.cliente.interfaz.MenuInfoPersonaje;
import edu.unlam.wome.cliente.mensajeria.PaqueteFinalizarBatalla;

/**
 * Clase FinalizarBatalla. Finaliza la batalla.
 *
 * @author Miguel
 */
public class FinalizarBatalla extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) getGson().
				fromJson(getCadenaLeida(), PaqueteFinalizarBatalla.class);
		this.getJuego().getPersonaje().setEstado(Estado.getEstadoJuego());
		this.getJuego().getEstadoJuego().setHaySolicitud(true, this.getJuego().getPersonaje(),
				MenuInfoPersonaje.MENU_PERDER_BATALLA); // Informar que se perdió la batalla
		Estado.setEstado(this.getJuego().getEstadoJuego());
	}

}
