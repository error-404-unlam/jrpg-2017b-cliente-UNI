package comandos;

import estados.Estado;
import interfaz.MenuInfoPersonaje;
import mensajeria.PaqueteFinalizarBatalla;

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
				MenuInfoPersonaje.menuPerderBatalla); // Informar que se perdió la batalla
		Estado.setEstado(this.getJuego().getEstadoJuego());
	}

}
