package comandos;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

/**
 * Comando Batalla
 *
 * @author Miguel
 */
public class Batalla extends ComandosEscucha {

	@Override
	public void ejecutar() {

		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) gson.fromJson(cadenaLeida, PaqueteBatalla.class);
		this.getJuego().getPersonaje().setEstado(Estado.getEstadoBatalla());
		Estado.setEstado(null);
		this.getJuego().setEstadoBatalla(new EstadoBatalla(this.getJuego(), paqueteBatalla));
		Estado.setEstado(this.getJuego().getEstadoBatalla());

	}

}
