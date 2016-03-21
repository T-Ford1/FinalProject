package components;

import graphics.Sprite;

public class Music extends DefaultComponent {

	public Music(int x, int y) {
		super(x, y, Sprite.MUSIC);
	}

	public void update() {
		render = false;
	}
}
