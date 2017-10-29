package comandos;

import cliente.Cliente;
import mensajeria.Comando;

public abstract class ComandosCliente extends Comando {
	private Cliente cliente;

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

}
