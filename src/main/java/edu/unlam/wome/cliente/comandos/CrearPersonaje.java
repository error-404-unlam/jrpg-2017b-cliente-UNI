package edu.unlam.wome.cliente.comandos;

import javax.swing.JOptionPane;

import comandos.ComandosCliente;
import edu.unlam.wome.cliente.mensajeria.PaquetePersonaje;

/**
 * Clase CrearPersonaje: Crea el personaje.
 * @author Miguel
 */
public class CrearPersonaje extends ComandosCliente {

	@Override
	public void ejecutar() {
		JOptionPane.showMessageDialog(null, "Registro exitoso.");
		this.getCliente().setPaquetePersonaje((PaquetePersonaje) getGson().
				fromJson(getCadenaLeida(), PaquetePersonaje.class));
		this.getCliente().getPaqueteUsuario().setInicioSesion(true);

	}

}
