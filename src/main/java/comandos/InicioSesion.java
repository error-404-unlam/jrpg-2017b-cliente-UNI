package comandos;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

/**
 * Clase InicioSesion. Encargada de iniciar la sesion del juego.
 * @author Miguel
 */
public class InicioSesion extends ComandosCliente {

	@Override
	public void ejecutar() {
		Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
		if (paquete.getMensaje().equals(Paquete.msjExito)) {

			// El usuario ya inicio sesi�n
			this.getCliente().getPaqueteUsuario().setInicioSesion(true);

			// Recibo el paquete personaje con los datos
			this.getCliente().setPaquetePersonaje(gson.fromJson(cadenaLeida, PaquetePersonaje.class));

		} else {
			if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "Error al iniciar sesión."
						+ "Revise el usuario y la contraseña");
			}

			// El usuario no pudo iniciar sesión
			this.getCliente().getPaqueteUsuario().setInicioSesion(false);
		}

	}

}
