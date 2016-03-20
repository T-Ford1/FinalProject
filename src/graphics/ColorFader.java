package graphics;

import java.awt.Color;

public class ColorFader {
	
	private final int[] colors;
	private int index;
	
	public ColorFader(Sprite c, int i) {
		colors = c.getPixels();
		index = i;
	}
	
	public ColorFader(Sprite c) {
		this(c, 0);
	}
	
	public Color next() {
		return new Color(colors[(index = ++index >= colors.length ? 0 : index)]);
	}
	
	public Color peek() {
		return new Color(colors[index]);
	}
}
