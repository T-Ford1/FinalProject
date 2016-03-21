package components;

import graphics.Sprite;

public class MessageBar extends DefaultComponent {
	
	private int extra;
	
	public MessageBar(int x, int y, int length) {
		super(x, y, Sprite.MESSAGEBAR);
		extra = length - unpress.getWidth() * 2;
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
