package components.game;

import components.GraphicsComponent;
import graphics.Sprite;
import java.awt.Dimension;

/**
 *
 * @author ford.terrell
 */
public class Game extends GraphicsComponent {
    
    public Game(Dimension s) {
        super(0, 72, s.width, s.height - 72, Sprite.scaleSprite(Sprite.MOTHERBOARD, 0.23));
    }

    public void render() {
        renderAll();
    }

    public void renderAll() {
        renderSprite();
    }
}
