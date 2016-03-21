package components;

import graphics.Sprite;

public class Upgrade extends DefaultComponent {

	public Upgrade(int x, int y) {
		super(x, y, Sprite.UPGRADE);
	}

	public void update() {
		render = false;
	}
}
