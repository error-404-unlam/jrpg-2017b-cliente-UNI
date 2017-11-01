package comandos;

import mensajeria.PaquetePersonaje;

/**
 * Comando Actualizar Inventario.
 * @author Miguel
 */
public class ActualizarInventario extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) getGson().
				fromJson(getCadenaLeida(), PaquetePersonaje.class);
		this.getJuego().getPersonajesConectados().remove(paquetePersonaje.getId());
		this.getJuego().getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
		if (this.getJuego().getPersonaje().getId() == paquetePersonaje.getId()) {
			this.getJuego().actualizarPersonaje();
			this.getJuego().getEstadoJuego().actualizarPersonaje();
			this.getJuego().getCli().actualizarItems(paquetePersonaje);
			this.getJuego().getCli().actualizarPersonaje(this.getJuego().getPersonajesConectados().
					get(paquetePersonaje.getId()));
		}

	}

}
