package edu.unlam.wome.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase encargada de la gestion del menu
 * Inicio de Sesion
 * @author Miguel
 */
public class MenuInicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	//BACKGROUND
	private static final int POS_X_BACKGROUND = 0;
	private static final int POS_Y_BACKGROUND = 0;
	private static final int ANCHO_BACKGROUND = 444;
	private static final int ALTO_BACKGROUND = 271;
	//LAYERED PANE
	private static final int POS_X_LAYERED_PANE = 0;
	private static final int POS_Y_LAYERED_PANE = 0;
	private static final int ANCHO_LAYERED_PANE = 444;
	private static final int ALTO_LAYERED_PANE = 271;
	//VENTANA
	private static final int POS_X_VENTANA = 100;
	private static final int POS_Y_VENTANA = 100;
	private static final int ANCHO_VENTANA = 450;
	private static final int ALTO_VENTANA = 300;
	//EMPTY BORDER
	private static final int TOP_CONTENTPANE = 5;
	private static final int BOTTOM_CONTENTPANE = 5;
	private static final int LEFT_CONTENTPANE = 5;
	private static final int RIGHT_CONTENTPANE = 5;
	//USUARIO
	private static final int POS_X_USER_FIELD = 198;
	private static final int POS_Y_USER_FIELD = 69;
	private static final int ANCHO_USER_FIELD = 118;
	private static final int ALTO_USER_FIELD = 20;
	private static final int COLUMNS_USER_FIELD = 10;
	//PASSWORD
	private static final int POS_X_PASSWORD_FIELD = 198;
	private static final int POS_Y_PASSWORD_FIELD = 119;
	private static final int ANCHO_PASSWORD_FIELD = 118;
	private static final int ALTO_PASSWORD_FIELD = 20;
	private static final int TAM_FUENTE_PASSWORD = 11;
	//BOTON CONECTAR
	private static final int POS_X_BOTON_CONECTAR = 141;
	private static final int POS_Y_BOTON_CONECTAR = 182;
	private static final int ANCHO_BOTON_CONECTAR = 153;
	private static final int ALTO_BOTON_CONECTAR = 23;
	//LABEL PASSWORD
	private static final int POS_X_LABEL_PASSWORD = 111;
	private static final int POS_Y_LABEL_PASSWORD = 118;
	private static final int ANCHO_LABEL_PASSWORD = 68;
	private static final int ALTO_LABEL_PASSWORD = 21;
	private static final int TAM_FUENTE_LABEL_PASSWORD = 15;
	//LABEL USUARIO
	private static final int POS_X_LABEL_USUARIO = 111;
	private static final int POS_Y_LABEL_USUARIO = 66;
	private static final int ANCHO_LABEL_USUARIO = 55;
	private static final int ALTO_LABEL_USUARIO = 23;
	private static final int TAM_FUENTE_LABEL_USUARIO = 15;
	//LABEL INGRESAR
	private static final int POS_X_LABEL_INGRESAR = 193;
	private static final int POS_Y_LABEL_INGRESAR = 183;
	private static final int ANCHO_LABEL_INGRESAR = 68;
	private static final int ALTO_LABEL_INGRESAR = 23;
	private static final int TAM_FUENTE_LABEL_INGRESAR = 15;
	/**
	 * Constructor Menu Inicio Sesion
	 * @param cliente cliente actual
	 */
	public MenuInicioSesion(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/java/edu/unlam/wome/frames/IconoWome.png"));
		setCursor(
				Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(
								MenuJugar.class.getResource("/cursor.png")).
						getImage(), new Point(0, 0), "custom cursor"));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				synchronized (cliente) {
					cliente.setAccion(Comando.SALIR);
					cliente.notify();
				}
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			}
		});

		setTitle("WOME - Iniciar Sesion");
		setBounds(
				POS_X_VENTANA,
				POS_Y_VENTANA,
				ANCHO_VENTANA,
				ALTO_VENTANA);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(
				new EmptyBorder(
						TOP_CONTENTPANE,
						LEFT_CONTENTPANE,
						BOTTOM_CONTENTPANE,
						RIGHT_CONTENTPANE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(
				POS_X_LAYERED_PANE,
				POS_Y_LAYERED_PANE,
				ANCHO_LAYERED_PANE,
				ALTO_LAYERED_PANE);
		contentPane.add(layeredPane);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(
				POS_X_LABEL_PASSWORD,
				POS_Y_LABEL_PASSWORD,
				ANCHO_LABEL_PASSWORD,
				ALTO_LABEL_PASSWORD);
		layeredPane.add(labelPassword);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, TAM_FUENTE_LABEL_PASSWORD));
		labelPassword.setForeground(Color.WHITE);

		JLabel labelUsuario = new JLabel("Usuario");
		labelUsuario.setBounds(
				POS_X_LABEL_USUARIO,
				POS_Y_LABEL_USUARIO,
				ANCHO_LABEL_USUARIO,
				ALTO_LABEL_USUARIO);
		layeredPane.add(labelUsuario, new Integer(2));
		labelUsuario.setForeground(Color.WHITE);
		labelUsuario.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						TAM_FUENTE_LABEL_USUARIO));

		JLabel labelIngresar = new JLabel("Ingresar");
		labelIngresar.setBounds(
				POS_X_LABEL_INGRESAR,
				POS_Y_LABEL_INGRESAR,
				ANCHO_LABEL_INGRESAR,
				ALTO_LABEL_INGRESAR);
		layeredPane.add(labelIngresar, new Integer(2));
		labelIngresar.setForeground(Color.WHITE);
		labelIngresar.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						TAM_FUENTE_LABEL_INGRESAR));

		userField = new JTextField();
		userField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				logIn(cliente);
			}
		});
		userField.setBounds(
				POS_X_USER_FIELD,
				POS_Y_USER_FIELD,
				ANCHO_USER_FIELD,
				ALTO_USER_FIELD);
		layeredPane.add(userField, new Integer(1));
		userField.setColumns(COLUMNS_USER_FIELD);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				logIn(cliente);
			}
		});
		passwordField.setBounds(
				POS_X_PASSWORD_FIELD,
				POS_Y_PASSWORD_FIELD,
				ANCHO_PASSWORD_FIELD,
				ALTO_PASSWORD_FIELD);
		layeredPane.add(passwordField, new Integer(1));
		passwordField.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						TAM_FUENTE_PASSWORD));
		JButton btnConectar = new JButton("");
		btnConectar.setBounds(
				POS_X_BOTON_CONECTAR,
				POS_Y_BOTON_CONECTAR,
				ANCHO_BOTON_CONECTAR,
				ALTO_BOTON_CONECTAR);
		layeredPane.add(btnConectar, new Integer(1));
		btnConectar.setFocusable(false);
		btnConectar.setIcon(new ImageIcon(MenuInicioSesion.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnConectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				logIn(cliente);

			}
		});

		JLabel labelBackground = new JLabel("");
		labelBackground.setBounds(
				POS_X_BACKGROUND,
				POS_Y_BACKGROUND,
				ANCHO_BACKGROUND,
				ALTO_BACKGROUND);
		labelBackground.setIcon(new ImageIcon(MenuInicioSesion.class
				.getResource("/edu/unlam/wome/frames/menuBackground.jpg")));
		layeredPane.add(labelBackground, new Integer(0));
	}

	/**
	 * Cargador de Usuario y Constraseña
	 * @param cliente cliente actual
	 */
	private void logIn(final Cliente cliente) {
		synchronized (cliente) {
			cliente.setAccion(Comando.INICIOSESION);
			cliente.getPaqueteUsuario().setUsername(userField.getText());
			cliente.getPaqueteUsuario().setPassword(String.valueOf(passwordField.getPassword()));
			cliente.notify();
			dispose();
		}
	}
}
