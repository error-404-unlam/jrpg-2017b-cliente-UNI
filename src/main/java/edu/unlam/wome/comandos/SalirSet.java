package edu.unlam.wome.comandos;

import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase SalirSet.
 * @author Miguel
 */
public class SalirSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		this.getCliente().getPaqueteUsuario().setIp(this.getCliente().getMiIp());
		this.getCliente().getPaqueteUsuario().setComando(Comando.SALIR);
	}

}
