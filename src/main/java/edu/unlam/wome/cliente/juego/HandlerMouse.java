package edu.unlam.wome.cliente.juego;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Clase Manejo de Mouse
 *
 * @author lesanmartin
 *
 */
public class HandlerMouse implements MouseListener {

	private int[] posMouse;
	private int[] posMouseRecorrido;
	private boolean nuevoRecorrido;
	private boolean nuevoClick;

	/**
	 * Constructor de la clase
	 */
	public HandlerMouse() {
		posMouse = new int[2];
		posMouseRecorrido = new int[2];
	}

	@Override
	/**
	 * Evento click del mouse
	 */
	public void mouseClicked(final MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			posMouse[0] = e.getX();
			posMouse[1] = e.getY();
			nuevoClick = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			posMouseRecorrido[0] = e.getX();
			posMouseRecorrido[1] = e.getY();
			nuevoRecorrido = true;
		}
	}

	@Override
	/**
	 * Evento de Mouse cuando ingresa el foco
	 */
	public void mouseEntered(final MouseEvent arg0) {

	}

	@Override
	/**
	 * Evento de Mouse de perdida de foco
	 */
	public void mouseExited(final MouseEvent arg0) {

	}

	@Override
	/**
	 * Evento de presion de click de mouse
	 */
	public void mousePressed(final MouseEvent arg0) {

	}

	@Override
	/**
	 * Evento de soltar mouse
	 */
	public void mouseReleased(final MouseEvent arg0) {

	}

	/**
	 * Retorna la posicion del mouse
	 *
	 * @return posMouse
	 */
	public int[] getPosMouse() {
		return posMouse;
	}

	/**
	 * Retorna el recorrido del mouse
	 *
	 * @return posMouseRecorrido
	 */
	public int[] getPosMouseRecorrido() {
		return posMouseRecorrido;
	}

	/**
	 * Retorna el nuevo recorrido del mouse
	 *
	 * @return nuevoRecorrido
	 */
	public boolean getNuevoRecorrido() {
		return nuevoRecorrido;
	}

	/**
	 * Setea el nuevo recorrido del mouse
	 *
	 * @param b booleano
	 */
	public void setNuevoRecorrido(final boolean b) {
		nuevoRecorrido = b;
	}

	/**
	 * Retorna el nuevo click del mouse
	 *
	 * @return nuevoClick
	 */
	public boolean getNuevoClick() {
		return nuevoClick;
	}

	/**
	 * Setea el nuevo click del mouse
	 *
	 * @param b booleano
	 */
	public void setNuevoClick(final boolean b) {
		nuevoClick = b;
	}
}
