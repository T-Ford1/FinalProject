package components;

import graphics.Sprite;

public class Remove extends DefaultComponent {

	public Remove(int x, int y) {
		super(x, y, Sprite.REMOVE);
	}

	public void update() {
		render = false;
	}
}
