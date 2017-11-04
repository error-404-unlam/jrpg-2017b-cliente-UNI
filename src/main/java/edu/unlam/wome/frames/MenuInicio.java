package edu.unlam.wome.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.unlam.wome.cliente.Cliente;

/**
 * Clase MenuInicio
 *
 * @author lesanmartin
 *
 */
public class MenuInicio extends JFrame {

	private JPanel contentPane;
	private static final int POS_X_PANTALLA = 100;
	private static final int POS_Y_PANTALLA = 100;
	private static final int ANCHO_PANTALLA = 450;
	private static final int ALTO_PANTALLA = 300;
	private static final int TOP_CONTENTPANE = 5;
	private static final int BOTTOM_CONTENTPANE = 5;
	private static final int LEFT_CONTENTPANE = 5;
	private static final int RIGHT_CONTENTPANE = 5;
	private static final int TAMANIO_FUENTE = 15;
	private static final int POS_X_LABELOGO = 109;
	private static final int POS_Y_LABELOGO = 39;
	private static final int ANCHO_LABELOGO = 216;
	private static final int ALTO_LABELOGO = 90;
	private static final int POS_X_LAYEREDPANE = 0;
	private static final int POS_Y_LAYEREDPANE = 0;
	private static final int ANCHO_LAYEREDPANE = 444;
	private static final int ALTO_LAYEREDPANE = 271;
	private static final int POS_X_LABELREGISTRARSE = 205;
	private static final int POS_Y_LABELREGISTRARSE = 162;
	private static final int ANCHO_LABELREGISTRARSE = 82;
	private static final int ALTO_LABELREGISTRARSE = 23;
	private static final int POS_X_LABELINICIARSESION = 210;
	private static final int POS_Y_LABELINICIARSESION = 202;
	private static final int ANCHO_LABELINICIARSESION = 91;
	private static final int ALTO_LABELINICIARSESION = 23;
	private static final int POS_X_BOTONREGISTRAR = 127;
	private static final int POS_Y_BOTONREGISTRAR = 162;
	private static final int ANCHO_BOTONREGISTRAR = 191;
	private static final int ALTO_BOTONREGISTRAR = 23;
	private static final int POS_X_BOTONINICARSESION = 127;
	private static final int POS_Y_BOTONINICIARSESION = 202;
	private static final int ANCHO_BOTONINICIARSESION = 191;
	private static final int ALTO_BOTONINICIARSESION = 23;
	private static final int POS_X_BACKGROUND = 0;
	private static final int POS_Y_BACKGROUND = 0;
	private static final int ANCHO_BACKGROUND = 444;
	private static final int ALTO_BACKGROUND = 271;
	/**
	 * Constructor de la clase
	 */
	public MenuInicio() {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/java/edu/unlam/wome/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Evento de teclaso
			 */
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Cliente cliente = new Cliente();
					cliente.start();
					dispose();
				}
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Propiedades de la ventana
		setTitle("WOME - World Of the Middle Earth");
		setBounds(POS_X_PANTALLA, POS_Y_PANTALLA, ANCHO_PANTALLA, ALTO_PANTALLA);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane
				.setBorder(
						new EmptyBorder(
								TOP_CONTENTPANE,
								BOTTOM_CONTENTPANE,
								LEFT_CONTENTPANE,
								RIGHT_CONTENTPANE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MenuCarga.class
				.getResource("/edu/unlam/wome/frames/WOME.png")));
		lblLogo.setBounds(POS_X_LABELOGO, POS_Y_LABELOGO, ANCHO_LABELOGO, ALTO_LABELOGO);
		contentPane.add(lblLogo);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(POS_X_LAYEREDPANE, POS_Y_LAYEREDPANE, ANCHO_LAYEREDPANE, ALTO_LAYEREDPANE);
		contentPane.add(layeredPane);

		// Boton Jugar
		JLabel lblRegistrarse = new JLabel("Jugar");
		lblRegistrarse.setBounds(POS_X_LABELREGISTRARSE, POS_Y_LABELREGISTRARSE, ANCHO_LABELREGISTRARSE,
				ALTO_LABELREGISTRARSE);
		layeredPane.add(lblRegistrarse, new Integer(2));
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setEnabled(true);
		lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));
		lblRegistrarse.setBackground(Color.WHITE);

		// Boton Salir
		JLabel lblIniciarSesion = new JLabel("Salir");
		lblIniciarSesion.setBounds(POS_X_LABELINICIARSESION, POS_Y_LABELINICIARSESION, ANCHO_LABELINICIARSESION,
				ALTO_LABELINICIARSESION);
		layeredPane.add(lblIniciarSesion, new Integer(2));
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));

		JButton btnRegistrar = new JButton("Jugar");
		btnRegistrar.setBounds(
				POS_X_BOTONREGISTRAR,
				POS_Y_BOTONREGISTRAR,
				ANCHO_BOTONREGISTRAR,
				ALTO_BOTONREGISTRAR);
		layeredPane.add(btnRegistrar, new Integer(1));
		btnRegistrar.setFocusable(false);
		btnRegistrar.setIcon(new ImageIcon(MenuJugar.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.start();
				dispose();
			}
		});

		JButton btnIniciarSesion = new JButton("Salir");
		btnIniciarSesion.setBounds(POS_X_BOTONINICARSESION, POS_Y_BOTONINICIARSESION, ANCHO_BOTONINICIARSESION,
				ALTO_BOTONINICIARSESION);
		layeredPane.add(btnIniciarSesion, new Integer(1));
		btnIniciarSesion.setFocusable(false);
		btnIniciarSesion.setIcon(new ImageIcon(MenuJugar.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent e) {
				dispose();
			}
		});

		JLabel lblBackground = new JLabel("");

		lblBackground.setBounds(POS_X_BACKGROUND, POS_Y_BACKGROUND, ANCHO_BACKGROUND, ALTO_BACKGROUND);
		lblBackground.setIcon(new ImageIcon(MenuJugar.class
				.getResource("/edu/unlam/wome/frames/menuBackground.jpg")));
		layeredPane.add(lblBackground, new Integer(0));
	}

	/**
	 * Main
	 *
	 * @param args parametro args
	 */
	public static void main(final String[] args) {
		new MenuInicio().setVisible(true);
	}

}
