package edu.unlam.wome.cliente.frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import edu.unlam.wome.cliente.cliente.Cliente;
import edu.unlam.wome.cliente.juego.Pantalla;
import edu.unlam.wome.cliente.mensajeria.PaquetePersonaje;

/**
 * Clase encargada de la gestion de las estadisticas
 * @author Miguel
 */
public class MenuStats extends JFrame {

	private JPanel contentPane;
	private PaquetePersonaje paquetePersonaje;
	private final double mod = 1.5;

	//BOTON VOLVER
	private static final int POS_X_BOTON_VOLVER = 128;
	private static final int POS_Y_BOTON_VOLVER = 245;
	private static final int ANCHO_BOTON_VOLVER = 97;
	private static final int ALTO_BOTON_VOLVER = 25;
	//BACKGROUND
	private static final int SCALE_ANCHO_BACKGROUND = 400;
	private static final int SCALE_ALTO_BACKGROUND = 350;
	private static final int POS_X_BACKGROUND = -12;
	private static final int POS_Y_BACKGROUND = -11;
	private static final int ANCHO_BACKGROUND = 363;
	private static final int ALTO_BACKGROUND = 312;
	//LABEL EXPERIENCIA PJ
	private static final int POS_X_LABEL_EXP_PJ = 251;
	private static final int POS_Y_LABEL_EXP_PJ = 42;
	private static final int ANCHO_LABEL_EXP_PJ = 77;
	private static final int ALTO_LABEL_EXP_PJ = 16;
	//LABEL ENERGIA PJ
	private static final int POS_X_LABEL_ENG_PJ = 251;
	private static final int POS_Y_LABEL_ENG_PJ = 100;
	private static final int ANCHO_LABEL_ENG_PJ = 77;
	private static final int ALTO_LABEL_ENG_PJ = 16;
	//LABEL ATAQUE PJ
	private static final int POS_X_LABEL_ATQ_PJ = 251;
	private static final int POS_Y_LABEL_ATQ_PJ = 129;
	private static final int ANCHO_LABEL_ATQ_PJ = 77;
	private static final int ALTO_LABEL_ATQ_PJ = 16;
	//LABEL DEFENSA PJ
	private static final int POS_X_LABEL_DEF_PJ = 251;
	private static final int POS_Y_LABEL_DEF_PJ = 158;
	private static final int ANCHO_LABEL_DEF_PJ = 77;
	private static final int ALTO_LABEL_DEF_PJ = 16;
	//LABEL MAGIA PJ
	private static final int POS_X_LABEL_MAGIC_PJ = 251;
	private static final int POS_Y_LABEL_MAGIC_PJ = 187;
	private static final int ANCHO_LABEL_MAGIC_PJ = 77;
	private static final int ALTO_LABEL_MAGIC_PJ = 16;
	//LABEL SALUD PJ
	private static final int POS_X_LABEL_SALUD_PJ = 80;
	private static final int POS_Y_LABEL_SALUD_PJ = 100;
	private static final int ANCHO_LABEL_SALUD_PJ = 77;
	private static final int ALTO_LABEL_SALUD_PJ = 16;
	//LABEL FUERZA PJ
	private static final int POS_X_LABEL_FZA_PJ = 80;
	private static final int POS_Y_LABEL_FZA_PJ = 129;
	private static final int ANCHO_LABEL_FZA_PJ = 77;
	private static final int ALTO_LABEL_FZA_PJ = 16;
	//LABEL INTELIGENCIA PJ
	private static final int POS_X_LABEL_DES_PJ = 80;
	private static final int POS_Y_LABEL_DES_PJ = 158;
	private static final int ANCHO_LABEL_DES_PJ = 77;
	private static final int ALTO_LABEL_DES_PJ = 16;
	//LABEL INTELIGENCIA PJ
	private static final int POS_X_LABEL_INT_PJ = 80;
	private static final int POS_Y_LABEL_INT_PJ = 187;
	private static final int ANCHO_LABEL_INT_PJ = 77;
	private static final int ALTO_LABEL_INT_PJ = 16;
	//LABEL CANT ITEM
	private static final int POS_X_LABEL_CANT_ITEM = 118;
	private static final int POS_Y_LABEL_CANT_ITEM = 216;
	private static final int ANCHO_LABEL_CANT_ITEM = 39;
	private static final int ALTO_LABEL_CANT_ITEM = 16;
	//LABEL LEVEL PJ
	private static final int POS_X_LABEL_LEVEL_PJ = 251;
	private static final int POS_Y_LABEL_LEVEL_PJ = 13;
	private static final int ANCHO_LABEL_LEVEL_PJ = 77;
	private static final int ALTO_LABEL_LEVEL_PJ = 16;
	//LABEL CANTIDAD DE ITEMS
	private static final int POS_X_LABEL_CANT_ITEMS = 12;
	private static final int POS_Y_LABEL_CANT_ITEMS = 216;
	private static final int ANCHO_LABEL_CANT_ITEMS = 110;
	private static final int ALTO_LABEL_CANT_ITEMS = 16;
	//LABEL NOMBRE PJ
	private static final int POS_X_LABEL_NOMBRE_PJ = 80;
	private static final int POS_Y_LABEL_NOMBRE_PJ = 13;
	private static final int ANCHO_LABEL_NOMBRE_PJ = 77;
	private static final int ALTO_LABEL_NOMBRE_PJ = 16;
	//LABEL CASTA PJ
	private static final int POS_X_LABEL_CASTA_PJ = 80;
	private static final int POS_Y_LABEL_CASTA_PJ = 42;
	private static final int ANCHO_LABEL_CASTA_PJ = 77;
	private static final int ALTO_LABEL_CASTA_PJ = 16;
	//LABEL RAZA PJ
	private static final int POS_X_LABEL_RAZA_PJ = 80;
	private static final int POS_Y_LABEL_RAZA_PJ = 71;
	private static final int ANCHO_LABEL_RAZA_PJ = 77;
	private static final int ALTO_LABEL_RAZA_PJ = 16;
	//LABEL FUERZA
	private static final int POS_X_LABEL_FUERZA = 12;
	private static final int POS_Y_LABEL_FUERZA = 129;
	private static final int ANCHO_LABEL_FUERZA = 48;
	private static final int ALTO_LABEL_FUERZA = 16;
	//LABEL DESTREZA
	private static final int POS_X_LABEL_DESTREZA = 12;
	private static final int POS_Y_LABEL_DESTREZA = 158;
	private static final int ANCHO_LABEL_DESTREZA = 56;
	private static final int ALTO_LABEL_DESTREZA = 16;
	//LABEL INTELIGENCIA
	private static final int POS_X_LABEL_INTELIGENCIA = 12;
	private static final int POS_Y_LABEL_INTELIGENCIA = 187;
	private static final int ANCHO_LABEL_INTELIGENCIA = 72;
	private static final int ALTO_LABEL_INTELIGENCIA = 16;
	//LABEL ATAQUE
	private static final int POS_X_LABEL_ATAQUE = 169;
	private static final int POS_Y_LABEL_ATAQUE = 129;
	private static final int ANCHO_LABEL_ATAQUE = 48;
	private static final int ALTO_LABEL_ATAQUE = 16;
	//LABEL DEFENSA
	private static final int POS_X_LABEL_DEFENSA = 169;
	private static final int POS_Y_LABEL_DEFENSA = 158;
	private static final int ANCHO_LABEL_DEFENSA = 56;
	private static final int ALTO_LABEL_DEFENSA = 16;
	//LABEL MAGIA
	private static final int POS_X_LABEL_MAGIA = 169;
	private static final int POS_Y_LABEL_MAGIA = 187;
	private static final int ANCHO_LABEL_MAGIA = 39;
	private static final int ALTO_LABEL_MAGIA = 16;
	//LABEL CASTA
	private static final int POS_X_LABEL_CASTA = 12;
	private static final int POS_Y_LABEL_CASTA = 42;
	private static final int ANCHO_LABEL_CASTA = 56;
	private static final int ALTO_LABEL_CASTA = 16;
	//LABEL RAZA
	private static final int POS_X_LABEL_RAZA = 12;
	private static final int POS_Y_LABEL_RAZA = 71;
	private static final int ANCHO_LABEL_RAZA = 56;
	private static final int ALTO_LABEL_RAZA = 16;
	//LABEL NIVEL
	private static final int POS_X_LABEL_NIVEL = 169;
	private static final int POS_Y_LABEL_NIVEL = 13;
	private static final int ANCHO_LABEL_NIVEL = 56;
	private static final int ALTO_LABEL_NIVEL = 16;
	//LABEL EXPERIENCIA
	private static final int POS_X_LABEL_EXPERIENCIA = 169;
	private static final int POS_Y_LABEL_EXPERIENCIA = 42;
	private static final int ANCHO_LABEL_EXPERIENCIA = 72;
	private static final int ALTO_LABEL_EXPERIENCIA = 16;
	//LABEL ENERGIA
	private static final int POS_X_LABEL_ENERGIA = 169;
	private static final int POS_Y_LABEL_ENERGIA = 100;
	private static final int ANCHO_LABEL_ENERGIA = 48;
	private static final int ALTO_LABEL_ENERGIA = 16;
	//LABEL SALUD
	private static final int POS_X_LABEL_SALUD = 12;
	private static final int POS_Y_LABEL_SALUD = 100;
	private static final int ANCHO_LABEL_SALUD = 56;
	private static final int ALTO_LABEL_SALUD = 16;
	//LABEL NOMBRE
	private static final int POS_X_LABEL_NOMBRE = 12;
	private static final int POS_Y_LABEL_NOMBRE = 13;
	private static final int ANCHO_LABEL_NOMBRE = 56;
	private static final int ALTO_LABEL_NOMBRE = 16;
	//VENTANA
	private static final int POS_X_VENTANA = 100;
	private static final int POS_Y_VENTANA = 100;
	private static final int ANCHO_VENTANA = 346;
	private static final int ALTO_VENTANA = 321;
	//EMPTY BORDER
	private static final int TOP_CONTENTPANE = 5;
	private static final int BOTTOM_CONTENTPANE = 5;
	private static final int LEFT_CONTENTPANE = 5;
	private static final int RIGHT_CONTENTPANE = 5;
	/**
	 * Create the frame.
	 * @param cliente Cliente actual
	 */
	public MenuStats(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(
				Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(
								MenuJugar.class.getResource("/cursor.png")).getImage(),
						new Point(0, 0), "custom cursor"));

