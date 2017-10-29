package comandos;

import juego.Juego;
import mensajeria.Comando;


public abstract class ComandosEscucha extends Comando {
	private Juego juego;

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Juego getJuego() {
		return juego;
	}

}
