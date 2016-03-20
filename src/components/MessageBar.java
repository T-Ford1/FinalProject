package components;

import graphics.Sprite;

public class MessageBar extends GraphicsComponent {
	
	private int extra;
	
	public MessageBar(int x, int y, int length) {
		super(x, y, Sprite.MESSAGEBAR);
		extra = length - sprite.getWidth() * 2;
	}

	public void update() {
		render = false;
	}

	public void render() {
		if(render) renderAll();
	}

	public void renderAll() {
		renderSprite(sprite, getX(), getY());
		for(int y = 0; y < sprite.getHeight(); y++) {
        	for(int x = 0; x < extra; x++) {
        		renderPixel(getX() + x + sprite.getWidth(), getY() + y, sprite.getPixel(sprite.getWidth() - 1, y));
        	}
        }
		for(int y = 0; y < sprite.getHeight(); y++) {
        	for(int x = 0; x < sprite.getWidth(); x++) {
        		renderPixel(getX() + x + sprite.getWidth() + extra, getY() + y, sprite.getPixel(sprite.getWidth() - x - 1, y));
        	}
        }
	}
}
