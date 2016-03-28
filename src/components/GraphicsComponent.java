package components;

import java.awt.Rectangle;

import frame.Window;
import graphics.Renderable;
import java.awt.Point;
import java.util.Random;

public abstract class GraphicsComponent implements Comparable<GraphicsComponent> {

    protected Rectangle bounds;
    protected Renderable press, hover, unpress;
    protected int priority;
    protected boolean pressed, hovered, render, alwaysRender;
    protected static final Random random = new Random();

    public GraphicsComponent(int x, int y, int width, int height, Renderable u, Renderable h, Renderable p) {
    	Window.addComponent(this);
        render = true;
        alwaysRender = false;
        bounds = new Rectangle(x, y, width, height);
        priority = 1;
        press = p;
        hover = h;
        unpress = u;
        pressed = false;
        hovered = false;
    }
    
    public GraphicsComponent(int x, int y, Renderable u, Renderable p, Renderable h) {
        this(x, y, u.getWidth(), u.getHeight(), u, p, h);
    }
    
    public GraphicsComponent(int x, int y, Renderable r) {
        this(x, y, r, r, r);
    }

    public abstract void update();

    public abstract void render();

    public abstract void renderAll();

    protected final void renderPixel(int x, int y, int rX, int rY) {
        renderPixel(x, y, getRenderable().getPixel(rX, rY));
    }
    
    protected void renderPixel(int x, int y, int rgb) {
        if (rgb == 0xFF_FF_00_FF) {
            return;
        }
        Window.renderPixel(getX() + x, getY() + y, rgb);
    }

    protected void renderSprite() {
        renderSprite(getRenderable(), 0, 0);
    }
    
    protected void renderSprite(int xOff, int yOff) {
        renderSprite(getRenderable(), xOff, yOff);
    }
    
    protected void renderSprite(Renderable r, int xOff, int yOff) {
    	for (int y = 0; y < r.getHeight(); y++) {
            for (int x = 0; x < r.getWidth(); x++) {
                renderPixel(x + xOff, y + yOff, r.getPixel(x, y));
            }
        }
    }
    
    public void setAlwaysRender() {
    	alwaysRender = true;
    }
    
    public boolean isAlwaysRender() {
    	return alwaysRender;
    }
    
    protected Renderable getRenderable() {
    	return !hovered ? unpress : !pressed ? hover : press;
    }

    protected int getX() {
        return bounds.x;
    }

    protected int getY() {
        return bounds.y;
    }

    protected int getWidth() {
        return bounds.width;
    }

    protected int getHeight() {
        return bounds.height;
    }

    public boolean isInside(Point mouse) {
        return bounds.contains(mouse);
    }

    protected final void setHover(boolean h) {
        hovered = h;
    }

    protected final void setPressed(boolean p) {
        pressed = p;
    }

    public int compareTo(GraphicsComponent c) {
        return priority - c.priority;
    }
    
    public void setRender(boolean render) {
        render = true;
    }
}
