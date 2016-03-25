package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ColorSprite extends Sprite {

    protected final ColorScheme schemes;
    private int update;

    protected ColorSprite(int x, int y, int w, int h, BufferedImage img) {
        super(x, y, w, h, img);
        schemes = new ColorScheme();
        update = 0;
    }

    public ColorSprite(String p) {
        super(p);
        schemes = new ColorScheme();
        update = 0;
    }

    public ColorSprite(Renderable r) {
        super(r);
        schemes = new ColorScheme();
        if(r instanceof ColorSprite) {
            ColorSprite sprite = (ColorSprite) r;
            HashMap<Integer, ColorFader> map = sprite.schemes.getSchemes();
            map.keySet().stream().forEach((c) -> {
                addColorScheme(c, new ColorFader(map.get(c)));
            });
        }
    }

    public ColorSprite(Color c, int w, int h) {
        super(c, w, h);
        schemes = new ColorScheme();
    }

    public int getPixel(int x, int y) {
        return schemes.get(super.getPixel(x, y));
    }

    public void update() {
        schemes.update();
        update++;
    }

    public final void addColorScheme(int color, ColorFader colors) {
        schemes.getSchemes().put(color, colors);
    }
}
