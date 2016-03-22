package components;

import graphics.Renderable;

public class Tab extends DefaultComponent {
	private int extra;

	public Tab(Renderable r, int x, int y, int length) {
		super(x, y, r);
		extra = length - getWidth() * 2;
	}

	public void renderAll() {
		renderSprite();
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < extra; x++) {
				renderPixel(x + getWidth(), y, getWidth() - 1, y);
			}
		}
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				renderPixel(x + getWidth() + extra, y, getWidth() - x - 1, y);
			}
		}
	}
}
