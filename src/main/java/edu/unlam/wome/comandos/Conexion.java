package edu.unlam.wome.comandos;

import java.util.LinkedList;
import java.util.Map;

import javax.swing.DefaultListModel;

import edu.unlam.wome.chat.VentanaContactos;
import edu.unlam.wome.mensajeria.PaqueteDePersonajes;
import edu.unlam.wome.mensajeria.PaqueteModoJuego;
import edu.unlam.wome.mensajeria.PaquetePersonaje;
import edu.unlam.wome.potenciados.PersonajesPotenciados;

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
		PersonajesPotenciados.potenciados.removeAll(new LinkedList<PersonajesPotenciados>());
		for (Map.Entry<Integer, PaquetePersonaje> personaje : pdp.getPersonajes().entrySet()) {
			if(personaje.getValue().getModoJuego() == PaqueteModoJuego.MODO_DIOS) {
				PersonajesPotenciados.potenciados.add(
						new PersonajesPotenciados(personaje.getValue().getId() , personaje.getValue().getModoJuego() ));
			}
			modelo.addElement(personaje.getValue().getNombre());
		}
		modelo.removeElement(this.getJuego().getPersonaje().getNombre());
		VentanaContactos.getList().setModel(modelo);
	}
}
