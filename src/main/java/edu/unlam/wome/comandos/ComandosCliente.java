package edu.unlam.wome.comandos;

import edu.unlam.wome.cliente.Cliente;
import edu.unlam.wome.mensajeria.Comando;

/**
 * Clase Comandos Cliente
 * @author Miguel
 */
public abstract class ComandosCliente extends Comando {
	private Cliente cliente;

	/**
	 * Setea el el cliente que se recibe por parametro
	 * @param cliente a setear
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Devuelve el cliente
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

}
