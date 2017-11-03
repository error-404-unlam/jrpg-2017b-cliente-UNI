package edu.unlam.wome.cliente.comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import comandos.ComandosEscucha;
import edu.unlam.wome.cliente.frames.MenuComerciar;
import edu.unlam.wome.cliente.mensajeria.Paquete;
import edu.unlam.wome.cliente.mensajeria.PaqueteComerciar;

/**
 * Clase Comercio. Comercia entre Personajes.
 * @author Miguel
 */
public class Comercio extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		paqueteComerciar = getGson().fromJson(getCadenaLeida(), PaqueteComerciar.class);
		// Cuando recibo el paquete de comercio actualizado intercambio user/
		// destino
		paqueteComerciar.setIdEnemigo(paqueteComerciar.getId());
		paqueteComerciar.setId(this.getJuego().getCli().getPaquetePersonaje().getId());

		if (paqueteComerciar.isSolicitudDeComercio()) {
			if (this.getJuego().getCli().getM1() != null) {
				paqueteComerciar.setMensaje(Paquete.getMsjFracaso());
			} else {
				this.getJuego().getCli().setPaqueteComercio(paqueteComerciar);
				this.getJuego().getCli().setM1(new MenuComerciar(this.getJuego().getCli()));
				this.getJuego().getCli().getM1().setVisible(true);
				paqueteComerciar.setMensaje(Paquete.getMsjExito());
			}
			paqueteComerciar.setSolicitudDeComercio(false);
			try {
				this.getJuego().getCli().getSal().writeObject(getGson().toJson(paqueteComerciar));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "No se envio la solicitud de comercio");
			}

		} else {
			if (paqueteComerciar.getMensaje().equals(Paquete.getMsjFracaso())) {
				JOptionPane.showMessageDialog(null, "Ya esta comerciando");
			} else {
				if (this.getJuego().getCli().getM1() == null) {
					this.getJuego().getCli().setPaqueteComercio(paqueteComerciar);
					this.getJuego().getCli().setM1(new MenuComerciar(this.getJuego().getCli()));
					this.getJuego().getCli().getM1().setVisible(true);
				}
			}
		}

	}

}
