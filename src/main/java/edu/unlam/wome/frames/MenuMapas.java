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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase encargada de gestionar los el menu de mapas.
 * @author Miguel
 */
public class MenuMapas extends JFrame {
	private static int numberMap = 0;
	private JPanel contentPane;
	private static final int POS_X_PANTALLA = 100;
	private static final int POS_Y_PANTALLA = 100;
	private static final int ANCHO_PANTALLA = 300;
	private static final int ALTO_PANTALLA = 450;
	private static final int TOP_PANTALLA = 5;
	private static final int BOTTOM_PANTALLA = 5;
	private static final int LEFT_PANTALLA = 5;
	private static final int RIGHT_PANTALLA = 5;
	private static final int POS_X_LAYEREDPANEL = 0;
	private static final int POS_Y_LAYEREDPANEL = 0;
	private static final int ANCHO_LAYEREDPANEL = 444;
	private static final int ALTO_LAYEREDPANEL = 271;
	private static final int POS_X_LABEL_ARIS = 204;
	private static final int POS_Y_LABEL_ARIS = 129;
	private static final int ANCHO_LABEL_ARIS = 32;
	private static final int ALTO_LABEL_ARIS = 23;
	private static final int TAMANIO_FUENTE = 15;
	private static final int POS_X_LABEL_AUBENOR = 191;
	private static final int POS_Y_LABEL_AUBENOR = 72;
	private static final int ANCHO_LABEL_AUBENOR = 66;
	private static final int ALTO_LABEL_AUBENOR = 23;
	private static final int POS_X_LABEL_EODRIM = 198;
	private static final int POS_Y_LABEL_EODRIM = 192;
	private static final int ANCHO_LABEL_EODRIM = 53;
	private static final int ALTO_LABEL_EODRIM = 23;
	private static final int POS_X_BOTON_AUBENOR = 148;
	private static final int POS_Y_BOTON_AUBENOR = 72;
	private static final int ANCHO_BOTON_AUBENOR = 143;
	private static final int ALTO_BOTON_AUBENOR = 23;
	private static final int POS_X_BOTON_EODRIM = 148;
	private static final int POS_Y_BOTON_EODRIM = 192;
	private static final int ANCHO_BOTON_EODRIM = 143;
	private static final int ALTO_BOTON_EODRIM = 23;
	private static final int POS_X_BOTON_ARIS = 148;
	private static final int POS_Y_BOTON_ARIS = 130;
	private static final int ANCHO_BOTON_ARIS = 143;
	private static final int ALTO_BOTON_ARIS = 23;
	private static final int POS_X_LABEL_BACK = 0;
	private static final int POS_Y_LABEL_BACK = 0;
	private static final int ANCHO_LABEL_BACK = 444;
	private static final int ALTO_LABEL_BACK = 271;
	private final int numSetMapa = 3;


