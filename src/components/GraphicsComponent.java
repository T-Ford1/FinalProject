package components;

import java.awt.Rectangle;

import frame.Window;
import graphics.Renderable;

public abstract class GraphicsComponent implements Comparable<GraphicsComponent> {

	public Rectangle bounds;
	public Renderable sprite;
	public boolean render;
	
	protected int priority;

	public GraphicsComponent(int x, int y, Renderable s) {
		render = true;
		bounds = new Rectangle(x, y, s.getWidth(), s.getHeight());
		priority = 1;
		sprite = s;
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public abstract void renderAll();
	
	public static final void renderPixel(int x, int y, int pixel) {
		if(pixel == 0xFF_FF_00_FF) {
			return;
		}
		Window.renderPixel(x, y, pixel);
	}
	
	public static final void renderBox(int xOff, int yOff, int width, int height, int pixel) {
		for(int y = 0; y < height; y++) {
        	for(int x = 0; x < width; x++) {
        		renderPixel(xOff + x, yOff + y, pixel);
        	}
        }
	}
	
	public static void renderSprite(Renderable sprite, int xOff, int yOff) {
		for(int y = 0; y < sprite.getHeight(); y++) {
        	for(int x = 0; x < sprite.getWidth(); x++) {
        		renderPixel(xOff + x, yOff + y, sprite.getPixel(x, y));
        	}
        }
	}
	
	public int getX() {
		return bounds.x;
	}
	
	public int getY() {
		return bounds.y;
	}
	
	public int getWidth() {
		return bounds.width;
	}
	
	public int getHeight() {
		return bounds.height;
	}
	
	public int compareTo(GraphicsComponent c) {
		return priority - c.priority;
	}
}