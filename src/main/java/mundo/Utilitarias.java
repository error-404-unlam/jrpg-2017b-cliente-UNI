package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Clase utilitaria de mundo
 * @author lesanmartin
 */
public final class Utilitarias {
	private Utilitarias() {
		
	}
	/**
	 * Carga el mapa
	 * @param path
	 * @return
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
	 * Convierte un numero de string a int
	 * @param numero
	 * @return
	 */
	public static int parseInt(final String numero) {
		try {
			return Integer.parseInt(numero);
		} catch (NumberFormatException e) {

			return 0;
		}
	}

}
