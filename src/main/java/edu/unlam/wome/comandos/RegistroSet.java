package edu.unlam.wome.comandos;

import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase RegistroSet
 * @author Miguel
 */
public class RegistroSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		System.out.println("ERROR");
		this.getCliente().getPaqueteUsuario().setComando(Comando.REGISTRO);

	}

}
