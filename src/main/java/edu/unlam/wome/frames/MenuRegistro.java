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
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase encargada del registro del usuario.
 * @author Miguel
 */
public class MenuRegistro extends JFrame {

	private JTextField txtUsuario;
	private JPasswordField pwPassword;

	//USUARIO
	private static final int POS_X_USER = 199;
	private static final int POS_Y_USER = 69;
	private static final int ANCHO_USER = 118;
	private static final int ALTO_USER = 20;
	private static final int COLUMNS_USER = 10;
	//PASSWORD
	private static final int POS_X_PASSWORD = 199;
	private static final int POS_Y_PASSWORD = 120;
	private static final int ANCHO_PASSWORD = 118;
	private static final int ALTO_PASSWORD = 20;
	//BACKGROUND
	private static final int POS_X_BACKGROUND = 0;
	private static final int POS_Y_BACKGROUND = 0;
	private static final int ANCHO_BACKGROUND = 444;
	private static final int ALTO_BACKGROUND = 271;
	//LABEL REGISTRARSE
	private static final int POS_X_LABEL_REGISTRARSE = 186;
	private static final int POS_Y_LABEL_REGISTRARSE = 182;
	private static final int ANCHO_LABEL_REGISTRARSE = 82;
	private static final int ALTO_LABEL_REGISTRARSE = 23;
	private static final int TAM_FUENTE_LABEL_REGISTRARSE = 15;
	//BOTON REGISTRARSE
	private static final int POS_X_BOTON_REGISTRARSE = 143;
	private static final int POS_Y_BOTON_REGISTRARSE = 182;
	private static final int ANCHO_BOTON_REGISTRARSE = 153;
	private static final int ALTO_BOTON_REGISTRARSE = 23;
	//VENTANA
	private static final int POS_X_VENTANA = 100;
	private static final int POS_Y_VENTANA = 100;
	private static final int ANCHO_VENTANA = 450;
	private static final int ALTO_VENTANA = 300;
	//LAYERED PANE
	private static final int POS_X_LAYERED_PANE = 0;
	private static final int POS_Y_LAYERED_PANE = 0;
	private static final int ANCHO_LAYERED_PANE = 444;
	private static final int ALTO_LAYERED_PANE = 271;
	//LABEL USUARIO
	private static final int POS_X_LABEL_USUARIO = 113;
	private static final int POS_Y_LABEL_USUARIO = 70;
	private static final int ANCHO_LABEL_USUARIO = 57;
	private static final int ALTO_LABEL_USUARIO = 19;
	private static final int TAM_FUENTE_LABEL_USUARIO = 15;
	//LABEL PASSWORD
	private static final int POS_X_LABEL_PASSWORD = 113;
	private static final int POS_Y_LABEL_PASSWORD = 121;
	private static final int ANCHO_LABEL_PASSWORD = 65;
	private static final int ALTO_LABEL_PASSWORD = 17;
	private static final int TAM_FUENTE_LABEL_PASSWORD = 15;
	/**
	 * Constructor Menu Registro
	 * @param cliente Cliente actual
	 */
	public MenuRegistro(final Cliente cliente) {
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
				dispose();
			}
		});

		setTitle("WOME - Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(
				POS_X_VENTANA,
				POS_Y_VENTANA,
				ANCHO_VENTANA,
				ALTO_VENTANA);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(
				POS_X_LAYERED_PANE,
				POS_Y_LAYERED_PANE,
				ANCHO_LAYERED_PANE,
				ALTO_LAYERED_PANE);
		getContentPane().add(layeredPane);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(
				POS_X_LABEL_USUARIO,
				POS_Y_LABEL_USUARIO,
				ANCHO_LABEL_USUARIO,
				ALTO_LABEL_USUARIO);
		layeredPane.add(lblUsuario, new Integer(1));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font(
				"Tahoma",
				Font.PLAIN,
				TAM_FUENTE_LABEL_USUARIO));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(
				POS_X_LABEL_PASSWORD,
				POS_Y_LABEL_PASSWORD,
				ANCHO_LABEL_PASSWORD,
				ALTO_LABEL_PASSWORD);
		layeredPane.add(lblPassword, new Integer(1));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font(
				"Tahoma",
				Font.PLAIN,
				TAM_FUENTE_LABEL_PASSWORD));

		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setBounds(
				POS_X_LABEL_REGISTRARSE,
				POS_Y_LABEL_REGISTRARSE,
				ANCHO_LABEL_REGISTRARSE,
				ALTO_LABEL_REGISTRARSE);
		layeredPane.add(lblRegistrarse, new Integer(2));
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setFont(new Font(
				"Tahoma", Font.PLAIN,
				TAM_FUENTE_LABEL_REGISTRARSE));

		JButton btnRegistrarse = new JButton("");
		btnRegistrarse.setBounds(
				POS_X_BOTON_REGISTRARSE,
				POS_Y_BOTON_REGISTRARSE,
				ANCHO_BOTON_REGISTRARSE,
				ALTO_BOTON_REGISTRARSE);
		layeredPane.add(btnRegistrarse, new Integer(1));
		btnRegistrarse.setFocusable(false);
		btnRegistrarse.setIcon(new ImageIcon(MenuRegistro.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));

		pwPassword = new JPasswordField();
		pwPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				logIn(cliente);
				dispose();
			}
		});
		pwPassword.setBounds(
				POS_X_PASSWORD,
				POS_Y_PASSWORD,
				ANCHO_PASSWORD,
				ALTO_PASSWORD);
		layeredPane.add(pwPassword, new Integer(1));

		txtUsuario = new JTextField();
		txtUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				logIn(cliente);
				dispose();
			}
		});
		txtUsuario.setBounds(
				POS_X_USER,
				POS_Y_USER,
				ANCHO_USER,
				ALTO_USER);
		layeredPane.add(txtUsuario, new Integer(1));
		txtUsuario.setColumns(COLUMNS_USER);

		JLabel labelBackground = new JLabel("");
		labelBackground.setBounds(
				POS_X_BACKGROUND,
				POS_Y_BACKGROUND,
				ANCHO_BACKGROUND,
				ALTO_BACKGROUND);
		layeredPane.add(labelBackground, new Integer(0));
		labelBackground.setIcon(new ImageIcon(MenuRegistro.class
				.getResource("/edu/unlam/wome/frames/menuBackground.jpg")));
		btnRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				logIn(cliente);
				dispose();
			}
		});
	}

	/**
	 * Devuelve el campo del usuario
	 * @return JTextField
	 */
	public JTextField gettxtUsuario() {
		return txtUsuario;
	}

	/**
	 * Setea el usuario
	 * @param usuario campo de texto del usuario
	 */
	public void settxtUsuario(final JTextField usuario) {
		this.txtUsuario = usuario;
	}

	/**
	 * Devuelve la contraseña
	 * @return JPasswordField
	 */
	public JPasswordField getPasswordField() {
		return pwPassword;
	}

	/**
	 * Setea la contraseña del usuario
	 * @param password campo de contraseña
	 */
	public void setPasswordField(final JPasswordField password) {
		this.pwPassword = password;
	}

	/**
	 * Setea el usuario y la contraseña
	 * @param cliente cliente actual
	 */
	private void logIn(final Cliente cliente) {
		synchronized (cliente) {
			cliente.getPaqueteUsuario().setUsername(txtUsuario.getText());
			cliente.getPaqueteUsuario().setPassword(String.valueOf(pwPassword.getPassword()));
			cliente.setAccion(Comando.REGISTRO);
			cliente.notify();
		}
	}
}
