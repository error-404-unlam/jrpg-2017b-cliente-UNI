package edu.unlam.wome.cliente.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.google.gson.Gson;

import edu.unlam.wome.cliente.juego.Juego;
import edu.unlam.wome.cliente.juego.Pantalla;
import edu.unlam.wome.cliente.mensajeria.Comando;

/**
 * Objeto MiChat, cuyo proposito es Gestionar el chat del WOME.
 * @author Miguel
 */
public class MiChat extends JFrame {

	/**
	 * Espacio en blanco del borde de la ventana Chat.
	 */
	private final int borde = 5;
	/**
	 * Posicion X de la ventana Chat.
	 */
	private final int posXChat = 100;
	/**
	 * Posicion Y de la ventana Chat.
	 */
	private final int posYChat = 100;
	/**
	 * Ancho de la ventana Chat.
	 */
	private final int anchoChat = 450;
	/**
	 * Alto de la ventana Chat.
	 */
	private final int altoChat = 300;

	/**
	 * Posicion X del cuadro de texto del Chat.
	 */
	private final int posXScrollPane = 10;
	/**
	 * Posicion Y del cuadro de texto del Chat.
	 */
	private final int posYScrollPane = 11;
	/**
	 * Ancho del cuadro de texto del Chat.
	 */
	private final int anchoChatScrollPane = 414;
	/**
	 * Alto del cuadro de texto del Chat.
	 */
	private final int altoChatScrollPane = 201;

	/**
	 * Posicion X del boton Enviar.
	 */
	private final int posXEnviar = 334;
	/**
	 * Posicion Y del boton Enviar.
	 */
	private final int posYEnviar = 225;
	/**
	 * Ancho del boton Enviar.
	 */
	private final int anchoEnviar = 81;
	/**
	 * Alto del boton Enviar.
	 */
	private final int altoEnviar = 23;

	/**
	 * Posicion X del Texto.
	 */
	private final int posXTexto = 10;
	/**
	 * Posicion Y del Texto.
	 */
	private final int posYTexto = 223;
	/**
	 * Ancho del Texto.
	 */
	private final int anchoTexto = 314;
	/**
	 * Alto del Texto.
	 */
	private final int altoTexto = 27;
	/**
	 * Cantidad de Columnas.
	 */
	private final int columnas = 10;

	/**
	 * Posicion X del Background.
	 */
	private final int posXBackground = -20;
	/**
	 * Posicion Y del Background.
	 */
	private final int posYBackground = 0;
	/**
	 * Ancho del Background
	 */
	private final int anchoBackground = 480;
	/**
	 * Alto del BackGround
	 */
	private final int altoBackground = 283;
	/**
	 * JPanel que contendrá los elementos del chat.
	 */
	private JPanel contentPane;

	/**
	 * Campo de texto en donde se escribirá el mensaje a enviar.
	 */
	private JTextField texto;

	/**
	 * Campo en donde estara la conversacion.
	 */
	private JTextArea chat;

	/**
	 * Atributo del tipo Juego, que se utilizara para
	 * guardar una referencia del juego actual.
	 */
	private Juego juego;

	/**
	 * Objeto utilizado para conversión entre objetos Java y JSON.
	 */
	private final Gson gson = new Gson();

	/**
	 * Coloca una imagen como background del chat.
	 */
	private final JLabel background;

	/**
	 * Se crea DefaultCaret para controlar la caret del chat.
	 */
	private DefaultCaret caret;

	/**
	 * Create the frame.
	 * @param gameP
	 *            parametro donde se recibe el juego actual.
	 */
	public MiChat(final Juego gameP) {
		System.out.println("Mi Chat");
		this.juego = gameP;
		setTitle("Mi Chat");
		this.background = new JLabel(new ImageIcon("recursos//background.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(posXChat, posYChat, anchoChat, altoChat);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(borde, borde, borde, borde));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(posXScrollPane, posYScrollPane, anchoChatScrollPane, altoChatScrollPane);
		contentPane.add(scrollPane);

		chat = new JTextArea();
		chat.setEditable(false);
		scrollPane.setViewportView(chat);
		caret = (DefaultCaret) chat.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		texto = new JTextField();
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(final WindowEvent e) {
				texto.requestFocus();
			}

			@Override
			public void windowClosing(final WindowEvent e) {
				if (getTitle() == "Sala") {
					if (Pantalla.getVentContac() != null) {
						VentanaContactos.getBotonMc().setEnabled(true);
					}
				}
				gameP.getChatsActivos().remove(getTitle());
			}
		});

		// SI TOCO ENTER
		texto.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (!texto.getText().equals("")) {
					chat.append("Me: " + texto.getText() + "\n");

					gameP.getCli().getPaqMsj().setUserEmisor(gameP.getPersonaje().getNombre());
					gameP.getCli().getPaqMsj().setUserReceptor(getTitle());
					gameP.getCli().getPaqMsj().setMensaje(texto.getText());

					// MANDO EL COMANDO PARA QUE ENVIE EL MSJ
					gameP.getCli().getPaqMsj().setComando(Comando.TALK);
					// El user receptor en espacio indica que es para todos
					if (getTitle() == "Sala") {
						gameP.getCli().getPaqMsj().setUserReceptor(null);
					}

					try {
						gameP.getCli().getSal().
						writeObject(gson.toJson(gameP.getCli().getPaqMsj()));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
					}
					texto.setText("");
				}
				texto.requestFocus();
			}
		});

		// SI TOCO ENVIAR
		JButton enviar = new JButton("ENVIAR");
		enviar.setIcon(new ImageIcon("recursos//enviarButton.png"));
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (!texto.getText().equals("")) {
					chat.append("Me: " + texto.getText() + "\n");

					gameP.getCli().getPaqMsj().setUserEmisor(gameP.getPersonaje().getNombre());
					gameP.getCli().getPaqMsj().setUserReceptor(getTitle());
					gameP.getCli().getPaqMsj().setMensaje(texto.getText());

					// MANDO EL COMANDO PARA QUE ENVIE EL MSJ
					gameP.getCli().getPaqMsj().setComando(Comando.TALK);
					// El user receptor en espacio indica que es para todos
					if (getTitle() == "Sala") {
						gameP.getCli().getPaqMsj().setUserReceptor(null);
					}

					try {
						gameP.getCli().getSal().
						writeObject(gson.toJson(gameP.getCli().getPaqMsj()));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error al enviar mensaje");

					}
					texto.setText("");
				}
				texto.requestFocus();
			}
		});
		enviar.setBounds(posXEnviar, posYEnviar, anchoEnviar, altoEnviar);
		contentPane.add(enviar);

		texto.setBounds(posXTexto, posYTexto, anchoTexto, altoTexto);
		contentPane.add(texto);
		texto.setColumns(columnas);
		background.setBounds(posXBackground, posYBackground, anchoBackground, altoBackground);
		contentPane.add(background);
	}

	/**
	 * Devuelve el JTextArea chat.
	 * @return chat
	 */
	public JTextArea getChat() {
		return chat;
	}

	/**
	 * Devuelve el JTextField texto.
	 * @return texto.
	 */
	public JTextField getTexto() {
		return texto;
	}
}
