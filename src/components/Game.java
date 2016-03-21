package components;

import java.awt.Dimension;

import graphics.Sprite;

public class Game extends GraphicsComponent {

    public Game(Dimension s) {
        super(0, 0, Sprite.GAME);
        bounds.setBounds(0, 0, s.width, s.height);
    }

    public void update() {
        render = false;
    }

    public void render() {
        if (render) {
            renderAll();
        }
    }

    public void renderAll() {
        int xOff = (unpress.getWidth() - bounds.width) / 2;
        int yOff = (unpress.getHeight() - bounds.height) / 2;
        for (int y = 0; y < bounds.height; y++) {
            int yPos = y + yOff;
            if (yPos < 0 | yPos >= unpress.getHeight()) {
                continue;
            }
            for (int x = 0; x < bounds.width; x++) {
                int xPos = x + xOff;
                if (xPos < 0 | xPos >= unpress.getWidth()) {
                    continue;
                }
                renderPixel(x, y, unpress.getPixel(xPos, yPos));
            }
        }
        render = false;
    }
}
