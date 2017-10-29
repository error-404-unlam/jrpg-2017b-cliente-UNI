package comandos;

import mensajeria.Comando;

public class RegistroSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		this.getCliente().getPaqueteUsuario().setComando(Comando.REGISTRO);

	}

}
