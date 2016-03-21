package components;

import java.awt.Color;

import graphics.ColorFader;
import graphics.ColorSprite;
import graphics.Sprite;

public class Settings extends DefaultComponent {
	
	public Settings(int x, int y) {
		super(x, y, Sprite.SETTINGS);
		sprite.setColor(Color.black);
		((ColorSprite) sprite).addColorScheme(Color.black, new ColorFader(Sprite.RAINBOW));
		priority = 3;
	}

	public void update() {
		sprite.update();
		render = true;
	}
}
