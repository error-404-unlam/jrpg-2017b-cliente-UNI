package comandos;

import chat.MiChat;
import chat.VentanaContactos;
import juego.Pantalla;
import mensajeria.PaqueteMensaje;

public class Talk extends ComandosEscucha {

	@Override
	public void ejecutar() {
		MiChat chat = null;
		String destino;
		juego.getCli().setPaqueteMensaje((PaqueteMensaje) gson.fromJson(cadenaLeida, PaqueteMensaje.class));
		if (!(juego.getCli().getPaqMsj().getUserReceptor() == null)) {
			if (!(juego.getChatsActivos().containsKey(juego.getCli().getPaqMsj().getUserEmisor()))) {
				chat = new MiChat(juego);

				chat.setTitle(juego.getCli().getPaqMsj().getUserEmisor());
				chat.setVisible(true);

				juego.getChatsActivos().put(juego.getCli().getPaqMsj().getUserEmisor(), chat);
			}
			destino = juego.getCli().getPaqMsj().getUserEmisor();
		} else {
			// ALL
			if (!juego.getChatsActivos().containsKey("Sala")) {
				chat = new MiChat(juego);

				chat.setTitle("Sala");
				chat.setVisible(true);

				juego.getChatsActivos().put("Sala", chat);
				if (Pantalla.ventContac != null) {
					VentanaContactos.getBotonMc().setEnabled(false);
				}
			}
			destino = "Sala";
		}
		juego.getChatsActivos().get(destino).getChat().append(juego.getCli().getPaqMsj().getUserEmisor() + ": " + juego.getCli().getPaqMsj().getMensaje() + "\n");
		juego.getChatsActivos().get(destino).getTexto().grabFocus();
	}
}
