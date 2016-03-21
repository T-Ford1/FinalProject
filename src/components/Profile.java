package components;

import graphics.Sprite;

public class Profile extends DefaultComponent {

	public Profile(int x, int y) {
		super(x, y, Sprite.PROFILE);
	}

	public void update() {
		render = false;
	}
}
