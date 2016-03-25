package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author ford.terrell
 */
public abstract class Sprite implements Renderable {

    public static final Sprite RAINBOW = new ColorSprite("res/animations/rainbow.png");
    public static final Sprite GREEN_TO_BLACK = new ColorSprite("res/animations/greentoblack.png");
    public static final Sprite BLACK_TO_GREEN = new ColorSprite("res/animations/blacktogreen.png");
    public static final Sprite TAB = new ColorSprite("res/components/tabs.png");
    public static final Sprite GAME_STATIC = new ColorSprite("res/containers/game_static.png");
    public static final Sprite TOOLBAR = new ColorSprite("res/containers/toolbar.png");
    public static final Sprite MENUBAR = new ColorSprite("res/containers/menubar.png");
    public static final Sprite DEFAULT = new ColorSprite(Color.blue, 32, 32);

    private final int WIDTH, HEIGHT;
    private final int[] pixels;

    protected Sprite(int xOff, int yOff, int w, int h, BufferedImage img) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        img.getRGB(xOff, yOff, w, h, pixels, 0, w);
    }

    public Sprite(Color c, int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        setColor(c);
    }
    
    public Sprite(Renderable s) {
        WIDTH = s.getWidth();
        HEIGHT = s.getHeight();
        pixels = new int[s.getPixels().length];
        System.arraycopy(s.getPixels(), 0, pixels, 0, pixels.length);
    }

    public Sprite(String p) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(p));
        } catch (IOException e) {
            e.printStackTrace();
            img = DEFAULT.getImage();
        } finally {
            pixels = new int[(HEIGHT = img.getHeight()) * (WIDTH = img.getWidth())];
            img.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        }
    }

    public BufferedImage getImage() {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
        return img;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getPixel(int x, int y) {
        return pixels[y * WIDTH + x];
    }

    public final void setColor(Color c) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] != 0xFF_FF_00_FF ? c.getRGB() : pixels[i];
        }
    }

    public void removeColor(Color c) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] == c.getRGB() ? 0xFF_FF_00_FF : pixels[i];
        }
    }

    public void replaceColor(Color old, Color next) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixels[i] == old.getRGB() ? next.getRGB() : pixels[i];
        }
    }

    public abstract void update();

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }
}
