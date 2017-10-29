package comandos;

import mensajeria.PaquetePersonaje;

/**
 * Comando Actualizar Inventario.
 * @author Miguel
 */
public class ActualizarInventario extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.
				fromJson(cadenaLeida, PaquetePersonaje.class);
		juego.getPersonajesConectados().remove(paquetePersonaje.getId());
		juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
		if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
			juego.actualizarPersonaje();
			juego.getEstadoJuego().actualizarPersonaje();
			juego.getCli().actualizarItems(paquetePersonaje);
			juego.getCli().actualizarPersonaje(juego.getPersonajesConectados().
					get(paquetePersonaje.getId()));
		}

	}

}
