package graphics;

import java.awt.Color;
import java.util.HashMap;

public class ColorScheme {

	private final HashMap<Color, ColorFader> schemes;

	public ColorScheme() {
		schemes = new HashMap<>();
	}

	public void put(Color color, ColorFader fade) {
		put(color, fade);
	}
	
	public Color next(Color color) {
		ColorFader c = schemes.get(color);
		return c == null ? color : c.next();
	}
	
	public Color peek(Color color) {
		ColorFader c = schemes.get(color);
		return c == null ? color : c.peek();
	}
	
	protected HashMap<Color, ColorFader> getSchemes() {
		return schemes;
	}
}
