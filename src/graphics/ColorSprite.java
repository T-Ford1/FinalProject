package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ColorSprite extends Sprite {
	
	private ColorScheme cs;
	
	protected ColorSprite(int x, int y, int w, int h, BufferedImage img) {
		super(x, y, w, h, img);
		cs = new ColorScheme();
	}

	public ColorSprite(String p) {
		super(p);
		cs = new ColorScheme();
	}
	
	public ColorSprite(Color c, int w, int h) {
		super(c, w, h);
		cs = new ColorScheme();
	}
	
	public int getPixel(int x, int y) {
		return cs.peek(new Color(super.getPixel(x, y))).getRGB();
	}
	
	public void update() {
		updateColorSchemes();
	}

	private void updateColorSchemes() {
		HashMap<Color, ColorFader> schemes = cs.getSchemes();
		for (ColorFader i : schemes.values()) {
			i.next();
		}
	}

	public void addColorScheme(Color color, ColorFader colors) {
		cs.getSchemes().put(color, colors);
	}
}
