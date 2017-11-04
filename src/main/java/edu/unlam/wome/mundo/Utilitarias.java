package edu.unlam.wome.mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Clase utilitaria de mundo
 *
 * @author lesanmartin
 */
public final class Utilitarias {
	/**
	 * Constructor de la clase
	 */
	private Utilitarias() {
	}

	/**
	 * Carga archivo a String
	 * @param path ruta del archivo
	 * @return builder.toString
	 */
	public static String archivoAString(final String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String linea;

			while ((linea = br.readLine()) != null) {
				builder.append(linea + System.lineSeparator());
			}

			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al intentar cargar el mapa " + path);
		}

		return builder.toString();
	}

	/**
	 * Parsea un string
	 * @param numero a parsear
	 * @return int
	 */
	public static int parseInt(final String numero) {
		try {
			return Integer.parseInt(numero);
		} catch (NumberFormatException e) {

			return 0;
		}
	}

}
