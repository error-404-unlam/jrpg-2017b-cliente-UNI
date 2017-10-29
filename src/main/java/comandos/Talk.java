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
		this.getJuego().getCli().setPaqueteMensaje((PaqueteMensaje) gson.fromJson(cadenaLeida, PaqueteMensaje.class));
		if (!(this.getJuego().getCli().getPaqMsj().getUserReceptor() == null)) {
			if (!(this.getJuego().getChatsActivos().containsKey(this.getJuego().getCli().getPaqMsj().getUserEmisor()))) {
				chat = new MiChat(this.getJuego());

				chat.setTitle(this.getJuego().getCli().getPaqMsj().getUserEmisor());
				chat.setVisible(true);

				this.getJuego().getChatsActivos().put(this.getJuego().getCli().getPaqMsj().getUserEmisor(), chat);
			}
			destino = this.getJuego().getCli().getPaqMsj().getUserEmisor();
		} else {
			// ALL
			if (!this.getJuego().getChatsActivos().containsKey("Sala")) {
				chat = new MiChat(this.getJuego());

				chat.setTitle("Sala");
				chat.setVisible(true);

				this.getJuego().getChatsActivos().put("Sala", chat);
				if (Pantalla.ventContac != null) {
					VentanaContactos.getBotonMc().setEnabled(false);
				}
			}
			destino = "Sala";
		}
		this.getJuego().getChatsActivos().get(destino).getChat().append(this.getJuego().getCli().getPaqMsj().getUserEmisor() + ": " + this.getJuego().getCli().getPaqMsj().getMensaje() + "\n");
		this.getJuego().getChatsActivos().get(destino).getTexto().grabFocus();
	}
}
