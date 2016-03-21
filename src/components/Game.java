package components;

import java.awt.Dimension;

import graphics.Sprite;

public class Game extends GraphicsComponent {

    private final Dimension screen;

    public Game(Dimension s) {
        super(0, 0, Sprite.GAME);
        screen = s;
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
        int xOff = (sprite.getWidth() - screen.width) / 2;
        int yOff = (sprite.getHeight() - screen.height) / 2;
        for (int y = 0; y < screen.height; y++) {
            int yPos = y + yOff;
            if (yPos < 0 | yPos >= sprite.getHeight()) {
                continue;
            }
            for (int x = 0; x < screen.width; x++) {
                int xPos = x + xOff;
                if (xPos < 0 | xPos >= sprite.getWidth()) {
                    continue;
                }
                renderPixel(x, y, sprite.getPixel(xPos, yPos));
            }
        }
        render = false;
    }
}
