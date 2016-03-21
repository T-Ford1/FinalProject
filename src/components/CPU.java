package components;

import graphics.Sprite;

public class CPU extends DefaultComponent {
	
	private final int extra;

	public CPU(int x, int y, int length) {
		super(x, y, Sprite.CPU);
		extra = length - 2 * unpress.getWidth();
	}

	public void update() {
		render = false;
	}
	
	public void renderAll() {
		renderSprite(unpress, getX(), getY());
		for(int y = 0; y < unpress.getHeight(); y++) {
        	for(int x = 0; x < extra; x++) {
        		renderPixel(getX() + x + unpress.getWidth(), getY() + y, unpress.getPixel(unpress.getWidth() - 1, y));
        	}
        }
		for(int y = 0; y < unpress.getHeight(); y++) {
        	for(int x = 0; x < unpress.getWidth(); x++) {
        		renderPixel(getX() + x + unpress.getWidth() + extra, getY() + y, unpress.getPixel(unpress.getWidth() - x - 1, y));
        	}
        }
	}
}
