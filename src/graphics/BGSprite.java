package graphics;

/**
 *
 * @author ford.terrell
 */
public class BGSprite extends ColorSprite {
    
    private int updates;
    private boolean renderedLastFrame;
    private final int x, y;

    public BGSprite(Renderable r, int rndr, int x, int y) {
        super(r);
        updates = rndr;
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
}