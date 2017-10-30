package comandos;

import javax.swing.JOptionPane;

import mensajeria.PaquetePersonaje;

/**
 * Clase CrearPersonaje: Crea el personaje.
 * @author Miguel
 */
public class CrearPersonaje extends ComandosCliente {

	@Override
	public void ejecutar() {
		JOptionPane.showMessageDialog(null, "Registro exitoso.");
		this.getCliente().setPaquetePersonaje((PaquetePersonaje) gson.
				fromJson(cadenaLeida, PaquetePersonaje.class));
		this.getCliente().getPaqueteUsuario().setInicioSesion(true);

	}

}
