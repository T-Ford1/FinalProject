package frame;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import components.*;
import input.*;

/**
 *
 * @author ford.terrell
 */
public class Window extends Canvas {

    private static final long serialVersionUID = 1L;

    public static Game window;
    public static ToolBar icons;
    public static MenuBar tabs;

    protected boolean screen;

    public static Keyboard keys;
    public static Mouse mouse;

    private static BufferedImage image;

    public Window() {
        screen = true;
        addKeyListener(keys = new Keyboard());
        addMouseListener(mouse = new Mouse());
        addMouseMotionListener(mouse);
    }

    public void init() {
        tabs = new MenuBar(getSize());
        window = new Game(getSize());
        icons = new ToolBar(getSize());
        image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        renderAll();
    }

    public static void renderPixel(int x, int y, int rgb) {
        image.setRGB(x, y, rgb);
    }

    protected static BufferedImage getImage() {
        return image;
    }

    public void update() {
        tabs.update();
        window.update();
        icons.update();
        if (keys.isPressed(KeyEvent.VK_ESCAPE)) {
            screen = false;
        }
        keys.update();
        mouse.update();
    }

    public void render() {
        window.render();
        tabs.render();
        icons.render();
    }

    public void renderAll() {
        window.renderAll();
        tabs.renderAll();
        icons.renderAll();
    }
}
