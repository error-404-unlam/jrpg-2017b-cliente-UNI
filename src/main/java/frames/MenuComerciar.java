package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import dominio.Item;
import mensajeria.Comando;

/**
 * Metodo encargado del comercio del cliente
 * @author Miguel
 */
public class MenuComerciar extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> misItems = new DefaultListModel<String>();
	private DefaultListModel<String> dar = new DefaultListModel<String>();
	private DefaultListModel<String> obtener = new DefaultListModel<String>();
	private int cantListos = 0;
	private JLabel cantListo;
	private Item item1;
	private int count = 0;
	private final Gson gson = new Gson();
	private int sizeItems;
	private JCheckBox chckbxListo;
	private JLabel leyenda;
	private final int posXChckbxListo = 289;
	private final int posYChckbxListo = 213;
	private final int anchoChckbxListo = 71;
	private final int altoChckbxListo = 25;
	private final int posXLabelBackground = -12;
	private final int posYLabelBackground = 0;
	private final int anchoLabelBackground = 628;
	private final int altoLabelBackground = 336;
	private final int posXIntEnemy = 428;
	private final int posYIntEnemy = 263;
	private final int anchoIntEnemy = 56;
	private final int altoIntEnemy = 16;
	private final int posXLeyenda = 12;
	private final int posYLeyenda = 299;
	private final int anchoLeyenda = 282;
	private final int altoLeyenda = 16;
	private final int posXBtnAgregar = 181;
	private final int posYBtnAgregar = 93;
	private final int anchoBtnAgregar = 51;
	private final int altoBtnAgregar = 25;
	private final int limSupCantItems = 9;
	private final int posXBtnSacar = 181;
	private final int posYBtnSacar = 131;
	private final int anchoBtnSacar = 51;
	private final int altoBtnSacar = 25;
	private final int posXCantListo = 317;
	private final int posYCantListo = 278;
	private final int anchoCantListo = 56;
	private final int altoCantListo = 16;
	private final int posXSaludEnemy = 428;
	private final int posYSaludEnemy = 217;
	private final int anchoSaludEnemy = 56;
	private final int altoSaludEnemy = 16;
	private final int posXEnergyEnemy = 428;
	private final int posYEnergyEnemy = 240;
	private final int anchoEnergyEnemy = 56;
	private final int altoEnergyEnemy = 16;
	private final int posXFzaEnemy = 536;
	private final int posYFzaEnemy = 217;
	private final int anchoFzaEnemy = 56;
	private final int altoFzaEnemy = 16;
	private final int posXDesEnemy = 536;
	private final int posYDesEnemy = 240;
	private final int anchoDesEnemy = 56;
	private final int altoDesEnemy = 16;
	private final int posXLblListo = 276;
	private final int posYLblListo = 279;
	private final int anchoLblListo = 56;
	private final int altoLblListo = 16;
	private final int posXBonusSalud = 51;
	private final int posYBonusSalud = 217;
	private final int anchoBonusSalud = 56;
	private final int altoBonusSalud = 16;
	private final int posXBonusEnergia = 51;
	private final int posYBonusEnergia = 240;
	private final int anchoBonusEnergia = 56;
	private final int altoBonusEnergia = 16;
	private final int posXBonusFuerza = 176;
	private final int posYBonusFuerza = 217;
	private final int anchoBonusFuerza = 56;
	private final int altoBonusFuerza = 16;
	private final int posXBonusDes = 176;
	private final int posYBonusDes = 240;
	private final int anchoBonusDes = 56;
	private final int altoBonusDes = 16;
	private final int posXBonusInt = 51;
	private final int posYBonusInt = 263;
	private final int anchoBonusInt = 56;
	private final int altoBonusInt = 16;
	private final int posXLblInteligencia = 12;
	private final int posYLblInteligencia = 263;
	private final int anchoLblInteligencia = 71;
	private final int altoLblInteligencia = 16;
	private final int posXLblSaludEnemy = 387;
	private final int posYLblSaludEnemy = 217;
	private final int anchoLblSaludEnemy = 56;
	private final int altoLblSaludEnemy = 16;
	private final int posXLblEnergiaEnemy = 387;
	private final int posYLblEnergiaEnemy = 240;
	private final int anchoLblEnergiaEnemy = 56;
	private final int altoLblEnergiaEnemy = 16;
	private final int posXLblFzaEnemy = 497;
	private final int posYLblFzaEnemy = 217;
	private final int anchoLblFzaEnemy = 56;
	private final int altoLblFzaEnemy = 16;
	private final int posXLblDesEnemy = 497;
	private final int posYLblDesEnemy = 240;
	private final int anchoLblDesEnemy = 56;
	private final int altoLblDesEnemy = 16;
	private final int posXLblIntEnemy = 387;
	private final int posYLblIntEnemy = 263;
	private final int anchoLblIntEnemy = 71;
	private final int altoLblIntEnemy = 16;
	private final int posXLblItemsAIntercambiar = 244;
	private final int posYLblItemsAIntercambiar = 13;
	private final int anchoLblItemsAIntercambiar = 157;
	private final int altoLblItemsAIntercambiar = 16;
	private final int posXLblItemsAMostrar = 428;
	private final int posYLblItemsAMostrar = 13;
	private final int anchoLblItemsAMostrar = 157;
	private final int altoLblItemsAMostrar = 16;
	private final int posXLblSalud = 12;
	private final int posYLblSalud = 217;
	private final int anchoLblSalud = 56;
	private final int altoLblSalud = 16;
	private final int posXLblEnergia = 12;
	private final int posYLblEnergia = 240;
	private final int anchoLblEnergia = 56;
	private final int altoLblEnergia = 16;
	private final int posXLblFuerza = 113;
	private final int posYLblFuerza = 217;
	private final int anchoLblFuerza = 56;
	private final int altoLblFuerza = 16;
	private final int posXLblDestreza = 113;
	private final int posYLblDestreza = 240;
	private final int anchoLblDestreza = 56;
	private final int altoLblDestreza = 16;
	private final int posXVentana = 100;
	private final int posYVentana = 100;
	private final int anchoVentana = 610;
	private final int altoVentana = 363;
	private final int posXBtnCancelar = 276;
	private final int posYBtnCancelar = 245;
	private final int anchoBtnCancelar = 97;
	private final int altoBtnCancelar = 25;
	private final int posXListMisItems = 12;
	private final int posYListMisItems = 42;
	private final int anchoListMisItems = 157;
	private final int altoListMisItems = 162;
	private final int posXListADar = 244;
	private final int posYListADar = 42;
	private final int anchoListADar = 157;
	private final int altoListADar = 162;
	private final int posXListAObtener = 428;
	private final int posYListAObtener = 42;
	private final int anchoListAObtener = 157;
	private final int altoListAObtener = 162;
	private final int posXLblMisItems = 12;
	private final int posYLblMisItems = 13;
	private final int anchoLblMisItems = 157;
	private final int altoLblMisItems = 16;
	private final int bordesVacios = 5;
	/**
	 * Create the frame.
	 * @param cliente de la ventana
	 */
	public MenuComerciar(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(
				Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(
								MenuJugar.class.getResource("/cursor.png")).getImage(),
						new Point(0, 0), "custom cursor"));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(
				posXVentana,
				posYVentana,
				anchoVentana,
				altoVentana);
		this.setLocationRelativeTo(null);
		this.setTitle("Comercio");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(
				bordesVacios,
				bordesVacios,
				bordesVacios,
				bordesVacios));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				cliente.setM1(null);
				dispose();
			}
		});

		BufferedImage imagenFondo = null;
		try {
			imagenFondo = ImageIO.read(new File("recursos//background.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

		}

		final JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("recursos//volver.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				cliente.setM1(null);
				dispose();
			}
		});
		btnCancelar.setBounds(
				posXBtnCancelar,
				posYBtnCancelar,
				anchoBtnCancelar,
				altoBtnCancelar);
		contentPane.add(btnCancelar);
		final JList<String> listMisItems = new JList<String>();
		listMisItems.setBounds(
				posXListMisItems,
				posYListMisItems,
				anchoListMisItems,
				altoListMisItems);
		contentPane.add(listMisItems);
		final JList<String> listADar = new JList<String>();
		listADar.setBounds(
				posXListADar,
				posYListADar,
				anchoListADar,
				altoListADar);
		contentPane.add(listADar);
		final JList<String> listAObtener = new JList<String>();
		listAObtener.setBounds(
				posXListAObtener,
				posYListAObtener,
				anchoListAObtener,
				altoListAObtener);
		contentPane.add(listAObtener);
		final JLabel lblMisItems = new JLabel("Mis Items");
		lblMisItems.setForeground(Color.WHITE);
		lblMisItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblMisItems.setBounds(
				posXLblMisItems,
				posYLblMisItems,
				anchoLblMisItems,
				altoLblMisItems);
		contentPane.add(lblMisItems);

		final JLabel lblItemsAIntercambiar = new JLabel("Items a Dar");
		lblItemsAIntercambiar.setForeground(Color.WHITE);
		lblItemsAIntercambiar.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsAIntercambiar.setBounds(
				posXLblItemsAIntercambiar,
				posYLblItemsAIntercambiar,
				anchoLblItemsAIntercambiar,
				altoLblItemsAIntercambiar);
		contentPane.add(lblItemsAIntercambiar);
		final JLabel lblItemsAObtener = new JLabel("Items a Obtener");
		lblItemsAObtener.setForeground(Color.WHITE);
		lblItemsAObtener.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsAObtener.setBounds(
				posXLblItemsAMostrar,
				posYLblItemsAMostrar,
				anchoLblItemsAMostrar,
				altoLblItemsAMostrar);
		contentPane.add(lblItemsAObtener);
		final JLabel lblSalud = new JLabel("Salud");
		lblSalud.setForeground(Color.WHITE);
		lblSalud.setBounds(
				posXLblSalud,
				posYLblSalud,
				anchoLblSalud,
				altoLblSalud);
		contentPane.add(lblSalud);
		final JLabel lblEnerga = new JLabel("Energía");
		lblEnerga.setForeground(Color.WHITE);
		lblEnerga.setBounds(
				posXLblEnergia,
				posYLblEnergia,
				anchoLblEnergia,
				altoLblEnergia);
		contentPane.add(lblEnerga);
		final JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setBounds(
				posXLblFuerza,
				posYLblFuerza,
				anchoLblFuerza,
				altoLblFuerza);
		contentPane.add(lblFuerza);
		final JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setBounds(
				posXLblDestreza,
				posYLblDestreza,
				anchoLblDestreza,
				altoLblDestreza);
		contentPane.add(lblDestreza);
		final JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setBounds(
				posXLblInteligencia,
				posYLblInteligencia,
				anchoLblInteligencia,
				altoLblInteligencia);
		contentPane.add(lblInteligencia);
		final JLabel lblSaludEnemy = new JLabel("Salud");
		lblSaludEnemy.setForeground(Color.WHITE);
		lblSaludEnemy.setBounds(
				posXLblSaludEnemy,
				posYLblSaludEnemy,
				anchoLblSaludEnemy,
				altoLblSaludEnemy);
		contentPane.add(lblSaludEnemy);
		final JLabel lblEnergiaEnemy = new JLabel("Energía");
		lblEnergiaEnemy.setForeground(Color.WHITE);
		lblEnergiaEnemy.setBounds(
				posXLblEnergiaEnemy,
				posYLblEnergiaEnemy,
				anchoLblEnergiaEnemy,
				altoLblEnergiaEnemy);
		contentPane.add(lblEnergiaEnemy);
		final JLabel lblFzaEnemy = new JLabel("Fuerza");
		lblFzaEnemy.setForeground(Color.WHITE);
		lblFzaEnemy.setBounds(
				posXLblFzaEnemy,
				posYLblFzaEnemy,
				anchoLblFzaEnemy,
				altoLblFzaEnemy);
		contentPane.add(lblFzaEnemy);
		final JLabel lblDesEnemy = new JLabel("Destreza");
		lblDesEnemy.setForeground(Color.WHITE);
		lblDesEnemy.setBounds(
				posXLblDesEnemy,
				posYLblDesEnemy,
				anchoLblDesEnemy,
				altoLblDesEnemy);
		contentPane.add(lblDesEnemy);
		final JLabel lblIntEnemy = new JLabel("Inteligencia");
		lblIntEnemy.setForeground(Color.WHITE);
		lblIntEnemy.setBounds(
				posXLblIntEnemy,
				posYLblIntEnemy,
				anchoLblIntEnemy,
				altoLblIntEnemy);
		contentPane.add(lblIntEnemy);
		final JLabel lblListo = new JLabel("Listo");
		lblListo.setForeground(Color.WHITE);
		lblListo.setBounds(
				posXLblListo,
				posYLblListo,
				anchoLblListo,
				altoLblListo);
		contentPane.add(lblListo);
		final JLabel bonusSalud = new JLabel("");
		bonusSalud.setForeground(Color.WHITE);
		bonusSalud.setHorizontalAlignment(SwingConstants.RIGHT);
		bonusSalud.setBounds(
				posXBonusSalud,
				posYBonusSalud,
				anchoBonusSalud,
				altoBonusSalud);
		contentPane.add(bonusSalud);
		final JLabel bonusEnergia = new JLabel("");
		bonusEnergia.setForeground(Color.WHITE);
		bonusEnergia.setHorizontalAlignment(SwingConstants.RIGHT);
		bonusEnergia.setBounds(
				posXBonusEnergia,
				posYBonusEnergia,
				anchoBonusEnergia,
				altoBonusEnergia);
		contentPane.add(bonusEnergia);
		final JLabel bonusFuerza = new JLabel("");
		bonusFuerza.setForeground(Color.WHITE);
		bonusFuerza.setHorizontalAlignment(SwingConstants.RIGHT);
		bonusFuerza.setBounds(
				posXBonusFuerza,
				posYBonusFuerza,
				anchoBonusFuerza,
				altoBonusFuerza);
		contentPane.add(bonusFuerza);
		final JLabel bonusDes = new JLabel("");
		bonusDes.setForeground(Color.WHITE);
		bonusDes.setHorizontalAlignment(SwingConstants.RIGHT);
		bonusDes.setBounds(
				posXBonusDes,
				posYBonusDes,
				anchoBonusDes,
				altoBonusDes);
		contentPane.add(bonusDes);
		final JLabel bonusInt = new JLabel("");
		bonusInt.setForeground(Color.WHITE);
		bonusInt.setHorizontalAlignment(SwingConstants.RIGHT);
		bonusInt.setBounds(
				posXBonusInt,
				posYBonusInt,
				anchoBonusInt,
				altoBonusInt);
		contentPane.add(bonusInt);
		final JLabel saludEnemy = new JLabel("");
		saludEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
		saludEnemy.setForeground(Color.WHITE);
		saludEnemy.setBounds(
				posXSaludEnemy,
				posYSaludEnemy,
				anchoSaludEnemy,
				altoSaludEnemy);
		contentPane.add(saludEnemy);
		final JLabel energyEnemy = new JLabel("");
		energyEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
		energyEnemy.setForeground(Color.WHITE);
		energyEnemy.setBounds(
				posXEnergyEnemy,
				posYEnergyEnemy,
				anchoEnergyEnemy,
				altoEnergyEnemy);
		contentPane.add(energyEnemy);
		final JLabel fzaEnemy = new JLabel("");
		fzaEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
		fzaEnemy.setForeground(Color.WHITE);
		fzaEnemy.setBounds(
				posXFzaEnemy,
				posYFzaEnemy,
				anchoFzaEnemy,
				altoFzaEnemy);
		contentPane.add(fzaEnemy);
		final JLabel desEnemy = new JLabel("");
		desEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
		desEnemy.setForeground(Color.WHITE);
		desEnemy.setBounds(
				posXDesEnemy,
				posYDesEnemy,
				anchoDesEnemy,
				altoDesEnemy);
		contentPane.add(desEnemy);
		final JLabel intEnemy = new JLabel("");
		intEnemy.setHorizontalAlignment(SwingConstants.RIGHT);
		intEnemy.setForeground(Color.WHITE);
		intEnemy.setBounds(
				posXIntEnemy,
				posYIntEnemy,
				anchoIntEnemy,
				altoIntEnemy);
		contentPane.add(intEnemy);
		chckbxListo = new JCheckBox("Listo");
		chckbxListo.setForeground(Color.WHITE);
		chckbxListo.setBackground(Color.BLACK);
		// Arranca deshabilitada
		chckbxListo.setEnabled(false);

		leyenda = new JLabel("Recuerda que la máxima cantidad de items es 9");
		leyenda.setForeground(Color.WHITE);
		leyenda.setBounds(
				posXLeyenda,
				posYLeyenda,
				anchoLeyenda,
				altoLeyenda);
		contentPane.add(leyenda);
		leyenda.setVisible(false);
		final JButton btnAgregar = new JButton("-->");
		btnAgregar.setIcon(new ImageIcon("recursos//flechaDer.png"));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				if (listMisItems.getSelectedValue() != null) {
					dar.addElement(listMisItems.getSelectedValue());
					if (obtener.size() != 0) {
						if (sizeItems - dar.size() + obtener.size() <= limSupCantItems) {
							chckbxListo.setEnabled(true);
							leyenda.setVisible(false);
						}
					}
					// Pongo el primer item y pregunto si es igual al
					// seleccionado
					// Entonces mientras que sean distinto lo busca
					// Cuando sea igual sale del while y lo agrega en la lista
					item1 = cliente.getPaquetePersonaje().getItems().get(count);
					while (!item1.getNombre().equals(listMisItems.getSelectedValue())) {
						count++;
						item1 = cliente.getPaquetePersonaje().getItems().get(count);
					}
					count = 0;
					cliente.getPaqueteComercio().getItemsADar().add(item1);
					misItems.removeElement(listMisItems.getSelectedValue());
					cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
					try {
						cliente.getSal().writeObject(gson.toJson(cliente.getPaqueteComercio()));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
					}
					if (misItems.size() == 0) {
						bonusSalud.setText("");
						bonusEnergia.setText("");
						bonusFuerza.setText("");
						bonusDes.setText("");
						bonusInt.setText("");
					}
				}
			}
		});
		btnAgregar.setBounds(
				posXBtnAgregar,
				posYBtnAgregar,
				anchoBtnAgregar,
				altoBtnAgregar);
		contentPane.add(btnAgregar);
		final JButton btnSacar = new JButton("<--");
		btnSacar.setIcon(new ImageIcon("recursos//flechaIzq.png"));
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				if (listADar.getSelectedValue() != null) {
					misItems.addElement(listADar.getSelectedValue());
					for (Item item : cliente.getPaquetePersonaje().getItems()) {
						if (item.getNombre().equals(listADar.getSelectedValue())) {
							cliente.getPaqueteComercio().getItemsADar().remove(item);
						}
					}
					dar.removeElement(listADar.getSelectedValue());
					// Si saque el item y la lista no tiene nada deshabilito el
					// check
					if (dar.size() == 0) {
						chckbxListo.setEnabled(false);
					}
					// Si los items en total es mayor a 9 no puedo comerciar
					if (sizeItems - dar.size() + obtener.size()
					> limSupCantItems) {
						chckbxListo.setEnabled(false);
						leyenda.setVisible(true);
					}
					cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
					try {
						cliente.getSal().writeObject(gson.toJson(cliente.getPaqueteComercio()));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "No se pudo actualizar comercio");
					}
					// Cuando paso un item de ofertar a no ofertado muestro el
					// que movi
					int i = misItems.size();
					if (i >= 1) {
						for (Item item : cliente.getPaquetePersonaje().getItems()) {
							if (misItems.getElementAt(i - 1).equals(item.getNombre())) {
								bonusSalud.setText("+ " + item.getBonusSalud());
								bonusEnergia.setText("+ " + item.getBonusEnergia());
								bonusFuerza.setText("+ " + item.getBonusFuerza());
								bonusDes.setText("+ " + item.getBonusDestreza());
								bonusInt.setText("+ " + item.getBonusInteligencia());
							}
						}
					}
				}
			}
		});
		btnSacar.setBounds(
				posXBtnSacar,
				posYBtnSacar,
				anchoBtnSacar,
				altoBtnSacar);
		contentPane.add(btnSacar);
		// List Listener para cargar stats del item mio clickeado
		listMisItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					if (listMisItems.getSelectedValue() != null) {
						for (Item item : cliente.getPaquetePersonaje().getItems()) {
							if (listMisItems.getSelectedValue().equals(item.getNombre())) {
								bonusSalud.setText("+ " + item.getBonusSalud());
								bonusEnergia.setText("+ " + item.getBonusEnergia());
								bonusFuerza.setText("+ " + item.getBonusFuerza());
								bonusDes.setText("+ " + item.getBonusDestreza());
								bonusInt.setText("+ " + item.getBonusInteligencia());
							}
						}
					}
				}
			}
		});

		// List Listener para cargar stats del item del enemigo clickeado
		listAObtener.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					if (obtener.size() != 0) {
						// cambiar la variable del for each a la lista que va a
						// venir del otro pj
						for (Item item : cliente.getPaqueteComercio().getItemsAObtener()) {
							if (listAObtener.getSelectedValue().equals(item.getNombre())) {
								saludEnemy.setText("+ " + item.getBonusSalud());
								energyEnemy.setText("+ " + item.getBonusEnergia());
								fzaEnemy.setText("+ " + item.getBonusFuerza());
								desEnemy.setText("+ " + item.getBonusDestreza());
								intEnemy.setText("+ " + item.getBonusInteligencia());
							}
						}
					}
				}
			}
		});

		// CARGO MIS ITEMS
		for (Item item : cliente.getPaquetePersonaje().getItems()) {
			misItems.addElement(item.getNombre());
		}

		// Seteo la cantidad de mis items en mi mochila
		sizeItems = misItems.size();

		// Seteo de JList
		listMisItems.setModel(misItems);
		listADar.setModel(dar);
		listAObtener.setModel(obtener);

		cantListo = new JLabel("0/2");
		cantListo.setHorizontalAlignment(SwingConstants.RIGHT);
		cantListo.setForeground(Color.WHITE);
		cantListo.setBounds(
				posXCantListo,
				posYCantListo,
				anchoCantListo,
				altoCantListo);
		contentPane.add(cantListo);
		chckbxListo.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent arg0) {
				if (chckbxListo.isSelected()) {
					// Si ya la persona con la que voy a comerciar esta en LISTO
					if (cantListos == 1) {
						cantListos++;
						// Primero actualizo el label de cant Listos
						cantListo.setText(cantListos + "/2");
						// Le envio al otro que toque listo y esta 2/2 listo
						// para trueque
						cliente.getPaqueteComercio().aumentarListo();
						cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
						try {
							cliente.getSal().writeObject(
									gson.toJson(
											cliente.getPaqueteComercio()));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(
									null,
									"No se pudo actualizar comercio");
						}
						////////
						// Ahora le digo que haga el trueque
						cliente.getPaqueteComercio().setComando(Comando.TRUEQUE);
						// Le informo al otro que vamos a hacer el trueque
						try {
							cliente.getSal().writeObject(
									gson.toJson(
											cliente.getPaqueteComercio()));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(
									null,
									"No se pudo actualizar comercio");
						}
						JOptionPane.showMessageDialog(
								cliente.getM1(),
								"Se ha realizado con exito el comercio");
						dispose();
					} else {
						// Si todavía LISTO = 0, le informo al otro
						cantListos++;
						// Deshabilito los botones para que no pueda agregar
						// nada
						btnAgregar.setEnabled(false);
						btnSacar.setEnabled(false);
						cliente.getPaqueteComercio().aumentarListo();
						cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
						// Tambien le tiene que avisar el LISTO al otro jugador
						try {
							cliente.getSal().writeObject(
									gson.toJson(
											cliente.getPaqueteComercio()));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(
									null,
									"No se pudo actualizar comercio");
						}
						cantListo.setText(cantListos + "/2");
					}
				} else {
					// Si habia clickeado LISTO, pero lo desclickie entonces le
					// digo
					// que disminuya en el otro cliente
					if (cantListos != 2) {
						// Si no tenia nada en la lista no tengo que disminuir
						// la cant
						// de listos
						cantListos--;
						cliente.getPaqueteComercio().disminuirListo();
						btnAgregar.setEnabled(true);
						btnSacar.setEnabled(true);
						cliente.getPaqueteComercio().setComando(Comando.ACTUALIZARCOMERCIO);
						// Tambien le tiene que avisar el NO LISTO al otro
						// jugador
						try {
							cliente.getSal().writeObject(
									gson.toJson(
											cliente.getPaqueteComercio()));
						} catch (IOException e) {
							JOptionPane.showMessageDialog(
									null,
									"No se pudo actualizar comercio");
						}
						cantListo.setText(cantListos + "/2");
					}
				}
			}
		});
		chckbxListo.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxListo.setBounds(
				posXChckbxListo,
				posYChckbxListo,
				anchoChckbxListo,
				altoChckbxListo);
		contentPane.add(chckbxListo);
		final JLabel background = new JLabel(
				new ImageIcon(
						imagenFondo.getScaledInstance(
								610, 416, Image.SCALE_DEFAULT)));
		background.setBounds(
				posXLabelBackground,
				posYLabelBackground,
				anchoLabelBackground,
				altoLabelBackground);
		contentPane.add(background);
	}

	/**
	 * Devuelve la cant de listos
	 * @return cantListos
	 */
	public int getCantListos() {
		return cantListos;
	}

	/**
	 * Setea la cantidad de listos
	 * @param cantListos a setear
	 */
	public void setCantListos(final int cantListos) {
		this.cantListos = cantListos;
	}

	/**
	 * Devuelve la cantListo
	 * @return cantListo
	 */
	public JLabel getCantListo() {
		return cantListo;
	}

	/**
	 * Setea el DefaultListModel de Obtener
	 * @param obtener lista de objetos
	 */
	public void setObtener(final DefaultListModel<String> obtener) {
		this.obtener = obtener;
	}

	/**
	 * Devuelve el Default list model de objetos de obtener
	 * @return obtener
	 */
	public DefaultListModel<String> getObtener() {
		return obtener;
	}

	/**
	 * Devuelve el Default list model de objetos de dar
	 * @return dar
	 */
	public DefaultListModel<String> getDar() {
		return dar;
	}

	/**
	 * Devuelve el tamaño de los items
	 * @return sizeItems
	 */
	public int getSizeItems() {
		return sizeItems;
	}

	/**
	 * Devuelve el checkbox de listo
	 * @return chckbxListo
	 */
	public JCheckBox getChckbxListo() {
		return chckbxListo;
	}

	/**
	 * Devuelve la leyenda
	 * @return leyenda
	 */
	public JLabel getLeyenda() {
		return leyenda;
	}
}
