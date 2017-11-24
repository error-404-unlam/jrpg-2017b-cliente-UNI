package edu.unlam.wome.mensajeria;

import com.google.gson.Gson;

/**
 * Clase de Comandos
 *
 * @author lesanmartin
 *
 */
public abstract class Comando {
	// Nombre del paquete donde se encuentran las clases con las
	// responsabilidades
	public static final String NOMBREPAQUETE = "edu.unlam.wome.comandos";
	public static final String[] CLASSNAMES = {
			"Conexion", "CrearPersonaje",
			"Desconectar", "InicioSesion",
			"MostrarMapas", "Movimiento",
			"Registro", "Salir", "Batalla",
			"Atacar", "FinalizarBatalla",
			"ActualizarPersonaje", "ActualizarPersonajeLvl",
			"ActualizarInventario", "Comercio", "ActualizarComercio",
			"Trueque", "ActualizarTrueque", "Talk", "ActualizarModoJuego"};
	public static final String[] CLASSNAMESBIS = {
			"Conexion", "CrearPersonaje",
			"Desconectar", "InicioSesionSet",
			"MostrarMapas", "Movimiento",
			"RegistroSet", "SalirSet",
			"Batalla", "Atacar",
			"FinalizarBatalla",	"ActualizarPersonaje",
			"ActualizarPersonajeLvl", "ActualizarInventario",
			"Comercio", "ActualizarComercio",
			"Trueque", "ActualizarTrueque", "Talk", "ActualizarModoJuego"};

	public static final int CONEXION = 0;
	public static final int CREACIONPJ = 1;
	public static final int DESCONECTAR = 2;
	public static final int INICIOSESION = 3;
	public static final int MOSTRARMAPAS = 4;
	public static final int MOVIMIENTO = 5;
	public static final int REGISTRO = 6;
	public static final int SALIR = 7;
	public static final int BATALLA = 8;
	public static final int ATACAR = 9;
	public static final int FINALIZARBATALLA = 10;
	public static final int ACTUALIZARPERSONAJE = 11;
	public static final int ACTUALIZARPERSONAJELV = 12;
	public static final int ACTUALIZARINVENTARIO = 13;
	public static final int COMERCIO = 14;
	public static final int ACTUALIZARCOMERCIO = 15;
	public static final int TRUEQUE = 16;
	public static final int ACTUALIZARTRUEQUE = 17;
	public static final int TALK = 18;
	public static final int ACTUALIZAR_MODO_JUEGO = 19;
	
	private final Gson gson = new Gson();
	private String cadenaLeida;

	/**
	 * Setea la cadena leida
	 *
	 * @param cadLeida parametros cadena leida
	 */
	public void setCadena(final String cadLeida) {
		this.setCadenaLeida(cadLeida);
	}

	/**
	 * Metodo abstracto para ejecutar
	 */
	public abstract void ejecutar();

	/**
	 * @return gson
	 */
	public Gson getGson() {
		return gson;
	}

	/**
	 * @return cadenaLeida
	 */
	public String getCadenaLeida() {
		return cadenaLeida;
	}

	/**
	 * @param cadenaLeida cadenaLeida a setear
	 */
	public void setCadenaLeida(final String cadenaLeida) {
		this.cadenaLeida = cadenaLeida;
	}
}
