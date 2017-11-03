package edu.unlam.wome.cliente.comandos;

import comandos.ComandosEscucha;
import edu.unlam.wome.cliente.estados.Estado;
import edu.unlam.wome.cliente.estados.EstadoBatalla;
import edu.unlam.wome.cliente.mensajeria.PaqueteBatalla;

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
