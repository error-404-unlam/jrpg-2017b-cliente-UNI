package edu.unlam.wome.cliente.comandos;

import java.util.Map;

import javax.swing.DefaultListModel;

import comandos.ComandosEscucha;
import edu.unlam.wome.cliente.chat.VentanaContactos;
import edu.unlam.wome.cliente.mensajeria.PaqueteDePersonajes;
import edu.unlam.wome.cliente.mensajeria.PaquetePersonaje;

/**
 * Clase Conexion.
 * @author Miguel
 */
public class Conexion extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteDePersonajes pdp = (PaqueteDePersonajes) getGson().fromJson(getCadenaLeida(), PaqueteDePersonajes.class);
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
