package edu.unlam.wome.inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.unlam.wome.dominio.main.Item;
import edu.unlam.wome.mensajeria.PaquetePersonaje;
import edu.unlam.wome.cliente.recursos.Recursos;

/**
 * Clase Celda
 *
 * @author lesanmartin
 *
 */
public class Celda extends JPanel {

	private BufferedImage item;
	private PaquetePersonaje paquetePersonaje;
	private JLabel label;
	private Item it;
	private static final int ANCHO_IMAGEN = 49;
	private static final int ALTURA_IMAGEN = 49;
	private static final int ANCHO_DIMENSION = 60;
	private static final int ALTURA_DIMENSION = 49;

	/**
	 * Constructor parametrizado de la clase
	 *
	 * @param item item
	 * @param paquetePersonaje paquete personaje
	 * @throws IOException Excepcion de entrada/salida
	 */
	public Celda(final Item item, final PaquetePersonaje paquetePersonaje) throws IOException {
		this.item = item.getFoto();
		it = item;
		this.paquetePersonaje = paquetePersonaje;
		label = new JLabel(
				new ImageIcon(
						this.item.getScaledInstance(
								ANCHO_IMAGEN,
								ALTURA_IMAGEN,
								Image.SCALE_DEFAULT)));
		actionListenersYLabel(item);
	}

	/**
	 * Constructor por defecto de la clase
	 */
	public Celda() {
		label = new JLabel(
				new ImageIcon(
						Recursos.getNoItem().
						getScaledInstance(
								ANCHO_IMAGEN,
								ALTURA_IMAGEN,
								Image.SCALE_DEFAULT)));
		add(label);
	}

	/**
	 * Acciones de item
	 * @param itemP Item de la celda
	 */
	private void actionListenersYLabel(final Item itemP) {
		StringBuilder s = new StringBuilder();

		s.append("<html>" + itemP.getNombre() + "<br>");

		if (itemP.getBonusSalud() != 0) {
			s.append("+" + itemP.getBonusSalud() + " Salud " + "<br>");
		}
		if (itemP.getBonusEnergia() != 0) {
			s.append("+" + itemP.getBonusEnergia() + " Energia " + "<br>");
		}
		if (itemP.getBonusFuerza() != 0) {
			s.append("+" + itemP.getBonusFuerza() + " Fuerza " + "<br>");
		}
		if (itemP.getBonusDestreza() != 0) {
			s.append("+" + itemP.getBonusDestreza() + " Destreza " + "<br>");
		}
		if (itemP.getBonusInteligencia() != 0) {
			s.append("+" + itemP.getBonusInteligencia() + " Inteligencia");
		}
		s.append("</html>");
		label.setToolTipText(s.toString());

		label.addMouseListener(mouseListener);

		addMouseListener(mouseListener);

		add(label);
		this.validate();
		this.repaint();

	}

	/**
	 * Reseteo de label
	 */
	protected void resetLabel() {
		label.setIcon(
				new ImageIcon(
						Recursos.getNoItem().
						getScaledInstance(
								ANCHO_IMAGEN,
								ALTURA_IMAGEN,
								Image.SCALE_DEFAULT)));
		label.setToolTipText(null);
		paquetePersonaje.removerItem(it);
		label.removeMouseListener(mouseListener);
		removeMouseListener(mouseListener);
	}

	/**
	 * Retorna el tamaño de la dimension
	 * @return Dimension dimension del objeto
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(ANCHO_DIMENSION, ALTURA_DIMENSION);
	}

	/**
	 * Retorna el label
	 *
	 * @return JLabel JLabel
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * Listener del mouse
	 */
	private MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(final MouseEvent e) {
			Object[] options = {"Tirar", "Cancelar"};
			if (e.getClickCount() == 2) {
				int answer = JOptionPane.showOptionDialog(
						getParent(),
						"¿Qué desea hacer?",
						"Item: " + it.getNombre(),
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						options,
						options[1]);
				// Tirar
				if (answer == 0) {
					paquetePersonaje.sacarBonus(
							it.getBonusSalud(), it.getBonusEnergia(),
							it.getBonusFuerza(), it.getBonusDestreza(),
							it.getBonusInteligencia());
					resetLabel();
				}
			}
		}
	};
}
