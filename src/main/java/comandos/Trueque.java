package comandos;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dominio.Casta;
import dominio.Item;
import dominio.Personaje;
import mensajeria.Comando;
import mensajeria.PaqueteComerciar;
import mensajeria.PaquetePersonaje;

/**
 * @author Los Tres Mosqueteros
 */
public class Trueque extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		PaquetePersonaje paquetePersonaje;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		Personaje pj = null;

		this.getJuego().getCli().getPaquetePersonaje().removerBonus();

		String nombre = this.getJuego().getCli().getPaquetePersonaje().getNombre();
		int salud = this.getJuego().getCli().getPaquetePersonaje().getSaludTope();
		int energia = this.getJuego().getCli().getPaquetePersonaje().getEnergiaTope();
		int fuerza = this.getJuego().getCli().getPaquetePersonaje().getFuerza();
		int destreza = this.getJuego().getCli().getPaquetePersonaje().getDestreza();
		int inteligencia = this.getJuego().getCli().getPaquetePersonaje().getInteligencia();
		int experiencia = this.getJuego().getCli().getPaquetePersonaje().getExperiencia();
		int nivel = this.getJuego().getCli().getPaquetePersonaje().getNivel();
		int id = this.getJuego().getCli().getPaquetePersonaje().getId();

		Casta casta = null;

		try {
			casta = (Casta) Class.forName("dominio" + "." + this.getJuego().getCli().
					getPaquetePersonaje().getCasta())
					.newInstance();
			pj = (Personaje) Class.forName("dominio" + "." + this.getJuego().getCli().
					getPaquetePersonaje().getRaza())
					.getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE,
							Integer.TYPE, Integer.TYPE,	Casta.class, Integer.TYPE,
							Integer.TYPE, Integer.TYPE)
					.newInstance(nombre, salud, energia, fuerza, destreza,
							inteligencia, casta, experiencia, nivel, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}
		// Si soy yo mismo, tengo que cambiar los items a darme, y despues
		// trueque
		if (id == paqueteComerciar.getId()) {
			paqueteComerciar.getItemsADar().removeAll(paqueteComerciar.getItemsADar());
			ArrayList<Item> items = this.getJuego().getPersonajesConectados().
					get(paqueteComerciar.getIdEnemigo()).getItems();
			boolean loop = true;
			int i = 0;
			while (this.getJuego().getCli().getM1().getObtener().size() > 0) {
				while (loop) {
					if (items.get(i).getNombre().equals(this.getJuego().getCli().
							getM1().getObtener().get(0))) {
						paqueteComerciar.getItemsADar().add(items.get(i));
						this.getJuego().getCli().getM1().getObtener().remove(0);
						loop = false;
					}
					i++;
				}
				loop = true;
				i = 0;
			}
			pj.trueque(this.getJuego().getCli().getPaquetePersonaje().getItems(),
					paqueteComerciar.getItemsADar(),
					this.getJuego().getCli().getM1().getDar());
		} else {
			// sino soy yo esta todo ok y trueque
			pj.trueque(this.getJuego().getCli().getPaquetePersonaje().getItems(),
					paqueteComerciar.getItemsADar(),
					this.getJuego().getCli().getM1().getDar());
		}
		this.getJuego().getCli().getPaquetePersonaje().actualizarTrueque(pj.getItems());
		paquetePersonaje = this.getJuego().getCli().getPaquetePersonaje();
		paquetePersonaje.setComando(Comando.ACTUALIZARTRUEQUE);
		this.getJuego().getCli().setM1(null);
		try {
			this.getJuego().getCli().getSal().writeObject(gson.toJson(paquetePersonaje));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar trueque");

		}

	}

}
