package edu.unlam.wome.cliente.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import edu.unlam.wome.cliente.cliente.Cliente;
import edu.unlam.wome.cliente.juego.Pantalla;
import edu.unlam.wome.cliente.mensajeria.Comando;

/**
 * Clase encargada del menu Asignar Skills
 * @author Miguel
 */
public class MenuAsignarSkills extends JFrame {

	private JPanel contentPane;
	private final Gson gson = new Gson();

	protected static final int MAX = 200;

	private int puntosNoAsignados;
	private int puntosAsignadosFuerza;
	private int puntosAsignadosDestreza;
	private int puntosAsignadosInteligencia;

	private int puntosFuerzaMinima;
	private int puntosDestrezaMinima;
	private int puntosInteligenciaMinima;

	private int puntosAsignarInicial;
	private int puntosFuerzaInicial;
	private int puntosDestrezaInicial;
	private int puntosInteligenciaInicial;

	private int puntosAsignar;
	private int puntosFuerza;
	private int puntosDestreza;
	private int puntosInteligencia;
	private final int anchoBackground = 298;
	private final int altoBackground = 294;

	private final int posXBotonMas1 = 118;
	private final int posYBotonMas1 = 92;
	private final int anchoBotonMas1 = 34;
	private final int altoBotonMas1 = 25;
	private final int posXBotonMas2 = 118;
	private final int posYBotonMas2 = 159;
	private final int anchoBotonMas2 = 34;
	private final int altoBotonMas2 = 25;
	private final int posXBotonMas3 = 118;
	private final int posYBotonMas3 = 217;
	private final int anchoBotonMas3 = 34;
	private final int altoBotonMas3 = 25;
	private final int posXBotonMenos1 = 12;
	private final int posYBotonMenos1 = 92;
	private final int anchoBotonMenos1 = 34;
	private final int altoBotonMenos = 25;
	private final int posXBotonMenos2 = 12;
	private final int posYBotonMenos2 = 159;
	private final int anchoBotonMenos2 = 34;
	private final int altoBotonMenos2 = 25;
	private final int posXBotonMenos3 = 12;
	private final int posYBotonMenos3 = 217;
	private final int anchoBotonMenos3 = 34;
	private final int altoBotonMenos3 = 25;
	private final int posXLabelCantPuntos = 12;
	private final int posYLabelCantPuntos = 13;
	private final int anchoLabelCantPuntos = 177;
	private final int altoLabelCantPuntos = 29;
	private final int posXBotonReset = 176;
	private final int posYBotonReset = 112;
	private final int anchoBotonReset = 97;
	private final int altoBotonReset = 25;
	private final int posXBotonConfirmar = 176;
	private final int posYBotonConfirmar = 78;
	private final int anchoBotonConfirmar = 97;
	private final int altoBotonConfirmar = 25;
	private final int posXBotonCancelar = 176;
	private final int posYBotonCancelar = 146;
	private final int anchoBotonCancelar = 97;
	private final int altoBotonCancelar = 25;
	private final int posXLabelFuerza = 50;
	private final int posYLabelFuerza = 101;
	private final int anchoLabelFuerza = 56;
	private final int altoLabelFuerza = 16;
	private final int posXLblFuerza = 50;
	private final int posYLblFuerza = 72;
	private final int anchoLblFuerza = 56;
	private final int altoLblFuerza = 16;
	private final int posXLabelDestreza = 50;
	private final int posYLabelDestreza = 159;
	private final int anchoLabelDestreza = 56;
	private final int altoLabelDestreza = 16;
	private final int posXLblDestreza = 50;
	private final int posYLblDestreza = 130;
	private final int anchoLblDestreza = 56;
	private final int altoLblDestreza = 16;
	private final int posXLabelInteligencia = 50;
	private final int posYLabelInteligencia = 217;
	private final int anchoLabelInteligencia = 56;
	private final int altoLabelInteligencia = 16;
	private final int posXLblInteligencia = 39;
	private final int posYLblInteligencia = 188;
	private final int anchoLblInteligencia = 83;
	private final int altoLblInteligencia = 16;
	private final int posXLabelPuntos = 39;
	private final int posYLabelPuntos = 41;
	private final int anchoLabelPuntos = 83;
	private final int altoLabelPuntos = 26;
	private final int bordesVacios = 5;
	private final int posXVentana = 100;
	private final int posYVentana = 100;
	private final int anchoVentana = 298;
	private final int altoVentana = 294;
	//revisar estos ultimos 4
	private final int posXTitulo = 100;
	private final int posYTitulo = 100;
	private final int anchoTitulo = 450;
	private final int altoTitulo = 300;

