package comandos;

import mensajeria.PaqueteDeMovimientos;

/**
 * Clase Movimiento de Personaje.
 * @author Miguel
 */
public class Movimiento extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteDeMovimientos pdm = (PaqueteDeMovimientos) getGson().
				fromJson(getCadenaLeida(), PaqueteDeMovimientos.class);
		this.getJuego().setUbicacionPersonajes(pdm.getPersonajes());

	}

}
