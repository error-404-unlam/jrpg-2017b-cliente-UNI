package comandos;

import javax.swing.JOptionPane;

import dominio.Item;
import mensajeria.PaqueteComerciar;

/**
 * Comando que actualiza el comercio.
 * @author Miguel
 */
public class ActualizarComercio extends ComandosEscucha {

	private final int tamMax = 9;
	@Override
	public void ejecutar() {
		int sizeMisItems = juego.getCli().getM1().getSizeItems();
		int sizeADar = juego.getCli().getM1().getDar().size();
		int sizeAObtener;
		int cuentaSize;
		PaqueteComerciar paqueteComerciar;
		paqueteComerciar = gson.fromJson(cadenaLeida, PaqueteComerciar.class);
		sizeAObtener = paqueteComerciar.getItemsADar().size();
		cuentaSize = sizeMisItems - sizeADar + sizeAObtener;
		if (sizeADar != 0) {
			if (cuentaSize <= tamMax) {
				juego.getCli().getM1().getChckbxListo().setEnabled(true);
				juego.getCli().getM1().getLeyenda().setVisible(false);
			} else if (cuentaSize > tamMax) {
				juego.getCli().getM1().getChckbxListo().setEnabled(false);
				juego.getCli().getM1().getLeyenda().setVisible(true);
			}
		}
		if (sizeAObtener == 0) {
			juego.getCli().getM1().getChckbxListo().setEnabled(false);
			juego.getCli().getM1().getLeyenda().setVisible(true);
		}
		if (juego.getCli().getPaqueteComercio().getListo() == paqueteComerciar.getListo()) {
			// actualizar la lista
			juego.getCli().getM1().getObtener().removeAllElements();
			for (Item item : paqueteComerciar.getItemsADar()) {
				juego.getCli().getM1().getObtener().addElement(item.getNombre());
			}
			juego.getCli().getPaqueteComercio().setItemsAObtener(paqueteComerciar.getItemsADar());
		} else {
			// se modifico el listo
			// me fijo si puso listo o lo saco
			if (juego.getCli().getPaqueteComercio().getListo() < paqueteComerciar.getListo()) {
				juego.getCli().getPaqueteComercio().aumentarListo();
			} else {
				juego.getCli().getPaqueteComercio().disminuirListo();
			}
			// modifico la cant de listos en el jframe y tambien el lbl
			juego.getCli().getM1().setCantListos(paqueteComerciar.getListo());
			juego.getCli().getM1().getCantListo().setText(
					String.valueOf(juego.getCli().getM1().getCantListos()) + "/2");
			if (juego.getCli().getM1().getCantListos() == 2) {
				JOptionPane.showMessageDialog(juego.getCli().getM1(),
						"Se ha realizado con exito el comercio");
				juego.getCli().getM1().dispose();
			}
		}
	}

}