		paquetePersonaje = cliente.getPaquetePersonaje();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(
				POS_X_VENTANA,
				POS_Y_VENTANA,
				ANCHO_VENTANA,
				ALTO_VENTANA);
		this.setLocationRelativeTo(null);
		this.setTitle("Estadísticas");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(
				TOP_CONTENTPANE,
				LEFT_CONTENTPANE,
				BOTTOM_CONTENTPANE,
				RIGHT_CONTENTPANE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				Pantalla.setMenuStats(null);
				dispose();
			}
		});

		BufferedImage imagenFondo = null;
		try {
			imagenFondo = ImageIO.read(new File("recursos//background.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

		}

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(
				POS_X_LABEL_NOMBRE,
				POS_Y_LABEL_NOMBRE,
				ANCHO_LABEL_NOMBRE,
				ALTO_LABEL_NOMBRE);
		contentPane.add(lblNombre);

		JLabel lblCasta = new JLabel("Casta");
		lblCasta.setForeground(Color.WHITE);
		lblCasta.setBounds(
				POS_X_LABEL_CASTA,
				POS_Y_LABEL_CASTA,
				ANCHO_LABEL_CASTA,
				ALTO_LABEL_CASTA);
		contentPane.add(lblCasta);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setForeground(Color.WHITE);
		lblRaza.setBounds(
				POS_X_LABEL_RAZA,
				POS_Y_LABEL_RAZA,
				ANCHO_LABEL_RAZA,
				ALTO_LABEL_RAZA);
		contentPane.add(lblRaza);

		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setForeground(Color.WHITE);
		lblNivel.setBounds(
				POS_X_LABEL_NIVEL,
				POS_Y_LABEL_NIVEL,
				ANCHO_LABEL_NIVEL,
				ALTO_LABEL_NIVEL);
		contentPane.add(lblNivel);

		JLabel lblExperiencia = new JLabel("Experiencia");
		lblExperiencia.setForeground(Color.WHITE);
		lblExperiencia.setBounds(
				POS_X_LABEL_EXPERIENCIA,
				POS_Y_LABEL_EXPERIENCIA,
				ANCHO_LABEL_EXPERIENCIA,
				ALTO_LABEL_EXPERIENCIA);
		contentPane.add(lblExperiencia);

		JLabel lblEnergia = new JLabel("Energia");
		lblEnergia.setForeground(Color.WHITE);
		lblEnergia.setBounds(
				POS_X_LABEL_ENERGIA,
				POS_Y_LABEL_ENERGIA,
				ANCHO_LABEL_ENERGIA,
				ALTO_LABEL_ENERGIA);
		contentPane.add(lblEnergia);

		JLabel lblSalud = new JLabel("Salud");
		lblSalud.setForeground(Color.WHITE);
		lblSalud.setBounds(
				POS_X_LABEL_SALUD,
				POS_Y_LABEL_SALUD,
				ANCHO_LABEL_SALUD,
				ALTO_LABEL_SALUD);
		contentPane.add(lblSalud);

		JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setBounds(
				POS_X_LABEL_FUERZA,
				POS_Y_LABEL_FUERZA,
				ANCHO_LABEL_FUERZA,
				ALTO_LABEL_FUERZA);
		contentPane.add(lblFuerza);

		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setBounds(
				POS_X_LABEL_DESTREZA,
				POS_Y_LABEL_DESTREZA,
				ANCHO_LABEL_DESTREZA,
				ALTO_LABEL_DESTREZA);
		contentPane.add(lblDestreza);

		JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setBounds(
				POS_X_LABEL_INTELIGENCIA,
				POS_Y_LABEL_INTELIGENCIA,
				ANCHO_LABEL_INTELIGENCIA,
				ALTO_LABEL_INTELIGENCIA);
		contentPane.add(lblInteligencia);

		JLabel lblAtaque = new JLabel("Ataque");
		lblAtaque.setForeground(Color.WHITE);
		lblAtaque.setBounds(
				POS_X_LABEL_ATAQUE,
				POS_Y_LABEL_ATAQUE,
				ANCHO_LABEL_ATAQUE,
				ALTO_LABEL_ATAQUE);
		contentPane.add(lblAtaque);

		JLabel lblDefensa = new JLabel("Defensa");
		lblDefensa.setForeground(Color.WHITE);
		lblDefensa.setBounds(
				POS_X_LABEL_DEFENSA,
				POS_Y_LABEL_DEFENSA,
				ANCHO_LABEL_DEFENSA,
				ALTO_LABEL_DEFENSA);
		contentPane.add(lblDefensa);

		JLabel lblMagia = new JLabel("Magia");
		lblMagia.setForeground(Color.WHITE);
		lblMagia.setBounds(
				POS_X_LABEL_MAGIA,
				POS_Y_LABEL_MAGIA,
				ANCHO_LABEL_MAGIA,
				ALTO_LABEL_MAGIA);
		contentPane.add(lblMagia);

		JLabel lblCantidadDeItems = new JLabel("Cantidad de Items");
		lblCantidadDeItems.setForeground(Color.WHITE);
		lblCantidadDeItems.setBounds(
				POS_X_LABEL_CANT_ITEMS,
				POS_Y_LABEL_CANT_ITEMS,
				ANCHO_LABEL_CANT_ITEMS,
				ALTO_LABEL_CANT_ITEMS);
		contentPane.add(lblCantidadDeItems);

		JLabel nmbPj = new JLabel(paquetePersonaje.getNombre());
		nmbPj.setForeground(Color.WHITE);
		nmbPj.setHorizontalAlignment(SwingConstants.RIGHT);
		nmbPj.setBounds(
				POS_X_LABEL_NOMBRE_PJ,
				POS_Y_LABEL_NOMBRE_PJ,
				ANCHO_LABEL_NOMBRE_PJ,
				ALTO_LABEL_NOMBRE_PJ);
		contentPane.add(nmbPj);

		JLabel cstPj = new JLabel(paquetePersonaje.getCasta());
		cstPj.setForeground(Color.WHITE);
		cstPj.setHorizontalAlignment(SwingConstants.RIGHT);
		cstPj.setBounds(
				POS_X_LABEL_CASTA_PJ,
				POS_Y_LABEL_CASTA_PJ,
				ANCHO_LABEL_CASTA_PJ,
				ALTO_LABEL_CASTA_PJ);
		contentPane.add(cstPj);

		JLabel rzPj = new JLabel(paquetePersonaje.getRaza());
		rzPj.setForeground(Color.WHITE);
		rzPj.setHorizontalAlignment(SwingConstants.RIGHT);
		rzPj.setBounds(
				POS_X_LABEL_RAZA_PJ,
				POS_Y_LABEL_RAZA_PJ,
				ANCHO_LABEL_RAZA_PJ,
				ALTO_LABEL_RAZA_PJ);
		contentPane.add(rzPj);

		JLabel saludPj = new JLabel(String.valueOf(paquetePersonaje.getSaludTope()));
		saludPj.setForeground(Color.WHITE);
		saludPj.setHorizontalAlignment(SwingConstants.RIGHT);
		saludPj.setBounds(
				POS_X_LABEL_SALUD_PJ,
				POS_Y_LABEL_SALUD_PJ,
				ANCHO_LABEL_SALUD_PJ,
				ALTO_LABEL_SALUD_PJ);
		contentPane.add(saludPj);

		JLabel fzaPj = new JLabel(String.valueOf(paquetePersonaje.getFuerza()));
		fzaPj.setForeground(Color.WHITE);
		fzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
		fzaPj.setBounds(
				POS_X_LABEL_FZA_PJ,
				POS_Y_LABEL_FZA_PJ,
				ANCHO_LABEL_FZA_PJ,
				ALTO_LABEL_FZA_PJ);
		contentPane.add(fzaPj);

		JLabel dstzaPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
		dstzaPj.setForeground(Color.WHITE);
		dstzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
		dstzaPj.setBounds(
				POS_X_LABEL_DES_PJ,
				POS_Y_LABEL_DES_PJ,
				ANCHO_LABEL_DES_PJ,
				ALTO_LABEL_DES_PJ);
		contentPane.add(dstzaPj);

		JLabel intPj = new JLabel(String.valueOf(paquetePersonaje.getInteligencia()));
		intPj.setForeground(Color.WHITE);
		intPj.setHorizontalAlignment(SwingConstants.RIGHT);
		intPj.setBounds(
				POS_X_LABEL_INT_PJ,
				POS_Y_LABEL_INT_PJ,
				ANCHO_LABEL_INT_PJ,
				ALTO_LABEL_INT_PJ);
		contentPane.add(intPj);

		JLabel cantItem = new JLabel(String.valueOf(paquetePersonaje.getCantItems()));
		cantItem.setForeground(Color.WHITE);
		cantItem.setHorizontalAlignment(SwingConstants.RIGHT);
		cantItem.setBounds(
				POS_X_LABEL_CANT_ITEM,
				POS_Y_LABEL_CANT_ITEM,
				ANCHO_LABEL_CANT_ITEM,
				ALTO_LABEL_CANT_ITEM);
		contentPane.add(cantItem);

		JLabel lvPj = new JLabel(String.valueOf(paquetePersonaje.getNivel()));
		lvPj.setForeground(Color.WHITE);
		lvPj.setHorizontalAlignment(SwingConstants.RIGHT);
		lvPj.setBounds(
				POS_X_LABEL_LEVEL_PJ,
				POS_Y_LABEL_LEVEL_PJ,
				ANCHO_LABEL_LEVEL_PJ,
				ALTO_LABEL_LEVEL_PJ);
		contentPane.add(lvPj);

		JLabel xpPj = new JLabel(String.valueOf(paquetePersonaje.getExperiencia()));
		xpPj.setForeground(Color.WHITE);
		xpPj.setHorizontalAlignment(SwingConstants.RIGHT);
		xpPj.setBounds(
				POS_X_LABEL_EXP_PJ,
				POS_Y_LABEL_EXP_PJ,
				ANCHO_LABEL_EXP_PJ,
				ALTO_LABEL_EXP_PJ);
		contentPane.add(xpPj);

		JLabel energiaPj = new JLabel(String.valueOf(paquetePersonaje.getEnergiaTope()));
		energiaPj.setForeground(Color.WHITE);
		energiaPj.setHorizontalAlignment(SwingConstants.RIGHT);
		energiaPj.setBounds(
				POS_X_LABEL_ENG_PJ,
				POS_Y_LABEL_ENG_PJ,
				ANCHO_LABEL_ENG_PJ,
				ALTO_LABEL_ENG_PJ);
		contentPane.add(energiaPj);

		int ataquePj = calcularAtaque(paquetePersonaje.getFuerza());
		JLabel ataPj = new JLabel(String.valueOf(ataquePj));
		ataPj.setForeground(Color.WHITE);
		ataPj.setHorizontalAlignment(SwingConstants.RIGHT);
		ataPj.setBounds(
				POS_X_LABEL_ATQ_PJ,
				POS_Y_LABEL_ATQ_PJ,
				ANCHO_LABEL_ATQ_PJ,
				ALTO_LABEL_ATQ_PJ);
		contentPane.add(ataPj);

		JLabel defPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
		defPj.setForeground(Color.WHITE);
		defPj.setHorizontalAlignment(SwingConstants.RIGHT);
		defPj.setBounds(
				POS_X_LABEL_DEF_PJ,
				POS_Y_LABEL_DEF_PJ,
				ANCHO_LABEL_DEF_PJ,
				ALTO_LABEL_DEF_PJ);
		contentPane.add(defPj);

		int intePj = calcularMagia(paquetePersonaje.getInteligencia());
		JLabel magicPj = new JLabel(String.valueOf(intePj));
		magicPj.setForeground(Color.WHITE);
		magicPj.setHorizontalAlignment(SwingConstants.RIGHT);
		magicPj.setBounds(
				POS_X_LABEL_MAGIC_PJ,
				POS_Y_LABEL_MAGIC_PJ,
				ANCHO_LABEL_MAGIC_PJ,
				ALTO_LABEL_MAGIC_PJ);
		contentPane.add(magicPj);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon("recursos//volver.png"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				Pantalla.setMenuStats(null);
				dispose();
			}
		});
		btnVolver.setBounds(
				POS_X_BOTON_VOLVER,
				POS_Y_BOTON_VOLVER,
				ANCHO_BOTON_VOLVER,
				ALTO_BOTON_VOLVER);
		contentPane.add(btnVolver);
		JLabel background = new JLabel(
				new ImageIcon(
						imagenFondo.getScaledInstance(
								SCALE_ANCHO_BACKGROUND,
								SCALE_ALTO_BACKGROUND,
								Image.SCALE_DEFAULT)));
		background.setBounds(
				POS_X_BACKGROUND,
				POS_Y_BACKGROUND,
				ANCHO_BACKGROUND,
				ALTO_BACKGROUND);
		contentPane.add(background);
	}

	/**
	 * Calcula la magia dada la inteligencia
	 * @param inteligencia inteligencia a usar
	 * @return daño a realizar
	 */
	private int calcularMagia(final int inteligencia) {
		return (int) (inteligencia * mod);
	}

	/**
	 * Calcula el ataque dada una fuerza
	 * @param fuerza fuerza del ataque
	 * @return daño a realizar
	 */
	private int calcularAtaque(final int fuerza) {
		return (int) (fuerza * mod);
	}
}
