package components;

import graphics.Sprite;

public class Add extends DefaultComponent {

	public Add(int x, int y) {
		super(x, y, Sprite.ADD);
	}

	public void update() {
		render = false;
	}
}
