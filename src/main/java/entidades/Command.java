package entidades;

import java.awt.image.BufferedImage;

public interface Command {

	public int executeGetFrame();
	public BufferedImage executeGetFrameActual();
}
