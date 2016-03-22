package components;

import java.awt.Dimension;

import graphics.Renderable;
import graphics.Sprite;

public class Game extends GraphicsComponent {

	public Game(Dimension s) {
		super(0, 0, s.width, s.height, Sprite.GAME, Sprite.GAME, Sprite.GAME);
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
		Renderable r = getRenderable();
		int xOff = (r.getWidth() - getWidth()) / 2;
		int yOff = (r.getHeight() - getHeight()) / 2;
		for (int y = 0; y < getHeight(); y++) {
			int yPos = y + yOff;
			if (yPos < 0 | yPos >= r.getHeight()) {
				continue;
			}
			for (int x = 0; x < getWidth(); x++) {
				int xPos = x + xOff;
				if (xPos < 0 | xPos >= r.getWidth()) {
					continue;
				}
				renderPixel(x, y, r.getPixel(xPos, yPos));
			}
		}
		render = false;
	}
}
