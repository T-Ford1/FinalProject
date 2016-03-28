package graphics;

/**
 *
 * @author ford.terrell
 */
public class BGSprite extends ColorSprite {

    private boolean renderedLastFrame;
    private int x, y, updates;

    public BGSprite(Renderable r, int u, int x, int y) {
        super(r);
        updates = u;
        this.x = x;
        this.y = y;
    }
    
    public void update() {
        super.update();
        updates--;
    }
    
    public void updateRender() {
        renderedLastFrame = updates <= 1;
    }
    
    public boolean renderedLast() {
        return renderedLastFrame;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public BGSprite copyOf() {
    	return new BGSprite(this, updates, x, y);
    }
    
    public BGSprite setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }
    
    public int getPixel(int x, int y) {
        System.out.println("getpixel");
        return schemes.get(pixels[y * WIDTH + x], 0);
    }
}