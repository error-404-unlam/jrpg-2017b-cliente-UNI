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

public class Trueque extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		PaquetePersonaje paquetePersonaje;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		Personaje pj = null;

		juego.getCli().getPaquetePersonaje().removerBonus();

		String nombre = juego.getCli().getPaquetePersonaje().getNombre();
		int salud = juego.getCli().getPaquetePersonaje().getSaludTope();
		int energia = juego.getCli().getPaquetePersonaje().getEnergiaTope();
		int fuerza = juego.getCli().getPaquetePersonaje().getFuerza();
		int destreza = juego.getCli().getPaquetePersonaje().getDestreza();
		int inteligencia = juego.getCli().getPaquetePersonaje().getInteligencia();
		int experiencia = juego.getCli().getPaquetePersonaje().getExperiencia();
		int nivel = juego.getCli().getPaquetePersonaje().getNivel();
		int id = juego.getCli().getPaquetePersonaje().getId();

		Casta casta = null;

		try {
			casta = (Casta) Class.forName("dominio" + "." + juego.getCli().getPaquetePersonaje().getCasta()).newInstance();
			pj = (Personaje) Class.forName("dominio" + "." + juego.getCli().getPaquetePersonaje().getRaza()).getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}
		// Si soy yo mismo, tengo que cambiar los items a darme, y despues
		// trueque
		if (id == paqueteComerciar.getId()) {
			paqueteComerciar.getItemsADar().removeAll(paqueteComerciar.getItemsADar());
			ArrayList<Item> items = juego.getPersonajesConectados().get(paqueteComerciar.getIdEnemigo()).getItems();
			boolean loop = true;
			int i = 0;
			while (juego.getCli().getM1().getObtener().size() > 0) {
				while (loop) {
					if (items.get(i).getNombre().equals(juego.getCli().getM1().getObtener().get(0))) {
						paqueteComerciar.getItemsADar().add(items.get(i));
						juego.getCli().getM1().getObtener().remove(0);
						loop = false;
					}
					i++;
				}
				loop = true;
				i = 0;
			}
			pj.trueque(juego.getCli().getPaquetePersonaje().getItems(), paqueteComerciar.getItemsADar(), juego.getCli().getM1().getDar());
		} else {
			// sino soy yo esta todo ok y trueque
			pj.trueque(juego.getCli().getPaquetePersonaje().getItems(), paqueteComerciar.getItemsADar(), juego.getCli().getM1().getDar());
		}
		juego.getCli().getPaquetePersonaje().actualizarTrueque(pj.getItems());
		paquetePersonaje = juego.getCli().getPaquetePersonaje();
		paquetePersonaje.setComando(Comando.ACTUALIZARTRUEQUE);
		juego.getCli().setM1(null);
		try {
			juego.getCli().getSal().writeObject(gson.toJson(paquetePersonaje));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al actualizar trueque");

		}

	}

}
