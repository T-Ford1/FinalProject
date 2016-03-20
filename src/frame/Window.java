package frame;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import components.*;
import input.*;

/**
 *
 * @author ford.terrell
 */
public class Window extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private final ArrayList<ComponentBranch> components;
	
	protected boolean fullscreen;
	
	public static Keyboard keys;
	public static Mouse mouse;
	
	private static BufferedImage image;

	public Window() {
		fullscreen = true;
		components = new ArrayList<>();
		addKeyListener(keys = new Keyboard());
		addMouseListener(mouse = new Mouse());
		addMouseMotionListener(mouse);
	}
	
	public void init() {
		components.add(new MenuBar(getSize()));
		components.add(new Game(getSize()));
		components.add(new ToolBar(getSize()));
		Collections.sort(components);
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
		for(GraphicsComponent c : components) {
			c.update();
		}
		if(keys.isPressed(KeyEvent.VK_ESCAPE)) {
    		int i = JOptionPane.showConfirmDialog(null, "Disable Fullscreen?");
    		if(i == 0) {
    			fullscreen = false;
    		}
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