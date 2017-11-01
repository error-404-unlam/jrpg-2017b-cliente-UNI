package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.google.gson.Gson;

import chat.VentanaContactos;
import cliente.Cliente;
import estados.Estado;
import frames.MenuAsignarSkills;
import frames.MenuEscape;
import frames.MenuInventario;
import frames.MenuJugar;
import frames.MenuStats;
import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Clase Pantalla
 *
 * @author lesanmartin
 *
 */
public class Pantalla {

	private JFrame pantalla;
	private Canvas canvas;

	// Menus
	private static MenuInventario menuInventario;
	private	static MenuAsignarSkills menuAsignar;
	private static MenuStats menuStats;
	private static MenuEscape menuEscp;
	private static VentanaContactos ventContac;

	private final Gson gson = new Gson();

	/**
	 * Constructor de la clase
	 *
	 * @param nombre	nombre del juego
	 * @param ancho		ancho de la ventana
	 * @param alto		alto de la ventana
	 * @param cliente	cliente
	 */
	public Pantalla(final String nombre, final int ancho, final int alto, final Cliente cliente) {
		pantalla = new JFrame(nombre);
		pantalla.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		pantalla.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));

		pantalla.setSize(ancho, alto);
		pantalla.setResizable(false);
		pantalla.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		pantalla.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent evt) {
				try {
					Paquete p = new Paquete();
					p.setComando(Comando.DESCONECTAR);
					p.setIp(cliente.getMiIp());
					cliente.getSal().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSal().close();
					cliente.getSocket().close();
					System.exit(0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicación.");
					System.exit(1);
				}
			}
		});
		pantalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				if (Estado.getEstado().esEstadoDeJuego()) {
					if (e.getKeyCode() == KeyEvent.VK_I) {
						if (menuInventario == null) {
							menuInventario = new MenuInventario(cliente);
							menuInventario.setVisible(true);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_A) {
						if (menuAsignar == null) {
							menuAsignar = new MenuAsignarSkills(cliente);
							menuAsignar.setVisible(true);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_S) {
						if (menuStats == null) {
							menuStats = new MenuStats(cliente);
							menuStats.setVisible(true);
						}
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						if (menuEscp == null) {
							menuEscp = new MenuEscape(cliente);
							menuEscp.setVisible(true);
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_C) {
					// if(Estado.getEstado().esEstadoDeJuego()) {
					if (ventContac == null) {
						ventContac = new VentanaContactos(cliente.getJuego());
						ventContac.setVisible(true);
					}
					// }
				}
			}
		});

		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(false);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ancho, alto));
		canvas.setMaximumSize(new Dimension(ancho, alto));
		canvas.setMinimumSize(new Dimension(ancho, alto));
		canvas.setFocusable(false);
		pantalla.add(canvas);
		pantalla.pack();
	}

	/**
	 * @return el menu Inventario
	 */
	public static MenuInventario getMenuInventario() {
		return menuInventario;
	}

	/**
	 * @param menuInv que se va a setear
	 */
	public static void setMenuInventario(final MenuInventario menuInv) {
		menuInventario = menuInv;
	}

	/**
	 * @return el menu para asignar skills
	 */
	public static MenuAsignarSkills getMenuAsignar() {
		return menuAsignar;
	}

	/**
	 * @param menuAsig es el menu en el cual se asignan los skills
	 */
	public static void setMenuAsignar(final MenuAsignarSkills menuAsig) {
		menuAsignar = menuAsig;
	}

	/**
	 * @return el menu con la informacion de los stats
	 */
	public static MenuStats getMenuStats() {
		return menuStats;
	}

	/**
	 * @param menStat	menu con las estadisticas
	 */
	public static void setMenuStats(final MenuStats menStat) {
		menuStats = menStat;
	}

	/**
	 * @return	el menu escape
	 */
	public static MenuEscape getMenuEscp() {
		return menuEscp;
	}

	/**
	 * @param menuEsc	es el menu desde el cual se puede acceder a los otros menus
	 * 					y salir del juego
	 */
	public static void setMenuEscp(final MenuEscape menuEsc) {
		menuEscp = menuEsc;
	}

	/**
	 * @return la ventana de chat
	 */
	public static VentanaContactos getVentContac() {
		return ventContac;
	}

	/**
	 * @param ventCont es el chat
	 */
	public static void setVentContac(final VentanaContactos ventCont) {
		ventContac = ventCont;
	}

	/**
	 * Retorna el canvas
	 *
	 * @return canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Retorna el Frame
	 *
	 * @return pantalla
	 */
	public JFrame getFrame() {
		return pantalla;
	}

	/**
	 * Setea la visibilidad de la pantalla
	 */
	public void mostrar() {
		pantalla.setVisible(true);
	}

	/**
	 * Centra el string
	 *
	 * @param g 	es el que se va a encargar de graficar el string
	 * @param r		son los limites en los que se va a graficar
	 * @param s		es lo que se va a escribir
	 */
	public static void centerString(final Graphics g, final Rectangle r, final String s) {
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Rectangle2D r2D = g.getFont().getStringBounds(s, frc);
		int rWidth = (int) Math.round(r2D.getWidth());
		int rHeight = (int) Math.round(r2D.getHeight());
		int rX = (int) Math.round(r2D.getX());
		int rY = (int) Math.round(r2D.getY());
		int a = (r.width / 2) - (rWidth / 2) - rX;
		int b = (r.height / 2) - (rHeight / 2) - rY;
		g.drawString(s, r.x + a, r.y + b);
	}
}
