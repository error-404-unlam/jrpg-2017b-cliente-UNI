package edu.unlam.wome.frames;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.inventario.Inventario;
import edu.unlam.wome.juego.Pantalla;
import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase Menu de inventario
 * @author lesanmartin
 */
public class MenuInventario extends JFrame {
	private JButton cancelar = new JButton("Exit");
	private static final int POS_X_VENTANA = 600;
	private static final int POS_Y_VENTANA = 600;
	private static final int ANCHO_VENTANA = 600;
	private static final int ALTURA_VENTANA = 600;
	private static final int LOC_X = 900;
	private static final int LOC_Y = 140;


	/**
	 * Constructor de la clase
	 *
	 * @param cliente Cliente que utiliza el inventario
	 */
	public MenuInventario(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/java/edu/unlam/wome/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					Gson gson = new Gson();
					cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARINVENTARIO);
					cliente.getSal().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar inventario");
				}
				Pantalla.setMenuInventario(null);
				dispose();
			}
		});
		this.setTitle("Inventario");
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			this.setLayout(new BorderLayout());
			this.add(new Inventario(cliente.getPaquetePersonaje()));
			this.add(cancelar, BorderLayout.AFTER_LAST_LINE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al iniciar el inventario");

		}
		this.setBounds(POS_X_VENTANA, POS_Y_VENTANA, ANCHO_VENTANA, ALTURA_VENTANA);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setLocation(LOC_X, LOC_Y);
		this.setResizable(false);
		this.setVisible(true);
	}
}
