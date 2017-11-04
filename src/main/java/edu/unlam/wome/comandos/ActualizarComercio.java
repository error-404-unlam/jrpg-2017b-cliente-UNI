package edu.unlam.wome.comandos;

import javax.swing.JOptionPane;

import edu.unlam.wome.dominio.main.Item;
import edu.unlam.wome.mensajeria.PaqueteComerciar;

/**
 * Comando que actualiza el comercio.
 * @author Miguel
 */
public class ActualizarComercio extends ComandosEscucha {

	private final int tamMax = 9;
	@Override
	public void ejecutar() {
		int sizeMisItems = this.getJuego().getCli().getM1().getSizeItems();
		int sizeADar = this.getJuego().getCli().getM1().getDar().size();
		int sizeAObtener;
		int cuentaSize;
		PaqueteComerciar paqueteComerciar;
		paqueteComerciar = getGson().fromJson(getCadenaLeida(), PaqueteComerciar.class);
		sizeAObtener = paqueteComerciar.getItemsADar().size();
		cuentaSize = sizeMisItems - sizeADar + sizeAObtener;
		if (sizeADar != 0) {
			if (cuentaSize <= tamMax) {
				this.getJuego().getCli().getM1().getChckbxListo().setEnabled(true);
				this.getJuego().getCli().getM1().getLeyenda().setVisible(false);
			} else if (cuentaSize > tamMax) {
				this.getJuego().getCli().getM1().getChckbxListo().setEnabled(false);
				this.getJuego().getCli().getM1().getLeyenda().setVisible(true);
			}
		}
		if (sizeAObtener == 0) {
			this.getJuego().getCli().getM1().getChckbxListo().setEnabled(false);
			this.getJuego().getCli().getM1().getLeyenda().setVisible(true);
		}
		if (this.getJuego().getCli().getPaqueteComercio().getListo() == paqueteComerciar.getListo()) {
			// actualizar la lista
			this.getJuego().getCli().getM1().getObtener().removeAllElements();
			for (Item item : paqueteComerciar.getItemsADar()) {
				this.getJuego().getCli().getM1().getObtener().addElement(item.getNombre());
			}
			this.getJuego().getCli().getPaqueteComercio().setItemsAObtener(paqueteComerciar.getItemsADar());
		} else {
			// se modifico el listo
			// me fijo si puso listo o lo saco
			if (this.getJuego().getCli().getPaqueteComercio().getListo() < paqueteComerciar.getListo()) {
				this.getJuego().getCli().getPaqueteComercio().aumentarListo();
			} else {
				this.getJuego().getCli().getPaqueteComercio().disminuirListo();
			}
			// modifico la cant de listos en el jframe y tambien el lbl
			this.getJuego().getCli().getM1().setCantListos(paqueteComerciar.getListo());
			this.getJuego().getCli().getM1().getCantListo().setText(
					String.valueOf(this.getJuego().getCli().getM1().getCantListos()) + "/2");
			if (this.getJuego().getCli().getM1().getCantListos() == 2) {
				JOptionPane.showMessageDialog(this.getJuego().getCli().getM1(),
						"Se ha realizado con exito el comercio");
				this.getJuego().getCli().getM1().dispose();
			}
		}
	}

}
