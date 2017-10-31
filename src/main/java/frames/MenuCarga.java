package frames;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

public class MenuCarga extends JFrame {

	private JPanel contentPane;
	private JLabel barraCargando;
	private static final int POS_X_PANTALLA = 100;
	private static final int POS_Y_PANTALLA = 100;
	private static final int ALTO_PANTALLA = 450;
	private static final int ANCHO_PANTALLA = 300;
	private static final int TOP_CONTENTPANE = 5;
	private static final int BOTTOM_CONTENTPANE = 5;
	private static final int LEFT_CONTENTPANE = 5;
	private static final int RIGHT_CONTENTPANE = 5;
	private static final int POS_X_BARRA = 52;
	private static final int POS_Y_BARRA = 0;
	private static final int ALTO_BARRA = 60;
	private static final int ANCHO_BARRA = 127;
	private static final int POS_X_LAB_BARRA = 47;
	private static final int POS_Y_LAB_BARRA = 154;
	private static final int ALTO_LAB_BARRA = 355;
	private static final int ANCHO_LAB_BARRA = 40;
	private static final int POS_X_LAB_LOGO = 109;
	private static final int POS_Y_LAB_LOGO = 39;
	private static final int ALTO_LAB_LOGO = 216;
	private static final int ANCHO_LAB_LOGO = 90;
	private static final int POS_X_LAB_BACK = 0;
	private static final int POS_Y_LAB_BACK = 0;
	private static final int ALTO_LAB_BACK = 444;
	private static final int ANCHO_LAB_BACK = 271;
	private static final int ALTO_BARRA_CARGANDO = 27;

	
	public MenuCarga(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// En caso de cerrar la ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized (cliente) {
					cliente.setAccion(Comando.SALIR);
					cliente.notify();
				}
				dispose();
			}
		});

		// Propiedades de la ventana
		setTitle("WOME - World Of the Middle Earth");
		setBounds(POS_X_PANTALLA, POS_Y_PANTALLA, ANCHO_PANTALLA, ALTO_PANTALLA);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane
				.setBorder(new EmptyBorder(TOP_CONTENTPANE, LEFT_CONTENTPANE, BOTTOM_CONTENTPANE, RIGHT_CONTENTPANE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		barraCargando = new JLabel("");
		barraCargando.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/Barra.png")));
		barraCargando.setBounds(POS_X_BARRA, POS_Y_BARRA, ANCHO_BARRA, ALTO_BARRA);
		contentPane.add(barraCargando);

		JLabel lblBarraCarga = new JLabel("");
		lblBarraCarga.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/BarraCarga.png")));
		lblBarraCarga.setBounds(POS_X_LAB_BARRA, POS_Y_LAB_BARRA, ANCHO_LAB_BARRA, ALTO_LAB_BARRA);
		contentPane.add(lblBarraCarga);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/WOME.png")));
		lblLogo.setBounds(POS_X_LAB_LOGO, POS_Y_LAB_LOGO, ANCHO_LAB_LOGO, ALTO_LAB_LOGO);
		contentPane.add(lblLogo);

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(POS_X_LAB_BACK, POS_Y_LAB_BACK, ANCHO_LAB_BACK, ALTO_LAB_BACK);
		contentPane.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/menuBackground.jpg")));
	}

	/**
	 * Setea el tamaño de la barra cargando
	 *
	 * @param ancho
	 */
	public void setBarraCargando(final int ancho) {
		barraCargando.setSize(ancho, ALTO_BARRA_CARGANDO);
	}
}
