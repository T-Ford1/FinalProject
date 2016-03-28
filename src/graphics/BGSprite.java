package graphics;

/**
 *
 * @author ford.terrell
 */
public class BGSprite extends ColorSprite {

    private boolean renderedLastFrame;
    private int x, y, updates, index;

    public BGSprite(Renderable r, int u, int x, int y) {
        super(r);
        updates = u;
        index = 0;
        this.x = x;
        this.y = y;
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
        return schemes.get(pixels[y * WIDTH + x], index);
    }
}