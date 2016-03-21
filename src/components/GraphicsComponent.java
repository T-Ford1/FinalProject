package components;

import java.awt.Rectangle;

import frame.Window;
import graphics.Renderable;
import java.awt.Point;

public abstract class GraphicsComponent implements Comparable<GraphicsComponent> {

    public Rectangle bounds;
    public Renderable press, unpress, hover;
    public boolean render;

    protected int priority;
    protected boolean pressed, hovered;

    public GraphicsComponent(int x, int y, int width, int height, Renderable u, Renderable p, Renderable h) {
        render = true;
        bounds = new Rectangle(x, y, width, height);
        priority = 1;
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

    public final void renderPixel(int xPos, int yPos, int spriteX, int spriteY) {
        int pixel = 0;
        if(pressed) {
            pixel = press.getPixel(spriteX, spriteY);
        }
        if (pixel == 0xFF_FF_00_FF) {
            return;
        }
        Window.renderPixel(x, y, pixel);
    }

    public final void renderBox(int xOff, int yOff, int width, int height, int pixel) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                renderPixel(xOff + x, yOff + y, pixel);
            }
        }
    }

    public void renderSprite(Renderable sprite, int xOff, int yOff) {
        for (int y = 0; y < sprite.getHeight(); y++) {
            for (int x = 0; x < sprite.getWidth(); x++) {
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

    public boolean isInside(Point mouse) {
        return bounds.contains(mouse);
    }

    public void onHover(Point mouse) {
        hovered = true;
    }

    public void onLeave(Point mouse) {
        hovered = false;
    }

    public void onPress(Point mouse) {
        pressed = true;
    }

    public void onRelease(Point mouse) {
        pressed = false;
    }

    public int compareTo(GraphicsComponent c) {
        return priority - c.priority;
    }
}
