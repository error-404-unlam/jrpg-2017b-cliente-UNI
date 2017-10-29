package comandos;

import mensajeria.Comando;

/**
 * Clase InicioSesionSet. Inicia la sesion.
 * @author Miguel
 */
public class InicioSesionSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		this.getCliente().getPaqueteUsuario().setComando(Comando.INICIOSESION);
	}

}
