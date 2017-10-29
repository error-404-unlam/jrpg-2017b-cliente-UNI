package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comandos.ComandosEscucha;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;

/**
 * La clase EscuchaMensajes tiene como función escuchar los mensajes que se enviarán al servidor.
 */
public class EscuchaMensajes extends Thread {

	private Juego juego;
	private Cliente cliente;
	private ObjectInputStream entrada;
	private final Gson gson = new Gson();

	// private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	// private Map<Integer, PaquetePersonaje> personajesConectados;
	/**
	 * Constructor de EscuchaMensaje
	 * 
	 * @param juego
	 *            Juego del que se escucha el mensaje.
	 */
	public EscuchaMensajes(final Juego juego) {
		this.juego = juego;
		cliente = juego.getCli();
		entrada = cliente.getEntrada();
	}

	@Override
	public void run() {

		try {

			Paquete paquete;

			ComandosEscucha comand;
			juego.setPersonajesConectados(new HashMap<Integer, PaquetePersonaje>());
			juego.setUbicacionPersonajes(new HashMap<Integer, PaqueteMovimiento>());

			while (true) {

				String objetoLeido = (String) entrada.readObject();

				paquete = gson.fromJson(objetoLeido, Paquete.class);
				comand = (ComandosEscucha) paquete.getObjeto(Comando.NOMBREPAQUETE);
				comand.setJuego(juego);
				comand.setCadena(objetoLeido);
				comand.ejecutar();

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falló la conexión con el servidor al intentar escuchar un mensaje.");
		}
	}
	/**
	 * Pide la ubicación de los personajes.
	 * 
	 * @return Devuelve el mapa con la ubicacion de los personajes.
	 */

	/**
	 * Pide los personajes conectados.
	 * 
	 * @return Devuelve el mapa con los personajes conectados.
	 */

}