package components;

import graphics.Sprite;

public class Shield extends DefaultComponent {

	public Shield(int x, int y) {
		super(x, y, Sprite.SHIELD);
	}

	public void update() {
		render = false;
	}
}
