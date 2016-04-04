package frame;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

import components.*;
import input.*;
import java.awt.Rectangle;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

/**
 *
 * @author ford.terrell
 */
public class Window extends Canvas {

    private static final long serialVersionUID = 1L;

    private static ArrayList<GraphicsComponent> components;

    public static Keyboard keys;
    public static Mouse mouse;

    private static BufferedImage image;
    private static int[] pixels;

    public Window() {
        addKeyListener(keys = new Keyboard());
        addMouseListener(mouse = new Mouse());
        addMouseMotionListener(mouse);
        components = new ArrayList<>();
    }

    public void init() {
        image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        pixels = new int[image.getWidth() * image.getHeight()];
        pixels = ((DataBufferInt) (image.getRaster().getDataBuffer())).getData();
        renderAll();
    }

    public static void renderPixel(int x, int y, int rgb) {
        renderPixel(x + y * image.getWidth(), rgb);
    }

    public static void renderPixel(int index, int rgb) {
        if (rgb == 0xFF_FF_00_FF) {
            return;
        }
        pixels[index] = rgb;
    }

    public static void renderArray(Rectangle img, int[] p) {
        int xStart = img.x < 0 ? -img.x : 0;
        //int xStart = (img.x < 0 ? -img.x : 0) + (img.x < bounds.x ? bounds.x - img.x : 0);
        int yStart = img.y < 0 ? -img.y : 0;
        int xMax = img.x + img.width >= image.getWidth() ? image.getWidth() -img.x : img.width;
        int yMax = img.y + img.height >= image.getHeight() ? image.getHeight() -img.y : img.height;
        int xSkip = xStart + img.width - xMax;
        int wSkip = image.getWidth() - img.width + xSkip;
        int index1 = (yStart + img.y) * image.getWidth() + (xStart + img.x), index2 = yStart * img.width + xStart;
        for (int y = yStart; y < yMax; y++, index1 += wSkip, index2 += xSkip) {
            for (int x = xStart; x < xMax; x++, index1++, index2++) {
                renderPixel(index1, p[index2]);
            }
        }
    }

    public static int getPixel(int x, int y) {
        return pixels[x + y * image.getWidth()];
    }

    public static void addComponent(GraphicsComponent g) {
        components.add(g);
    }

    public static void removeComponent(GraphicsComponent g) {
        components.remove(g);
    }

    public static void removeAll() {
        for (int i = 0; i < components.size(); i++) {
            components.remove(i--);
        }
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xFF_00_00_00;
        }
    }

    protected static BufferedImage getImage() {
        return image;
    }

    public void update() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update();
        }
        keys.update();
        mouse.update();
    }

    public void render() {
        for (GraphicsComponent c : components) {
            if (c.isRender() | c.isAlwaysRender()) {
                c.render();
            }
        }
    }

    public void renderAll() {
        for (GraphicsComponent c : components) {
            c.renderAll();
        }
    }
}
