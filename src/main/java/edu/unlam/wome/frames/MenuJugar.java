package edu.unlam.wome.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase Menu Jugar
 *
 * @author lesanmartin
 *
 */
public class MenuJugar extends JFrame {

	private JPanel contentPane;
	private static final int POS_X_PANTALLA = 100;
	private static final int POS_Y_PANTALLA = 100;
	private static final int ALTO_PANTALLA = 300;
	private static final int ANCHO_PANTALLA = 450;
	private static final int POS_X_CONTPANEL = 5;
	private static final int POS_Y_CONTPANEL = 5;
	private static final int ALTO_CONTPANEL = 5;
	private static final int ANCHO_CONTPANEL = 5;
	private static final int POS_X_LAYEREDPANEL = 0;
	private static final int POS_Y_LAYEREDPANEL = 0;
	private static final int ALTO_LAYEREDPANEL = 271;
	private static final int ANCHO_LAYEREDPANEL = 444;
	private static final int POS_X_LABREG = 181;
	private static final int POS_Y_LABREG = 162;
	private static final int ALTO_LABREG = 23;
	private static final int ANCHO_LABREG = 82;
	private static final int TAMANIO_FUENTE = 15;
	private static final int POS_X_LABINICSES = 175;
	private static final int POS_Y_LABINICSES = 91;
	private static final int ALTO_LABINICSES = 23;
	private static final int ANCHO_LABINICSES = 91;
	private static final int POS_X_BOTONREG = 121;
	private static final int POS_Y_BOTONREG = 162;
	private static final int ALTO_BOTONREG = 23;
	private static final int ANCHO_BOTONREG = 191;
	private static final int POS_X_BOTONINICSES = 121;
	private static final int POS_Y_BOTONINICSES = 92;
	private static final int ALTO_BOTONINICSES = 23;
	private static final int ANCHO_BOTONINICSES = 191;
	private static final int POS_X_LAB_BACK = 0;
	private static final int POS_Y_LAB_BACK = 0;
	private static final int ALTO_LAB_BACK = 271;
	private static final int ANCHO_LAB_BACK = 444;
	/**
	 * Constructor de la clase
	 *
	 * @param cliente que utiliza el menu
	 */
	public MenuJugar(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/java/edu/unlam/wome/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Evento de teclado
			 */
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					MenuInicioSesion menuInicioSesion = new MenuInicioSesion(cliente);
					menuInicioSesion.setVisible(true);
					dispose();
				}
			}
		});

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// En caso de cerrar la ventana
		addWindowListener(new WindowAdapter() {
			@Override
			/**
			 * Evento de la ventana
			 */
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
		setBounds(POS_X_PANTALLA, POS_Y_PANTALLA, ANCHO_PANTALLA, ALTO_PANTALLA);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(
				new EmptyBorder(
						POS_X_CONTPANEL,
						POS_Y_CONTPANEL,
						ANCHO_CONTPANEL,
						ALTO_CONTPANEL));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(POS_X_LAYEREDPANEL, POS_Y_LAYEREDPANEL, ANCHO_LAYEREDPANEL, ALTO_LAYEREDPANEL);
		contentPane.add(layeredPane);

		// Boton Registrarse
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setBounds(POS_X_LABREG, POS_Y_LABREG, ANCHO_LABREG, ALTO_LABREG);
		layeredPane.add(lblRegistrarse, new Integer(2));
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setEnabled(true);
		lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));
		lblRegistrarse.setBackground(Color.WHITE);

		// Boton Iniciar sesion
		JLabel lblIniciarSesion = new JLabel("Iniciar Sesion");
		lblIniciarSesion.setBounds(POS_X_LABINICSES, POS_Y_LABINICSES, ANCHO_LABINICSES, ALTO_LABINICSES);
		layeredPane.add(lblIniciarSesion, new Integer(2));
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));

		JButton btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setBounds(POS_X_BOTONREG, POS_Y_BOTONREG, ANCHO_BOTONREG, ALTO_BOTONREG);
		layeredPane.add(btnRegistrar, new Integer(1));
		btnRegistrar.setFocusable(false);
		btnRegistrar.setIcon(new ImageIcon(MenuJugar.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			@Override
			/**
			 * Accion de evento menu registro
			 */
			public void actionPerformed(final ActionEvent e) {
				MenuRegistro menuRegistro = new MenuRegistro(cliente);
				menuRegistro.setVisible(true);
				dispose();
			}
		});

		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBounds(
				POS_X_BOTONINICSES,
				POS_Y_BOTONINICSES,
				ANCHO_BOTONINICSES,
				ALTO_BOTONINICSES);
		layeredPane.add(btnIniciarSesion, new Integer(1));
		btnIniciarSesion.setFocusable(false);
		btnIniciarSesion.setIcon(new ImageIcon(MenuJugar.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			/**
			 * Acccion de evento de menu inicio de sesion
			 */
			public void actionPerformed(final ActionEvent e) {
				MenuInicioSesion menuInicioSesion = new MenuInicioSesion(cliente);
				menuInicioSesion.setVisible(true);
				dispose();
			}
		});

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(POS_X_LAB_BACK, POS_Y_LAB_BACK, ANCHO_LAB_BACK, ALTO_LAB_BACK);
		lblBackground.setIcon(new ImageIcon(MenuJugar.class
				.getResource("/edu/unlam/wome/frames/menuBackground.jpg")));
		layeredPane.add(lblBackground, new Integer(0));
	}
}
