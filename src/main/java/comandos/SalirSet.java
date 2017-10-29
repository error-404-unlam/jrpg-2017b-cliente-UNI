package comandos;

import mensajeria.Comando;

public class SalirSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		this.getCliente().getPaqueteUsuario().setIp(this.getCliente().getMiIp());
		this.getCliente().getPaqueteUsuario().setComando(Comando.SALIR);
	}

}
