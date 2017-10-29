package comandos;

import cliente.Cliente;
import mensajeria.Comando;

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
	public void setCliente(Cliente cliente) {
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
