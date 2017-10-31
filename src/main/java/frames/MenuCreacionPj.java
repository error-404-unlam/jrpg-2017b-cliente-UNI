package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cliente.Cliente;
import mensajeria.Comando;
import mensajeria.PaquetePersonaje;

public class MenuCreacionPj extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JLabel destreza;
	private JLabel fuerza;
	private JLabel inteligencia;
	private JLabel salud;
	private JLabel energia;

	private JComboBox<String> cbxCasta;
	private JComboBox<String> cbxRaza;
	private final int posXLabelBackground = 0;
	private final int posYLabelBackground = 0;
	private final int anchoLabelBackground = 444;
	private final int altoLabelBackground = 271;
	private final int posXCbxRaza = 32;
	private final int posYCbxRaza = 48;
	private final int anchoCbxRaza = 76;
	private final int altoCbxRaza = 20;
	private final int posXCbxCasta = 161;
	private final int posYCbxCasta = 48;
	private final int anchoCbxCasta = 76;
	private final int altoCbxCasta = 20;
	private final int posXLblCasta = 161;
	private final int posYLblCasta = 23;
	private final int anchoLblCasta = 46;
	private final int altoLblCasta = 14;
	private final int tamFuenteLblCasta = 15;
	private final int tamFuenteLblRaza = 15;
	private final int posXLblRaza = 33;
	private final int posYLblRaza = 23;
	private final int anchoLblRaza = 46;
	private final int altoLblRaza = 14;
	private final int posXBtnAceptar = 230;
	private final int posYBtnAceptar = 174;
	private final int anchoBtnAceptar = 153;
	private final int altoBtnAceptar = 23;
	private final int tamFuenteLblAceptar = 15;
	private final int posXLblAceptar = 280;
	private final int posYLblAceptar = 173;
	private final int anchoLblAceptar = 50;
	private final int altoLblAceptar = 24;

	public MenuCreacionPj(final Cliente cliente, final PaquetePersonaje personaje, final Gson gson) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0), "custom cursor"));

		final String vecSalud[] = { "55", "50", "60" };
		final String vecEnergia[] = { "55", "60", "50" };
		final String vecFuerza[] = { "15", "10", "10" };
		final String vecDestreza[] = { "10", "10", "15" };
		final String vecInteligencia[]={"10", "15", "10"};

		//En caso de cerrar
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				personaje.setNombre(nombre.getText());
				if (nombre.getText().equals(""))
					personaje.setNombre("nameless");
				personaje.setRaza((String) cbxRaza.getSelectedItem());
				personaje.setSaludTope(Integer.parseInt(vecSalud[cbxRaza.getSelectedIndex()]));
				personaje.setEnergiaTope(Integer.parseInt(vecEnergia[cbxRaza.getSelectedIndex()]));
				personaje.setCasta((String) cbxCasta.getSelectedItem());
				personaje.setFuerza(Integer.parseInt(vecFuerza[cbxCasta.getSelectedIndex()]));
				personaje.setDestreza(Integer.parseInt(vecDestreza[cbxCasta.getSelectedIndex()]));
				personaje.setInteligencia(Integer.parseInt(vecInteligencia[cbxCasta.getSelectedIndex()]));
				synchronized (cliente) {
					cliente.notify();
				}
				dispose();
			}
		});

		setTitle("WOME - Crear personaje");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 444, 271);
		contentPane.add(layeredPane);

		JLabel lblNewLabel_5 = new JLabel("Fuerza");
		lblNewLabel_5.setBounds(33, 100, 46, 14);
		layeredPane.add(lblNewLabel_5, new Integer(1));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));

		fuerza = new JLabel("15");
		fuerza.setBounds(110, 102, 22, 14);
		layeredPane.add(fuerza, new Integer(1));
		fuerza.setForeground(Color.GREEN);

		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setBounds(33, 126, 60, 14);
		layeredPane.add(lblDestreza, new Integer(1));
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setFont(new Font("Tahoma", Font.PLAIN, 13));

		destreza = new JLabel("10");
		destreza.setBounds(110, 127, 22, 14);
		layeredPane.add(destreza, new Integer(1));
		destreza.setForeground(Color.GREEN);

		JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setBounds(33, 151, 66, 22);
		layeredPane.add(lblInteligencia, new Integer(1));
		lblInteligencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInteligencia.setForeground(Color.WHITE);

		inteligencia = new JLabel("10");
		inteligencia.setBounds(110, 156, 22, 14);
		layeredPane.add(inteligencia, new Integer(1));
		inteligencia.setForeground(Color.GREEN);

		JLabel lblSalud = new JLabel("Salud");
		lblSalud.setBounds(33, 183, 46, 14);
		layeredPane.add(lblSalud, new Integer(1));
		lblSalud.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSalud.setForeground(Color.WHITE);

		salud = new JLabel("55");
		salud.setBounds(110, 183, 22, 14);
		layeredPane.add(salud, new Integer(1));
		salud.setForeground(Color.GREEN);

		JLabel lblEnergia = new JLabel("Energia");
		lblEnergia.setBounds(33, 204, 46, 20);
		layeredPane.add(lblEnergia, new Integer(1));
		lblEnergia.setForeground(Color.WHITE);
		lblEnergia.setFont(new Font("Tahoma", Font.PLAIN, 13));

		energia = new JLabel("55");
		energia.setBounds(110, 208, 22, 14);
		layeredPane.add(energia, new Integer(1));
		energia.setForeground(Color.GREEN);

		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(207, 125, 60, 14);
		layeredPane.add(lblNewLabel_4, new Integer(1));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));

		nombre = new JTextField();
		nombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearPj(cliente, personaje, gson, vecSalud, vecEnergia, vecFuerza, vecDestreza, vecInteligencia);
			}
		});
		nombre.setBounds(277, 122, 122, 20);
		layeredPane.add(nombre, new Integer(1));
		nombre.setColumns(10);
		JLabel lblAceptar = new JLabel("Aceptar");
		lblAceptar.setBounds(
				posXLblAceptar,
				posYLblAceptar,
				anchoLblAceptar,
				altoLblAceptar);
		layeredPane.add(lblAceptar, new Integer(2));
		lblAceptar.setForeground(Color.WHITE);
		lblAceptar.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						tamFuenteLblAceptar));

		// En caso de apretar el boton aceptar
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(
				posXBtnAceptar,
				posYBtnAceptar,
				anchoBtnAceptar,
				altoBtnAceptar);
		layeredPane.add(btnAceptar, new Integer(1));
		btnAceptar.setFocusable(false);
		btnAceptar.setIcon(new ImageIcon(MenuCreacionPj.class.getResource("/frames/BotonMenu.png")));
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				crearPj(
						cliente, personaje, gson,
						vecSalud, vecEnergia, vecFuerza,
						vecDestreza, vecInteligencia);

			}

		});
		JLabel lblNewLabel = new JLabel("Raza");
		lblNewLabel.setBounds(
				posXLblRaza,
				posYLblRaza,
				anchoLblRaza,
				altoLblRaza);
		layeredPane.add(lblNewLabel, new Integer(1));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						tamFuenteLblRaza));

		JLabel lblCasta = new JLabel("Casta");
		lblCasta.setBounds(
				posXLblCasta,
				posYLblCasta,
				anchoLblCasta,
				altoLblCasta);
		layeredPane.add(lblCasta, new Integer(1));
		lblCasta.setForeground(Color.WHITE);
		lblCasta.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						tamFuenteLblCasta));
	
		cbxCasta = new JComboBox<>();
		cbxCasta.setBounds(
				posXCbxCasta,
				posYCbxCasta,
				anchoCbxCasta,
				altoCbxCasta);
		layeredPane.add(cbxCasta, new Integer(1));
		cbxCasta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				fuerza.setText(vecFuerza[cbxCasta.getSelectedIndex()]);
				destreza.setText(vecDestreza[cbxCasta.getSelectedIndex()]);
				inteligencia.setText(vecInteligencia[cbxCasta.getSelectedIndex()]);
			}
		});
		cbxCasta.addItem("Guerrero");
		cbxCasta.addItem("Hechicero");
		cbxCasta.addItem("Asesino");
		cbxRaza = new JComboBox<>();
		cbxRaza.setBounds(
				posXCbxRaza,
				posYCbxRaza,
				anchoCbxRaza,
				altoCbxRaza);
		layeredPane.add(cbxRaza, new Integer(1));
		cbxRaza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				salud.setText(vecSalud[cbxRaza.getSelectedIndex()]);
				energia.setText(vecEnergia[cbxRaza.getSelectedIndex()]);
			}
		});
		cbxRaza.addItem("Humano");
		cbxRaza.addItem("Elfo");
		cbxRaza.addItem("Orco");

		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(
				posXLabelBackground,
				posYLabelBackground,
				anchoLabelBackground,
				altoLabelBackground);
		layeredPane.add(lblBackground, new Integer(0));
		lblBackground.setIcon(new ImageIcon(MenuCreacionPj.class.getResource("/frames/menuBackground.jpg")));
	}

	/**
	 * Metodo Crear personaje
	 * @param cliente cliente actual
	 * @param personaje personaje
	 * @param gson gson
	 * @param vecSalud vector de salud de los personajes
	 * @param vecEnergia vector de energia de los personajes
	 * @param vecFuerza vector de fuerza de los personajes
	 * @param vecDestreza vector de destreza de los personajes
	 * @param vecInteligencia vector de inteligencia de los personajes
	 */
	protected void crearPj(
			final Cliente cliente,
			final  PaquetePersonaje personaje,
			final Gson gson,
			final String[] vecSalud,
			final String[] vecEnergia,
			final String[] vecFuerza,
			final String[] vecDestreza,
			final String[] vecInteligencia) {

		personaje.setNombre(nombre.getText());
		if (nombre.getText().equals("")) {
			personaje.setNombre("nameless");
		}
		personaje.setRaza((String) cbxRaza.getSelectedItem());
		personaje.setSaludTope(Integer.parseInt(vecSalud[cbxRaza.getSelectedIndex()]));
		personaje.setEnergiaTope(Integer.parseInt(vecEnergia[cbxRaza.getSelectedIndex()]));
		personaje.setCasta((String) cbxCasta.getSelectedItem());
		personaje.setFuerza(Integer.parseInt(vecFuerza[cbxCasta.getSelectedIndex()]));
		personaje.setDestreza(Integer.parseInt(vecDestreza[cbxCasta.getSelectedIndex()]));
		personaje.setInteligencia(Integer.parseInt(vecInteligencia[cbxCasta.getSelectedIndex()]));
		try {

			// Le envio los datos al servidor
			cliente.getPaquetePersonaje().setComando(Comando.CREACIONPJ);
			cliente.getSal().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
			dispose();
		} catch (JsonSyntaxException | IOException esd) {
			JOptionPane.showMessageDialog(null, "Error al crear personaje");

		}
	}

}