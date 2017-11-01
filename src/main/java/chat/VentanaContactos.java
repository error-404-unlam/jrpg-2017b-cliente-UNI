package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import juego.Juego;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;

/**
 * Crea la ventana Contactos del Chat.
 * @author Miguel
 */
public class VentanaContactos extends JFrame {
	/**
	 * JPanel que contendrá los elementos de la ventana Contactos.
	 */
	private JPanel contentPane;
	private DefaultListModel<String> modelo = new DefaultListModel<String>();
	/**
	 * Jlist que contiene los elementos de la ventana.
	 */
	private static JList<String> list = new JList<String>();
	/**
	 * Boton Multichat.
	 */
	private static JButton botonMc;
	/**
	 * Fondo de la ventana.
	 */
	private JLabel background;
	/**
	 * Bordes en blanco de la ventana.
	 */
	private final int bordeVacio = 5;

	/**
	 * Posicion X de la ventana Contactos.
	 */
	private final int posXContactos = 100;
	/**
	 * Posicion Y de la ventana Contactos.
	 */
	private final int posYContactos = 100;
	/**
	 * Ancho de la ventana Contactos.
	 */
	private final int anchoContactos = 327;
	/**
	 * Alto de la ventana Contactos.
	 */
	private final int altoContactos = 273;
	/**
	 * Posicion X del Scroll Pane de Contactos.
	 */
	private final int posXScrollPane = 10;
	/**
	 * Posicion Y del Scroll Pane de Contactos.
	 */
	private final int posYScrollPane = 11;
	/**
	 * Ancho del Scroll Pane de Contactos.
	 */
	private final int anchoContactoScrollPane = 299;
	/**
	 * Alto del Scroll Pane de Contactos.
	 */
	private final int altoContactoScrollPane = 188;

	/**
	 * Posicion X del boton MultiChat.
	 */
	private final int posXMultiChat = 119;
	/**
	 * Posicion Y del boton MultiChat.
	 */
	private final int posYMultiChat = 208;
	/**
	 * Ancho del boton MultiChat.
	 */
	private final int anchoMultiChat = 89;
	/**
	 * Alto del boton MultiChat.
	 */
	private final int altoMultiChat = 23;

	/**
	 * Posicion X del Background.
	 */
	private final int posXBackground = -16;
	/**
	 * Posicion Y del Background.
	 */
	private final int posYBackground = 0;
	/**
	 * Ancho del Background
	 */
	private final int anchoBackground = 352;
	/**
	 * Alto del BackGround
	 */
	private final int altoBackground = 254;

	/**
	 * Constructor de la clase VentanaContactos que recibe el Juego Actual.
	 * @param juegoParam
	 *            Juego Actual
	 */
	public VentanaContactos(final Juego juegoParam) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(posXContactos, posYContactos, anchoContactos, altoContactos);
		setLocationRelativeTo(null);
		setTitle("Usuarios");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(bordeVacio, bordeVacio, bordeVacio, bordeVacio));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(posXScrollPane, posYScrollPane, anchoContactoScrollPane, altoContactoScrollPane);
		contentPane.add(scrollPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent arg0) {
				Pantalla.setVentContac(null);
				dispose();
			}
		});

		botonMc = new JButton("Multichat");
		botonMc.setIcon(new ImageIcon("recursos//multichatButton.png"));
		botonMc.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (modelo.size() != 0) {
					if (!juegoParam.getChatsActivos().containsKey("Sala")) {
						MiChat chat = new MiChat(juegoParam);
						juegoParam.getChatsActivos().put("Sala", chat);
						chat.setTitle("Sala");
						chat.setVisible(true);
						botonMc.setEnabled(false);
					}
				}
			}
		});
		botonMc.setBounds(posXMultiChat, posYMultiChat, anchoMultiChat, altoMultiChat);
		contentPane.add(botonMc);

		// Cargo la lista de contactos
		actualizarLista(juegoParam);
		// Pregunto si la ventana sala esta abierta y cancelo el boton
		// multichat
		if (juegoParam.getChatsActivos().containsKey("Sala")) {
			botonMc.setEnabled(false);
		} else {
			botonMc.setEnabled(true);
		}

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					if (list.getSelectedValue() != null) {
						if (!juegoParam.getChatsActivos().
								containsKey(list.getSelectedValue())) {
							if (juegoParam.getCli() != null) {
								MiChat chat = new MiChat(juegoParam);
								juegoParam.getChatsActivos().
									put(list.getSelectedValue(), chat);
								chat.setTitle(list.getSelectedValue());
								chat.setVisible(true);
							}
						}
					}
				}
			}
		});

		list.setModel(modelo);
		scrollPane.setViewportView(list);

		background = new JLabel(new ImageIcon("recursos//background.jpg"));
		background.setBounds(posXBackground, posYBackground, anchoBackground, altoBackground);
		contentPane.add(background);
	}

	/**
	 * Actualiza la lista De Contactos del Juego.
	 * @param juego Juego a Actualizar la lista.
	 */
	private void actualizarLista(final Juego juego) {
		if (juego.getCli() != null) {
			synchronized (juego.getCli()) {
				modelo.removeAllElements();
				if (juego.getPersonajesConectados() != null) {
					for (Map.Entry<Integer, PaquetePersonaje> personaje
							:juego.getPersonajesConectados().entrySet()) {
						modelo.addElement(personaje.getValue().getNombre());
					}
					modelo.removeElement(juego.getPersonaje().getNombre());
					list.setModel(modelo);
				}
			}
		}
	}

	/**
	 * Devuelve la lista de Contactos.
	 * @return list
	 */
	public static JList<String> getList() {
		return list;
	}

	/**
	 * Devuelve el boton multiChat.
	 * @return botonMc
	 */
	public static JButton getBotonMc() {
		return botonMc;
	}
}
