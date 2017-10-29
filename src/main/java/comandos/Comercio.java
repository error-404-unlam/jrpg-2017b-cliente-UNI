package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import frames.MenuComerciar;
import mensajeria.Paquete;
import mensajeria.PaqueteComerciar;

public class Comercio extends ComandosEscucha {

	@Override
	public void ejecutar() {
		PaqueteComerciar paqueteComerciar;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		// Cuando recibo el paquete de comercio actualizado intercambio user/
		// destino
		paqueteComerciar.setIdEnemigo(paqueteComerciar.getId());
		paqueteComerciar.setId(juego.getCli().getPaquetePersonaje().getId());

		if (paqueteComerciar.isSolicitudDeComercio()) {
			if (juego.getCli().getM1() != null) {
				paqueteComerciar.setMensaje(Paquete.msjFracaso);
			} else {
				juego.getCli().setPaqueteComercio(paqueteComerciar);
				juego.getCli().setM1(new MenuComerciar(juego.getCli()));
				juego.getCli().getM1().setVisible(true);
				paqueteComerciar.setMensaje(Paquete.msjExito);
			}
			paqueteComerciar.setSolicitudDeComercio(false);
			try {
				juego.getCli().getSal().writeObject(gson.toJson(paqueteComerciar));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "No se envio la solicitud de comercio");
			}

		} else {
			if (paqueteComerciar.getMensaje().equals(Paquete.msjFracaso)) {
				JOptionPane.showMessageDialog(null, "Ya esta comerciando");
			} else {
				if (juego.getCli().getM1() == null) {
					juego.getCli().setPaqueteComercio(paqueteComerciar);
					juego.getCli().setM1(new MenuComerciar(juego.getCli()));
					juego.getCli().getM1().setVisible(true);
				}
			}
		}

	}

}
