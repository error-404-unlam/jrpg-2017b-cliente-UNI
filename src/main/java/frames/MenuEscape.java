package frames;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import estados.Estado;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Clase MenuEscape
 *
 * @author lesanmartin
 *
 */
public class MenuEscape extends JFrame {

	private JPanel contentPane;
	private final Gson gson = new Gson();
	private static final int POS_X_PANTALLA = 100;
	private static final int POS_Y_PANTALLA = 100;
	private static final int ANCHO_PANTALLA = 180;
	private static final int ALTO_PANTALLA = 270;
	private static final int TOP_CONTENTPANE = 5;
	private static final int BOTTOM_CONTENTPANE = 5;
	private static final int LEFT_CONTENTPANE = 5;
	private static final int RIGHT_CONTENTPANE = 5;
	private static final int POS_X_VERSTATS = 29;
	private static final int POS_Y_VERSTATS = 13;
	private static final int ANCHO_VERSTATS = 125;
	private static final int ALTO_VERSTATS = 25;
	private static final int POS_X_ASIGNARSKILLS = 29;
	private static final int POS_Y_ASIGNARSKILLS = 66;
	private static final int ANCHO_ASIGNARSKILLS = 125;
	private static final int ALTO_ASIGNARSKILLS = 25;
	private static final int POS_X_INVENTARIO = 29;
	private static final int POS_Y_INVENTARIO = 121;
	private static final int ANCHO_INVENTARIO = 125;
	private static final int ALTO_INVENTARIO = 25;
	private static final int POS_X_DESCONECTARSE = 29;
	private static final int POS_Y_DESCONECTARSE = 175;
	private static final int ANCHO_DESCONECTARSE = 125;
	private static final int ALTO_DESCONECTARSE = 25;
	private static final int POS_X_VOLVER = 29;
	private static final int POS_Y_VOLVER = 227;
	private static final int ANCHO_VOLVER = 125;
	private static final int ALTO_VOLVER = 25;
	private static final int ANCHO_IMAGEN = 200;
	private static final int ALTO_IMAGEN = 350;
	private static final int POS_X_BACKGROUND = 0;
	private static final int POS_Y_BACKGROUND = 0;
	private static final int ANCHO_BACKGROUND = 186;
	private static final int ALTO_BACKGROUND = 273;
	/**
	 * Constructor de la clase
	 *
	 * @param cliente
	 *            parametro cliente
	 */
	public MenuEscape(final Cliente cliente) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setBounds(POS_X_PANTALLA, POS_Y_PANTALLA, ANCHO_PANTALLA, ALTO_PANTALLA);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane
				.setBorder(new EmptyBorder(TOP_CONTENTPANE, BOTTOM_CONTENTPANE, LEFT_CONTENTPANE, RIGHT_CONTENTPANE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton verStats = new JButton("Estadísticas");
		verStats.setIcon(new ImageIcon("recursos//stats.png"));
		verStats.setToolTipText("Presiona S para ver estadísticas");
		verStats.setBounds(POS_X_VERSTATS, POS_Y_VERSTATS, ANCHO_VERSTATS, ALTO_VERSTATS);
		verStats.addActionListener(new ActionListener() {
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent e) {
				dispose();
				Pantalla.setMenuEscp(null);
				if (Pantalla.getMenuStats() == null) {
					Pantalla.setMenuStats(new MenuStats(cliente));
					Pantalla.getMenuStats().setVisible(true);
				}
			}
		});
		contentPane.add(verStats);

		JButton asignarSkills = new JButton("Asignar Skills");
		asignarSkills.setIcon(new ImageIcon("recursos//asignar skills.png"));
		asignarSkills.setToolTipText("Presiona A para asignar skills");
		asignarSkills.setBounds(POS_X_ASIGNARSKILLS, POS_Y_ASIGNARSKILLS, ANCHO_ASIGNARSKILLS, ALTO_ASIGNARSKILLS);
		asignarSkills.addActionListener(new ActionListener() {
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent e) {
				dispose();
				Pantalla.setMenuEscp(null);
				Pantalla.setMenuAsignar(null);
				if (Pantalla.getMenuAsignar() == null) {
					Pantalla.setMenuAsignar(new MenuAsignarSkills(cliente));
				}
				Pantalla.getMenuAsignar().setVisible(true);
			}
		});
		contentPane.add(asignarSkills);

		JButton inventario = new JButton("Inventario");
		inventario.setIcon(new ImageIcon("recursos//inventario.png"));
		inventario.setToolTipText("Presiona I para abrir inventario");
		inventario.setBounds(POS_X_INVENTARIO, POS_Y_INVENTARIO, ANCHO_INVENTARIO, ALTO_INVENTARIO);
		inventario.addActionListener(new ActionListener() {
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent e) {
				dispose();
				Pantalla.setMenuEscp(null);
				if (Estado.getEstado().esEstadoDeJuego()) {
					if (Pantalla.getMenuInventario() == null) {
						Pantalla.setMenuInventario(new MenuInventario(cliente));
						Pantalla.getMenuInventario().setVisible(true);
					}
				}
			}
		});
		contentPane.add(inventario);

		JButton desconectarse = new JButton("Desconectarse");
		desconectarse.setBounds(POS_X_DESCONECTARSE, POS_Y_DESCONECTARSE, ANCHO_DESCONECTARSE, ALTO_DESCONECTARSE);
		desconectarse.setIcon(new ImageIcon("recursos//desconectarse.png"));
		desconectarse.addActionListener(new ActionListener() {
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent e) {
				try {
					Paquete p = new Paquete();
					p.setComando(Comando.DESCONECTAR);
					p.setIp(cliente.getMiIp());
					cliente.getSal().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSal().close();
					cliente.getSocket().close();
					System.exit(0);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al desconectar");

				}
			}
		});
		contentPane.add(desconectarse);

		JButton volver = new JButton("Volver");
		volver.setIcon(new ImageIcon("recursos//volver.png"));
		volver.setBounds(POS_X_VOLVER, POS_Y_VOLVER, ANCHO_VOLVER, ALTO_VOLVER);
		volver.addActionListener(new ActionListener() {
			/**
			 * Evento de boton
			 */
			public void actionPerformed(final ActionEvent arg0) {
				Pantalla.setMenuEscp(null);
				dispose();
			}
		});
		contentPane.add(volver);

		BufferedImage imagenFondo = null;
		try {
			imagenFondo = ImageIO.read(new File("recursos//fondo2.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

		}
		JLabel background = new JLabel(new ImageIcon(imagenFondo.getScaledInstance(ANCHO_IMAGEN, ALTO_IMAGEN, Image.SCALE_DEFAULT)));
		background.setBounds(POS_X_BACKGROUND, POS_Y_BACKGROUND, ANCHO_BACKGROUND, ALTO_BACKGROUND);
		contentPane.add(background);
	}
}