	/**
	 * Clase MenuMapas
	 *
	 * @param cliente cliente a usar el menu mapas
	 */
	public MenuMapas(final Cliente cliente) {
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
					synchronized (cliente) {
						cliente.getPaquetePersonaje().setMapa(1);
						setNumberMap(1);
						cliente.notify();
					}
					dispose();
				}
			}
		});

		setTitle("Elegir Mapa");
		setBounds(POS_X_PANTALLA, POS_Y_PANTALLA, ANCHO_PANTALLA, ALTO_PANTALLA);

		// En caso de cerrar
		addWindowListener(new WindowAdapter() {
			@Override
			/**
			 * Evento de cierre de ventana
			 */
			public void windowClosing(final WindowEvent e) {
				synchronized (cliente) {
					cliente.setAccion(Comando.SALIR);
					cliente.notify();
				}
				dispose();
			}
		});

		// Panel
		setTitle("WOME - Elegir Mapa");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(POS_X_PANTALLA, POS_Y_PANTALLA, ALTO_PANTALLA, ANCHO_PANTALLA);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(TOP_PANTALLA, LEFT_PANTALLA, BOTTOM_PANTALLA, RIGHT_PANTALLA));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(POS_X_LAYEREDPANEL, POS_Y_LAYEREDPANEL, ANCHO_LAYEREDPANEL, ALTO_LAYEREDPANEL);
		contentPane.add(layeredPane);

		// Mapa Aris
		JLabel lblAris = new JLabel("Aris");
		lblAris.setBounds(POS_X_LABEL_ARIS, POS_Y_LABEL_ARIS, ANCHO_LABEL_ARIS, ALTO_LABEL_ARIS);
		layeredPane.add(lblAris, new Integer(2));
		lblAris.setForeground(Color.WHITE);
		lblAris.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));

		// Mapa Aubenor
		JLabel lblAubenor = new JLabel("Aubenor");
		lblAubenor.setBounds(POS_X_LABEL_AUBENOR, POS_Y_LABEL_AUBENOR, ANCHO_LABEL_AUBENOR, ALTO_LABEL_AUBENOR);
		layeredPane.add(lblAubenor, new Integer(2));
		lblAubenor.setForeground(Color.WHITE);
		lblAubenor.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));

		// Mapa Eodrim
		JLabel lblEodrim = new JLabel("Eodrim");
		lblEodrim.setBounds(POS_X_LABEL_EODRIM, POS_Y_LABEL_EODRIM, ANCHO_LABEL_EODRIM, ALTO_LABEL_EODRIM);
		layeredPane.add(lblEodrim, new Integer(2));
		lblEodrim.setForeground(Color.WHITE);
		lblEodrim.setFont(new Font("Tahoma", Font.PLAIN, TAMANIO_FUENTE));

		JButton btnAubenor = new JButton("");
		btnAubenor.setBounds(POS_X_BOTON_AUBENOR, POS_Y_BOTON_AUBENOR, ANCHO_BOTON_AUBENOR, ALTO_BOTON_AUBENOR);
		layeredPane.add(btnAubenor, new Integer(1));
		btnAubenor.setFocusable(false);
		btnAubenor.setIcon(new ImageIcon(MenuMapas.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));

		JButton btnEodrim = new JButton("");
		btnEodrim.setBounds(POS_X_BOTON_EODRIM, POS_Y_BOTON_EODRIM, ANCHO_BOTON_EODRIM, ALTO_BOTON_EODRIM);
		layeredPane.add(btnEodrim, new Integer(1));
		btnEodrim.setFocusable(false);
		btnEodrim.setEnabled(false);
		btnEodrim.setIcon(new ImageIcon(MenuMapas.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnEodrim.addActionListener(new ActionListener() {
			@Override
			/**
			 * Accion de performance
			 */
			public void actionPerformed(final ActionEvent e) {
				synchronized (cliente) {
					cliente.getPaquetePersonaje().setMapa(numSetMapa);
					cliente.notify();
				}
				dispose();
			}
		});

		btnEodrim.setEnabled(false);

		JButton btnAris = new JButton("");
		btnAris.setBounds(POS_X_BOTON_ARIS, POS_Y_BOTON_ARIS, ANCHO_BOTON_ARIS, ALTO_BOTON_ARIS);
		layeredPane.add(btnAris, new Integer(1));
		btnAris.setFocusable(false);
		btnAris.setIcon(new ImageIcon(MenuMapas.class
				.getResource("/edu/unlam/wome/frames/BotonMenu.png")));
		btnAris.addActionListener(new ActionListener() {
			@Override
			/**
			 * Accion de performance
			 */
			public void actionPerformed(final ActionEvent e) {
				synchronized (cliente) {
					cliente.getPaquetePersonaje().setMapa(2);
					setNumberMap(2);
					cliente.notify();
				}
				dispose();
			}
		});

		btnAris.setEnabled(true);

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(POS_X_LABEL_BACK, POS_Y_LABEL_BACK, ANCHO_LABEL_BACK, ALTO_LABEL_BACK);
		layeredPane.add(lblBackground, new Integer(0));
		lblBackground.setIcon(new ImageIcon(MenuMapas.class
				.getResource("/edu/unlam/wome/frames/menuBackground.jpg")));
		btnAubenor.addActionListener(new ActionListener() {
			@Override
			/**
			 * Accion de performance
			 */
			public void actionPerformed(final ActionEvent e) {
				synchronized (cliente) {
					cliente.getPaquetePersonaje().setMapa(1);
					setNumberMap(1);
					cliente.notify();
				}
				dispose();
			}
		});
	}

	/**
	 * @return numberMap
	 */
	public static int getNumberMap() {
		return numberMap;
	}

	/**
	 * @param numberMap a setear
	 */
	public static void setNumberMap(final int numberMap) {
		MenuMapas.numberMap = numberMap;
	}
}
