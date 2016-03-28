package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface Renderable {
	
	public int getPixel(int x, int y);
	
	public int[] getPixels();
	
	public void update();
	
	public void setColor(Color c);
	
	public void removeColor(Color c);
	
	public void replaceColor(Color old, Color next);
	
	public int getHeight();
	
	public int getWidth();
	
	public BufferedImage getImage();
	
	public Renderable copyOf();
}
