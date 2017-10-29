package comandos;

import mensajeria.Comando;

public class InicioSesionSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		this.getCliente().getPaqueteUsuario().setComando(Comando.INICIOSESION);
	}

}
