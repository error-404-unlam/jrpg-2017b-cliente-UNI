package edu.unlam.wome.cliente;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import edu.unlam.wome.comandos.ComandosCliente;
import edu.unlam.wome.frames.MenuCarga;
import edu.unlam.wome.frames.MenuComerciar;
import edu.unlam.wome.frames.MenuJugar;
import edu.unlam.wome.frames.MenuMapas;
import edu.unlam.wome.juego.Juego;
import edu.unlam.wome.mensajeria.Comando;
import edu.unlam.wome.mensajeria.Paquete;
import edu.unlam.wome.mensajeria.PaqueteComerciar;
import edu.unlam.wome.mensajeria.PaqueteMensaje;
import edu.unlam.wome.mensajeria.PaquetePersonaje;
import edu.unlam.wome.mensajeria.PaqueteUsuario;

/**
 * La clase Cliente tiene como función ejecutar el cliente.
 */
public class Cliente extends Thread {

	private Socket cliente;
	private String miIp;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;

	private final int anchoVentanaWoME = 800;
	private final int altoVentanaWoME = 600;
	// Objeto gson
	private final Gson gson = new Gson();

	// Paquete usuario y paquete personaje
	private PaqueteUsuario paqueteUsuario;
	private PaquetePersonaje paquetePersonaje;
	private PaqueteComerciar paqueteComercio;
	private PaqueteMensaje paqueteMensaje = new PaqueteMensaje();

	// Acciones que realiza el usuario
	private int accion;

	// MENU COMERCIAR
	private MenuComerciar m1;

	// Ip y puerto
	private String ip;
	private int puerto;

	/**
	 * Pide la accion
	 * @return Devuelve la accion
	 */
	public int getAccion() {
		return accion;
	}

	/**
	 * Setea la accion
	 * @param accion
	 *            accion a setear
	 */
	public void setAccion(final int accion) {
		this.accion = accion;
	}

	private Juego wome;
	private MenuCarga menuCarga;

