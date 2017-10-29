package comandos;

import mensajeria.Comando;

/**
 * Clase RegistroSet
 * @author Miguel
 */
public class RegistroSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		this.getCliente().getPaqueteUsuario().setComando(Comando.REGISTRO);

	}

}
