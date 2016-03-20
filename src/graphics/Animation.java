package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Animation implements Renderable {

	private final Sprite[] sprites;

	private int index;

	public Animation(Sprite[] s, int i) {
		index = i;
		sprites = s;
	}

	public Animation(Sprite[] s) {
		this(s, 0);
	}

	public Animation(Sprite s) {
		this(new Sprite[] { s }, 0);
	}

	private Sprite next() {
		return sprites[(++index >= sprites.length ? 0 : index)];
	}

	private Sprite peek() {
		return sprites[index];
	}

	public int getPixel(int x, int y) {
		return peek().getPixel(x, y);
	}

	public int[] getPixels() {
		return peek().getPixels();
	}

	public void update() {
		peek().update();
		next();
	}

	public void setColor(Color c) {
		peek().setColor(c);
	}
	
	public void removeColor(Color c) {
		peek().removeColor(c);
	}

	public BufferedImage getImage() {
		return peek().getImage();
	}

	public int getHeight() {
		return peek().getHeight();
	}

	public int getWidth() {
		return peek().getWidth();
	}

	public void replaceColor(Color old, Color next) {
		peek().replaceColor(old, next);
	}
}
