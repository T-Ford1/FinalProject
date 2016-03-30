package components;

import java.awt.Color;

import graphics.ColorFader;
import graphics.ColorSprite;
import graphics.Sprite;

public class Settings extends DefaultComponent {

    public Settings(int x, int y, int index) {
        super(x, y, index);
        setAlwaysRender();
        ((ColorSprite) unpress).addColorScheme(Color.black.getRGB(), new ColorFader(Sprite.RAINBOW));
    }
}
