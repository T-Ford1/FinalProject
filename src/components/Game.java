package components;

import java.awt.Dimension;

import graphics.Sprite;

public class Game extends GraphicsComponent {
	
	private Dimension screen;
	
	public Game(Dimension s) {
		super(0, 0, Sprite.GAME);
		screen = s;
	}

	public void update() {
		render = false;
	}

	public void render() {
		if(render) {
			renderAll();
		}
	}

	public void renderAll() {
		int xOff = (sprite.getWidth() - screen.width) / 2;
		int yOff = (sprite.getHeight() - screen.height) / 2;
		for(int y = 0; y < sprite.getHeight(); y++) {
			int yPos = y + yOff;
			if(yPos < 0 | yPos >= screen.height) continue;
			for(int x = 0; x < screen.width; x++) {
				int xPos = x + xOff;
				if(xPos < 0 | xPos >= screen.width) continue;
				super.renderPixel(xPos, yPos, sprite.getPixel(x, y));
			}
		}
		render = false;
	}
}
