package components.game;

import components.DefaultComponent;
import graphics.Sprite;

public class Tab extends DefaultComponent {

    public Tab(int xPos, int yPos, int w, int h) {
        super(xPos, yPos, w, h, Sprite.TAB, Sprite.TAB2, Sprite.TAB3);
        int width = getWidth() - renderable[0].getWidth() - renderable[2].getWidth();
        if (width != 0) {
            renderable[1] = new Sprite(width, renderable[1].getHeight(), renderable[1]);
        }
    }

    public void update() {
    }

    public void renderAll() {
        renderSprite(0, 0, renderable[0]);
        renderSprite(renderable[0].getWidth(), 0, renderable[1]);
        renderSprite(getWidth() - renderable[2].getWidth(), 0, renderable[2]);
        render = false;
    }
}
