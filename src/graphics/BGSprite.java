package graphics;

import components.Background;

/**
 *
 * @author ford.terrell
 */
public class BGSprite extends ColorSprite {

    private boolean renderedLastFrame;
    private int x, y, updates, index;

    public BGSprite(Background b, Renderable r, int u, int xPos, int yPos) {
        super(r);
        updates = u - 1;
        index = 0;
        x = xPos;
        y = yPos;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int pixel = b.getPixel(x + getX(), y + getY());
                if(pixel == 0xFF_00_00_00 | pixel == 0xFF_30_BB_2D) {
                } else {
                    pixels[y * WIDTH + x] = 0xFF_FF_00_FF;
                }
            }
        }
    }
    
    public void update() {
        super.update();
        index = ++index > updates ? updates : index;
    }
    
    public void updateRender() {
        renderedLastFrame = index == updates;
    }
    
    public boolean renderedLast() {
        return renderedLastFrame;
    }
    
    public final int getX() {
        return x;
    }
    
    public final int getY() {
        return y;
    }
    
    public BGSprite copyOf(Background b) {
    	return new BGSprite(b, this, updates, x, y);
    }
    
    public BGSprite setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public int getPixel(int x, int y) {
        return schemes.get(pixels[y * WIDTH + x], index);
    }
}