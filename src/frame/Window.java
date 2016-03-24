package frame;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import components.*;
import input.*;
import java.util.ArrayList;

/**
 *
 * @author ford.terrell
 */
public class Window extends Canvas {

    private static final long serialVersionUID = 1L;


    private static ArrayList<GraphicsComponent> components;
    protected boolean screen;

    public static Keyboard keys;
    public static Mouse mouse;

    private static BufferedImage image;

    public Window() {
        screen = true;
        addKeyListener(keys = new Keyboard());
        addMouseListener(mouse = new Mouse());
        addMouseMotionListener(mouse);
        components = new ArrayList<>();
    }

    public void init() {
    	new Background(getSize(), Type.SHIFTING);
        new MenuBar(getSize());
        new ToolBar(getSize());
        image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        renderAll();
    }

    public static void renderPixel(int x, int y, int rgb) {
        image.setRGB(x, y, rgb);
    }
    
    public static int getPixel(int x, int y) {
    	return image.getRGB(x, y);
    }
    
    public static void addComponent(GraphicsComponent g) {
    	components.add(g);
    }

    protected static BufferedImage getImage() {
        return image;
    }

    public void update() {
    	if (keys.isPressed(KeyEvent.VK_ESCAPE)) {
            screen = false;
        }
        for(GraphicsComponent c : components) {
        	c.update();
        }
        keys.update();
        mouse.update();
    }

    public void render() {
    	for(GraphicsComponent c : components) {
        	c.render();
        }
    }

    public void renderAll() {
    	for(GraphicsComponent c : components) {
        	c.renderAll();
        }
    }
}
