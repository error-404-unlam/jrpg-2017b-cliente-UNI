package comandos;

import java.util.Map;

import javax.swing.DefaultListModel;

import chat.VentanaContactos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;

/**
 * Clase Conexion.
 * @author Miguel
 */
public class Conexion extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteDePersonajes pdp = (PaqueteDePersonajes) gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
		this.getJuego().setPersonajesConectados(pdp.getPersonajes());
		actualizarLista(pdp);
	}

	/**
	 * Actualista la lista con de Contactos.
	 * @param pdp PaqueteDePersonajes
	 */
	private void actualizarLista(final PaqueteDePersonajes pdp) {
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		VentanaContactos.getList().removeAll();
		for (Map.Entry<Integer, PaquetePersonaje> personaje : pdp.getPersonajes().entrySet()) {
			modelo.addElement(personaje.getValue().getNombre());
		}
		modelo.removeElement(this.getJuego().getPersonaje().getNombre());
		VentanaContactos.getList().setModel(modelo);
	}
}
