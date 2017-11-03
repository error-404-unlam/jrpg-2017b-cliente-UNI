package edu.unlam.wome.cliente.frames;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.unlam.wome.cliente.cliente.Cliente;
import edu.unlam.wome.cliente.mensajeria.Comando;

/**
 * Clase encargada del menu de carga.
 * @author Miguel
 */
public class MenuCarga extends JFrame {

	private JPanel contentPane;
	private JLabel barraCargando;
	private final int bordeVacio = 5;
	private final int posXLblBarraCargando = 52;
	private final int posYLblBarraCargando = 160;
	private final int anchoLblBarraCargando = 0;
	private final int altoLblBarraCargando = 27;
	private final int posXLblCarga = 47;
	private final int posYLblCarga = 154;
	private final int anchoLblCarga = 355;
	private final int altoLblCarga = 40;
	private final int posXLblBackground = 0;
	private final int posYLblBackground = 0;
	private final int anchoLblBackground = 444;
	private final int altoLblBackground = 271;
	private final int posXLblLogo = 109;
	private final int posYLblLogo = 39;
	private final int anchoLblLogo = 216;
	private final int altoLblLogo = 90;
	private final int posXVentana = 100;
	private final int posYLblVentana = 100;
	private final int anchoLblVentana = 450;
	private final int altoLblVentana = 300;
	private final int altoBarraCarga = 27;
	/**
	 * Constructor del menu carga
	 * @param cliente del juego
	 */
	public MenuCarga(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(
				Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(
								MenuJugar.class.getResource("/cursor.png")).
						getImage(), new Point(0, 0), "custom cursor"));

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// En caso de cerrar la ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				synchronized (cliente) {
					cliente.setAccion(Comando.SALIR);
					cliente.notify();
				}
				dispose();
			}
		});
		// Propiedades de la ventana
		setTitle("WOME - World Of the Middle Earth");
		setBounds(
				posXVentana,
				posYLblVentana,
				anchoLblVentana,
				altoLblVentana);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(
				bordeVacio,
				bordeVacio,
				bordeVacio,
				bordeVacio));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		barraCargando = new JLabel("");
		barraCargando.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/Barra.png")));
		barraCargando.setBounds(
				posXLblBarraCargando,
				posYLblBarraCargando,
				anchoLblBarraCargando,
				altoLblBarraCargando);
		contentPane.add(barraCargando);
		JLabel lblBarraCarga = new JLabel("");
		lblBarraCarga.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/BarraCarga.png")));
		lblBarraCarga.setBounds(posXLblCarga, posYLblCarga, anchoLblCarga, altoLblCarga);
		contentPane.add(lblBarraCarga);
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/WOME.png")));
		lblLogo.setBounds(
				posXLblLogo,
				posYLblLogo,
				anchoLblLogo,
				altoLblLogo);
		contentPane.add(lblLogo);
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(
				posXLblBackground,
				posYLblBackground,
				anchoLblBackground,
				altoLblBackground);
		contentPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/menuBackground.jpg")));
	}
	/**
	 * Establece el ancho y alto de la barra de carga
	 * @param ancho de la barra de carga
	 */
	public void setBarraCargando(final int ancho) {
		barraCargando.setSize(ancho, altoBarraCarga);
	}
}
