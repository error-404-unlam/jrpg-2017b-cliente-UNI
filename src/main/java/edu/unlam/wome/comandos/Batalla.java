package edu.unlam.wome.comandos;

import edu.unlam.wome.estados.Estado;
import edu.unlam.wome.estados.EstadoBatalla;
import edu.unlam.wome.mensajeria.PaqueteBatalla;

/**
 * Comando Batalla
 *
 * @author Miguel
 */
public class Batalla extends ComandosEscucha {

	@Override
	public void ejecutar() {

		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) getGson().fromJson(getCadenaLeida(), PaqueteBatalla.class);
		this.getJuego().getPersonaje().setEstado(Estado.getEstadoBatalla());
		Estado.setEstado(null);
		this.getJuego().setEstadoBatalla(new EstadoBatalla(this.getJuego(), paqueteBatalla));
		Estado.setEstado(this.getJuego().getEstadoBatalla());

	}

}
