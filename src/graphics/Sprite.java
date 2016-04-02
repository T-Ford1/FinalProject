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
public class Sprite implements Renderable {

    public static final Sprite RAINBOW = new ColorSprite("res/animations/rainbow.png");
    public static final Sprite TAB = new ColorSprite("res/components/tab1.png");
    public static final Sprite TAB2 = new ColorSprite("res/components/tab2.png");
    public static final Sprite TAB3 = new ColorSprite("res/components/tab3.png");
    public static final Sprite GAME_STATIC = new ColorSprite("res/containers/game_static.png");
    public static final Sprite TOOLBAR = new ColorSprite("res/containers/toolbar.png");
    public static final Sprite MENUBAR = new ColorSprite("res/containers/menubar.png");
    public static final Sprite DEFAULT = new ColorSprite(Color.blue, 32, 32);

    protected final int WIDTH, HEIGHT;
    protected final int[] pixels;

    /**
     * creates a sprite stretched or shrinked to the width and height required
     * from the Renderable r
     *
     * @param xOff the x position that the sprite starts in img
     * @param yOff the y position the sprite starts in img
     * @param w width
     * @param h height
     * @param img the image file used
     */
    protected Sprite(int xOff, int yOff, int w, int h, BufferedImage img) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        img.getRGB(xOff, yOff, w, h, pixels, 0, w);
    }

    /**
     * creates a sprite stretch or shrink to the width and height required
     * from the Renderable r
     *
     * @param w width
     * @param h height
     * @param r the original sprite
     */
    public Sprite(int w, int h, Renderable r) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        int index = 0;
        for (int y = 0; y < h; y++) {
            int yPos = (int) (((double) y / h) * r.getHeight());
            for (int x = 0; x < w; x++) {
                int xPos = (int) (((double) x / w) * r.getWidth());
                pixels[index++] = r.getPixel(xPos, yPos);
            }
        }
    }

    /**
     * a sprite colored totally c and w wide and h high
     *
     * @param c color
     * @param w width
     * @param h height
     */
    public Sprite(Color c, int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        setColor(c);
    }

    /**
     * a copy of s
     *
     * @param s Renderable subclass
     */
    public Sprite(Renderable s) {
        WIDTH = s.getWidth();
        HEIGHT = s.getHeight();
        pixels = new int[s.getPixels().length];
        System.arraycopy(s.getPixels(), 0, pixels, 0, pixels.length);
    }

    /**
     * a sprite which takes up the entire image file
     *
     * @param p a path to a file
     */
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

    public Sprite(int[] p, int w) {
        WIDTH = w;
        HEIGHT = p.length / w;
        pixels = new int[WIDTH * HEIGHT];
        System.arraycopy(p, 0, pixels, 0, pixels.length);
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
    
    public void setPixel(int x, int y, int rgb) {
        pixels[y * WIDTH + x] = rgb;
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

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public void update() {
    }

    public Sprite copyOf() {
        return new Sprite(this);
    }
}
