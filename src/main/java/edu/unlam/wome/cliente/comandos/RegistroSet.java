package edu.unlam.wome.cliente.comandos;

import comandos.ComandosCliente;
import edu.unlam.wome.cliente.mensajeria.Comando;

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