	/**
	 * Constructor del Cliente
	 */
	public Cliente() {

		ip = JOptionPane.showInputDialog("Ingrese IP del servidor: (default localhost)");
		if (ip == null) {
			ip = "localhost";
		}
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream("archivo.properties"));
			puerto = Integer.parseInt(propiedades.getProperty("puerto"));
			cliente = new Socket(ip, puerto);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					null, "Fallo al iniciar la aplicación."
							+ "Revise la conexión con el servidor.");
			System.exit(1);
		}
	}

	/**
	 * Crea el cliente con los parametros que ser reciben
	 * @param ip del servidor
	 * @param puerto del servidor
	 */
	public Cliente(final String ip, final int puerto) {
		try {
			cliente = new Socket(ip, puerto);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					null, "Fallo al iniciar la aplicación."
							+ "Revise la conexión con el servidor.");
			System.exit(1);
		}
	}

	@Override
	public void run() {
		synchronized (this) {
			try {
				ComandosCliente comand;
				// Creo el paquete que le voy a enviar al servidor
				paqueteUsuario = new PaqueteUsuario();
				MenuJugar menuJugar = null;
				while (!paqueteUsuario.isInicioSesion()) {

					// Muestro el menú principal
					if (menuJugar == null) {
						menuJugar = new MenuJugar(this);
						menuJugar.setVisible(true);

						// Creo los paquetes que le voy a enviar al servidor
						paqueteUsuario = new PaqueteUsuario();
						paquetePersonaje = new PaquetePersonaje();

						// Espero a que el usuario seleccione alguna accion
						wait();

						comand = (ComandosCliente) Paquete.
								getObjetoSet(Comando.NOMBREPAQUETE, getAccion());
						System.out.println("ANTESPASE");
						comand.setCadena(null);
						System.out.println("PASE");
						comand.setCliente(this);
						comand.ejecutar();

						// Le envio el paquete al servidor
						salida.writeObject(gson.toJson(paqueteUsuario));
					}
					// Recibo el paquete desde el servidor
					String cadenaLeida = (String) entrada.readObject();
					Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);

					comand = (ComandosCliente) paquete.getObjeto(Comando.NOMBREPAQUETE);
					comand.setCadena(cadenaLeida);
					comand.setCliente(this);
					comand.ejecutar();
				}

				// Creo un paquete con el comando mostrar mapas
				paquetePersonaje.setComando(Comando.MOSTRARMAPAS);

				// Abro el menu de eleccion del mapa
				MenuMapas menuElegirMapa = new MenuMapas(this);
				menuElegirMapa.setVisible(true);

				// Espero a que el usuario elija el mapa
				wait();

				// Si clickeo en la Cruz al Seleccionar mapas
				if (paquetePersonaje.getMapa() == 0) {
					paquetePersonaje.setComando(Comando.DESCONECTAR);
					salida.writeObject(gson.toJson(paquetePersonaje));
				} else {
					// Establezco el mapa en el paquete personaje
					paquetePersonaje.setIp(miIp);

					// Le envio el paquete con el mapa seleccionado
					salida.writeObject(gson.toJson(paquetePersonaje));

					// Instancio el juego y cargo los recursos
					wome = new Juego("World Of the Middle Earth", anchoVentanaWoME,
							altoVentanaWoME, this, paquetePersonaje);

					// Muestro el menu de carga
					menuCarga = new MenuCarga(this);
					menuCarga.setVisible(true);

					// Espero que se carguen todos los recursos
					wait();

					// Inicio el juego
					wome.start();

					// Finalizo el menu de carga
					menuCarga.dispose();
				}
			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(
						null, "Falló la conexión con el "
								+ "servidor durante el inicio de sesión.");
				System.exit(1);
			}
		}

	}

	/**
	 * Pide el cliente.
	 * @return Devuelve el cliente.
	 */
	public Socket getSocket() {
		return cliente;
	}

	/**
	 * Setea el cliente.
	 * @param clienteParam
	 *            cliente a setear.
	 */
	public void setSocket(final Socket clienteParam) {
		this.cliente = clienteParam;
	}

	/**
	 * Pide la ip.
	 * @return Devuelve la ip.
	 */
	public String getMiIp() {
		return miIp;
	}

	/**
	 * Setea la ip.
	 * @param miIp
	 *            ip a setear.
	 */
	public void setMiIp(final String miIp) {
		this.miIp = miIp;
	}

	/**
	 * Pide la entrada.
	 * @return Devuelve la entrada.
	 */
	public ObjectInputStream getEntrada() {
		return entrada;
	}

	/**
	 * Setea la entrada.
	 * @param entrada
	 *            entrada a setear.
	 */
	public void setEntrada(final ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	/**
	 * Pide la salida.
	 * @return Devuelve la salida.
	 */
	public ObjectOutputStream getSal() {
		return salida;
	}

	/**
	 * Setea la salida.
	 * @param salida
	 *            salida a setear.
	 */
	public void setSalida(final ObjectOutputStream salida) {
		this.salida = salida;
	}

	/**
	 * Pide el paquete usuario.
	 * @return Devuelve el paquete usuario.
	 */
	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}

	/**
	 * Pide el paquete personaje.
	 * @return Devuelve el paquete personaje.
	 */
	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	/**
	 * Pide el juego.
	 * @return Devuelve el juego.
	 */
	public Juego getJuego() {
		return wome;
	}

	/**
	 * Pide el menu de carga.
	 * @return Devuelve el menu de carga.
	 */
	public MenuCarga getMenuCarga() {
		return menuCarga;
	}

	/**
	 * Actualiza los items del Personaje.
	 * @param paqueteActualizado paquete actualizado.
	 */
	public void actualizarItems(final PaquetePersonaje paqueteActualizado) {
		if (paquetePersonaje.getCantItems() != 0
				&& paquetePersonaje.getCantItems() != paqueteActualizado.getCantItems()) {
			paquetePersonaje.anadirItem(paqueteActualizado.getItems().
					get(paqueteActualizado.getItems().size() - 1));
		}
	}

	/**
	 * Retorna la ip del Servidor.
	 * @return ip.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Setea el PaquetePersonaje que se recibe como parametro.
	 * @param pP PaquetePersonaje.
	 */
	public void actualizarPersonaje(final PaquetePersonaje pP) {
		paquetePersonaje = pP;
	}

	/**
	 * Retorna el Juego.
	 * @return Devuelve el juego.
	 */
	public Juego getWome() {
		return wome;
	}

	/**
	 * Setea el Juego.
	 * @param wome juego a setear.
	 */
	public void setWome(final Juego wome) {
		this.wome = wome;
	}

	/**
	 * Pide el puerto.
	 * @return devuelve el puerto.
	 */
	public int getPuerto() {
		return puerto;
	}

	/**
	 * Setea el paquete Usuario.
	 * @param paqueteUsuario a setear.
	 */
	public void setPaqueteUsuario(final PaqueteUsuario paqueteUsuario) {
		this.paqueteUsuario = paqueteUsuario;
	}

	/**
	 * Setea el paquete Personaje.
	 * @param paquetePersonaje a setear.
	 */
	public void setPaquetePersonaje(final PaquetePersonaje paquetePersonaje) {
		this.paquetePersonaje = paquetePersonaje;
	}

	/**
	 * Setea la ip.
	 * @param ip a setear.
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * Setea el menu de Cargar.
	 * @param menuCarga a setear.
	 */
	public void setMenuCarga(final MenuCarga menuCarga) {
		this.menuCarga = menuCarga;
	}

	/**
	 * Pide el menu comerciar.
	 * @return el menu comerciar.
	 */
	public MenuComerciar getM1() {
		return m1;
	}

	/**
	 * Setea el menu comerciar.
	 * @param m1 a setear.
	 */
	public void setM1(final MenuComerciar m1) {
		this.m1 = m1;
	}

	/**
	 * Pide el paquete comercio.
	 * @return el paquete Comercio.
	 */
	public PaqueteComerciar getPaqueteComercio() {
		return paqueteComercio;
	}

	/**
	 * Setea el paquete comercio.
	 * @param paqueteComercio a setear.
	 */
	public void setPaqueteComercio(final PaqueteComerciar paqueteComercio) {
		this.paqueteComercio = paqueteComercio;
	}

	/**
	 * Pide el paquete mensaje.
	 * @return el paquete mensaje.
	 */
	public PaqueteMensaje getPaqMsj() {
		return paqueteMensaje;
	}

	/**
	 * Setea el paquete mensaje.
	 * @param paqueteMensaje a setear.
	 */
	public void setPaqueteMensaje(final PaqueteMensaje paqueteMensaje) {
		this.paqueteMensaje = paqueteMensaje;
	}
}