	/**
	 * Crea la ventana.
	 * @param cliente en el que se dibujara la ventana
	 */
	public MenuAsignarSkills(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(
				Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon(
								MenuJugar.class.getResource("/cursor.png")).
						getImage(),
						new Point(0, 0),
						"custom cursor"));

		// Inicializo variables
		puntosNoAsignados = cliente.getPaquetePersonaje().getPuntosNoAsignados();
		puntosAsignadosFuerza = cliente.getPaquetePersonaje().getPuntosAsignadosFuerza();
		puntosAsignadosDestreza = cliente.getPaquetePersonaje().getPuntosAsignadosDestreza();
		puntosAsignadosInteligencia = cliente.getPaquetePersonaje().getPuntosAsignadosInteligencia();

		puntosAsignarInicial = puntosNoAsignados;
		puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();

		puntosFuerzaMinima = puntosFuerzaInicial - puntosAsignadosFuerza;
		puntosDestrezaMinima = puntosDestrezaInicial - puntosAsignadosDestreza;
		puntosInteligenciaMinima = puntosInteligenciaInicial - puntosAsignadosInteligencia;

		puntosAsignar = puntosAsignarInicial;
		puntosFuerza = puntosFuerzaInicial;
		puntosDestreza = puntosDestrezaInicial;
		puntosInteligencia = puntosInteligenciaInicial;
		// Crea la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(
				posXTitulo,
				posYTitulo,
				anchoTitulo,
				altoTitulo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(
				bordesVacios,
				bordesVacios,
				bordesVacios,
				bordesVacios));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Asignar Skills");
		setBounds(
				posXVentana,
				posYVentana,
				anchoVentana,
				altoVentana);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent arg0) {
				Pantalla.setMenuAsignar(null);
				dispose();
			}
		});

		// Puntos de fuerza
		final JLabel labelFuerza = new JLabel("");
		labelFuerza.setForeground(Color.WHITE);
		labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		labelFuerza.setBounds(
				posXLabelFuerza,
				posYLabelFuerza,
				anchoLabelFuerza,
				altoLabelFuerza);
		labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
		contentPane.add(labelFuerza);
		final JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuerza.setBounds(
				posXLblFuerza,
				posYLblFuerza,
				anchoLblFuerza,
				altoLblFuerza);
		contentPane.add(lblFuerza);

		// Puntos de destreza
		final JLabel labelDestreza = new JLabel("");
		labelDestreza.setForeground(Color.WHITE);
		labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		labelDestreza.setBounds(
				posXLabelDestreza,
				posYLabelDestreza,
				anchoLabelDestreza,
				altoLabelDestreza);
		labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
		contentPane.add(labelDestreza);
		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestreza.setBounds(
				posXLblDestreza,
				posYLblDestreza,
				anchoLblDestreza,
				altoLblDestreza);
		contentPane.add(lblDestreza);

		// Puntos de inteligencia
		final JLabel labelInteligencia = new JLabel("");
		labelInteligencia.setForeground(Color.WHITE);
		labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		labelInteligencia.setBounds(
				posXLabelInteligencia,
				posYLabelInteligencia,
				anchoLabelInteligencia,
				altoLabelInteligencia);
		labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
		contentPane.add(labelInteligencia);
		final JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteligencia.setBounds(
				posXLblInteligencia,
				posYLblInteligencia,
				anchoLblInteligencia,
				altoLblInteligencia);
		contentPane.add(lblInteligencia);
		// Puntos para asignar
		final JLabel labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(
				posXLabelPuntos,
				posYLabelPuntos,
				anchoLabelPuntos,
				altoLabelPuntos);
		labelPuntos.setText(String.valueOf(puntosAsignarInicial));
		contentPane.add(labelPuntos);

		final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
		lblCantidadDePuntos.setForeground(Color.WHITE);
		lblCantidadDePuntos.setBounds(
				posXLabelCantPuntos,
				posYLabelCantPuntos,
				anchoLabelCantPuntos,
				altoLabelCantPuntos);
		contentPane.add(lblCantidadDePuntos);
		// Botón para resetear las habilidades
		final JButton buttonReset = new JButton("Reset");
		ImageIcon iconoReset = new ImageIcon("recursos//botonReset.png");
		buttonReset.setIcon(iconoReset);
		buttonReset.setEnabled(false);
		buttonReset.addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {

				puntosFuerza = puntosFuerzaMinima;
				puntosDestreza = puntosDestrezaMinima;
				puntosInteligencia = puntosInteligenciaMinima;

				int difFuerza = puntosFuerza - puntosFuerzaInicial;
				int difDestreza = puntosDestreza - puntosDestrezaInicial;
				int difInteligencia = puntosInteligencia - puntosInteligenciaInicial;
				cliente.getPaquetePersonaje().setPuntosNoAsignados(
						puntosNoAsignados
						- difFuerza
						- difDestreza
						- difInteligencia);
				cliente.getPaquetePersonaje().setPuntosAsignadosFuerza(
						puntosAsignadosFuerza
						+ difFuerza);
				cliente.getPaquetePersonaje().setPuntosAsignadosDestreza(
						puntosAsignadosDestreza
						+ difDestreza);
				cliente.getPaquetePersonaje().setPuntosAsignadosInteligencia(
						puntosAsignadosInteligencia
						+ difInteligencia);
				cliente.getPaquetePersonaje().useBonus(0, 0, difFuerza, difDestreza, difInteligencia);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);

				puntosAsignarInicial = puntosAsignar;

				try {
					cliente.getSal().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar las estadísticas.");
				}

				JOptionPane.showMessageDialog(null, "Se han actualizado los atributos.");
				dispose();

			}
		});
		buttonReset.setBounds(
				posXBotonReset,
				posYBotonReset,
				anchoBotonReset,
				altoBotonReset);
		contentPane.add(buttonReset);
		// Botón para confirmar
		final JButton buttonConfirm = new JButton("Confirmar");
		ImageIcon iconoConfirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(iconoConfirm);
		buttonConfirm.setEnabled(false);
		buttonReset.setEnabled(true);
		buttonConfirm.addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {

				int difFuerza = puntosFuerza - puntosFuerzaInicial;
				int difDestreza = puntosDestreza - puntosDestrezaInicial;
				int difInteligencia = puntosInteligencia - puntosInteligenciaInicial;
				cliente.getPaquetePersonaje().setPuntosNoAsignados(puntosNoAsignados
						- difFuerza
						- difDestreza
						- difInteligencia);
				cliente.getPaquetePersonaje().setPuntosAsignadosFuerza(
						puntosAsignadosFuerza
						+ difFuerza);
				cliente.getPaquetePersonaje().setPuntosAsignadosDestreza(
						puntosAsignadosDestreza
						+ difDestreza);
				cliente.getPaquetePersonaje().setPuntosAsignadosInteligencia(
						puntosAsignadosInteligencia
						+ difInteligencia);
				cliente.getPaquetePersonaje().useBonus(0, 0, difFuerza, difDestreza, difInteligencia);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);

				puntosAsignarInicial = puntosAsignar;

				try {
					cliente.getSal().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar las estadísticas.");
				}

				JOptionPane.showMessageDialog(null, "Se han actualizado los atributos.");
				dispose();
			}
		});
		buttonConfirm.setBounds(
				posXBotonConfirmar,
				posYBotonConfirmar,
				anchoBotonConfirmar,
				altoBotonConfirmar);
		contentPane.add(buttonConfirm);
		// Botón para cancelar
		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon iconoC = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(iconoC);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				Pantalla.setMenuAsignar(null);
				dispose();
			}
		});
		buttonCancel.setBounds(
				posXBotonCancelar,
				posYBotonCancelar,
				anchoBotonCancelar,
				altoBotonCancelar);
		contentPane.add(buttonCancel);

		// Botones para modificar atributos
		final JButton buttonMenos1 = new JButton("");
		final JButton buttonMenos2 = new JButton("");
		final JButton buttonMenos3 = new JButton("");
		final JButton buttonMas1 = new JButton("");
		final JButton buttonMas2 = new JButton("");
		final JButton buttonMas3 = new JButton("");

		// Desactivar botones que no hacen falta
		if (puntosAsignadosFuerza == 0 && puntosAsignadosDestreza == 0 && puntosAsignadosInteligencia == 0)
		{
			buttonMenos1.setEnabled(false);
			buttonMenos2.setEnabled(false);
			buttonMenos3.setEnabled(false);
			buttonReset.setEnabled(false);
		}
		if (puntosFuerza == puntosFuerzaMinima || puntosFuerza == puntosFuerzaInicial) {
			buttonMenos1.setEnabled(false);
		}

		if (puntosDestreza == puntosDestrezaMinima || puntosDestreza == puntosDestrezaInicial) {
			buttonMenos2.setEnabled(false);
		}

		if (puntosInteligencia == puntosInteligenciaMinima || puntosInteligencia == puntosInteligenciaInicial) {
			buttonMenos3.setEnabled(false);
		}

		if (puntosAsignar == 0) {
			buttonMas1.setEnabled(false);
			buttonMas2.setEnabled(false);
			buttonMas3.setEnabled(false);
		}

		if (puntosFuerza >= MAX) {
			buttonMas1.setEnabled(false);
		}

		if (puntosDestreza >= MAX) {
			buttonMas2.setEnabled(false);
		}

		if (puntosInteligencia >= MAX) {
			buttonMas3.setEnabled(false);
		}

		// Restar fuerza
		ImageIcon icono1 = new ImageIcon("recursos//botonMenoss.png");
		buttonMenos1.setIcon(icono1);
		buttonMenos1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (puntosFuerza > puntosFuerzaInicial) {
					puntosFuerza--;
					if (puntosAsignar == 0) {
						if (puntosInteligencia < MAX) {
							buttonMas3.setEnabled(true);
						}
						if (puntosDestreza < MAX) {
							buttonMas2.setEnabled(true);
						}
					} else {
						buttonMas1.setEnabled(true);
						buttonMas2.setEnabled(true);
						buttonMas3.setEnabled(true);
					}
					puntosAsignar++;
					if (puntosAsignar == puntosAsignarInicial) {
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					if (puntosFuerza == puntosFuerzaInicial) {
						buttonMenos1.setEnabled(false);
						buttonMas1.setEnabled(true);
					} else if (puntosFuerza >= puntosFuerzaInicial) {
						buttonMas1.setEnabled(true);
					}
				}
			}
		});
		buttonMenos1.setBounds(
				posXBotonMenos1,
				posYBotonMenos1,
				anchoBotonMenos1,
				altoBotonMenos);
		contentPane.add(buttonMenos1);
		// Restar destreza
		buttonMenos2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (puntosDestreza > puntosDestrezaInicial) {
					puntosDestreza--;
					if (puntosAsignar == 0) {
						if (puntosInteligencia < MAX) {
							buttonMas3.setEnabled(true);
						}
						if (puntosFuerza < MAX) {
							buttonMas1.setEnabled(true);
						}
					} else {
						buttonMas1.setEnabled(true);
						buttonMas2.setEnabled(true);
						buttonMas3.setEnabled(true);
					}
					puntosAsignar++;
					if (puntosAsignar == puntosAsignarInicial) {
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					if (puntosDestreza == puntosDestrezaInicial) {
						buttonMenos2.setEnabled(false);
						buttonMas2.setEnabled(true);
					} else if (puntosDestreza >= puntosDestrezaInicial) {
						buttonMas2.setEnabled(true);
					}
				}
			}
		});
		buttonMenos2.setIcon(icono1);
		buttonMenos2.setBounds(
				posXBotonMenos2,
				posYBotonMenos2,
				anchoBotonMenos2,
				altoBotonMenos2);
		contentPane.add(buttonMenos2);
		// Restar inteligencia
		buttonMenos3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (puntosInteligencia > puntosInteligenciaInicial) {
					puntosInteligencia--;
					if (puntosAsignar == 0) {
						if (puntosFuerza < MAX) {
							buttonMas1.setEnabled(true);
						}
						if (puntosDestreza < MAX) {
							buttonMas2.setEnabled(true);
						}
					} else {
						buttonMas1.setEnabled(true);
						buttonMas2.setEnabled(true);
						buttonMas3.setEnabled(true);
					}
					puntosAsignar++;
					if (puntosAsignar == puntosAsignarInicial) {
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));
					if (puntosInteligencia == puntosInteligenciaInicial) {
						buttonMenos3.setEnabled(false);
						buttonMas3.setEnabled(true);
					} else if (puntosInteligencia >= puntosInteligenciaInicial) {
						buttonMas3.setEnabled(true);
					}
				}
			}
		});
		buttonMenos3.setIcon(icono1);
		buttonMenos3.setBounds(
				posXBotonMenos3,
				posYBotonMenos3,
				anchoBotonMenos3,
				altoBotonMenos3);
		contentPane.add(buttonMenos3);
		// Aumentar fuerza
		buttonMas1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (puntosAsignar != 0 && !labelFuerza.getText().equals(Integer.toString(MAX))) {
					puntosFuerza++;
					puntosAsignar--;

					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					buttonMenos1.setEnabled(true);
					if (puntosAsignar == 0) {
						buttonMas1.setEnabled(false);
						buttonMas2.setEnabled(false);
						buttonMas3.setEnabled(false);
					}
				}
				if (puntosAsignar == 0 || labelFuerza.getText().equals(Integer.toString(MAX))) {
					buttonMas1.setEnabled(false);
				}
			}
		});
		ImageIcon icono2 = new ImageIcon("recursos//botonMass.png");
		buttonMas1.setIcon(icono2);
		buttonMas1.setBounds(
				posXBotonMas1,
				posYBotonMas1,
				anchoBotonMas1,
				altoBotonMas1);
		contentPane.add(buttonMas1);

		// Aumentar destreza
		buttonMas2.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (puntosAsignar != 0 && !labelDestreza.getText().equals(Integer.toString(MAX))) {
					puntosDestreza++;
					puntosAsignar--;

					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					buttonMenos2.setEnabled(true);
					if (puntosAsignar == 0) {
						buttonMas1.setEnabled(false);
						buttonMas2.setEnabled(false);
						buttonMas3.setEnabled(false);
					}
					if (puntosAsignar == 0
							|| labelDestreza.getText().equals(
									Integer.toString(MAX))) {
						buttonMas2.setEnabled(false);
					}
				}
			}
		});
		buttonMas2.setIcon(icono2);
		buttonMas2.setBounds(posXBotonMas2,
				posYBotonMas2,
				anchoBotonMas2,
				altoBotonMas2);
		contentPane.add(buttonMas2);
		// Aumentar inteligencia
		buttonMas3.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (puntosAsignar != 0 && !labelInteligencia.getText().equals(Integer.toString(MAX))) {
					puntosInteligencia++;
					puntosAsignar--;

					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));
					buttonMenos3.setEnabled(true);
					if (puntosAsignar == 0) {
						buttonMas1.setEnabled(false);
						buttonMas2.setEnabled(false);
						buttonMas3.setEnabled(false);
					}
					if (puntosAsignar == 0
							|| labelInteligencia.getText().equals(
									Integer.toString(MAX))) {
						buttonMas3.setEnabled(false);
					}
				}
			}
		});
		buttonMas3.setIcon(icono2);
		buttonMas3.setBounds(
				posXBotonMas3,
				posYBotonMas3,
				anchoBotonMas3,
				altoBotonMas3);
		contentPane.add(buttonMas3);
		// Imagen de fondo
		final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg"));
		imageLabel.setBounds(0, 0, anchoBackground, altoBackground);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);
	}
}
