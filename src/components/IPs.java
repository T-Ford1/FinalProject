package components;

import graphics.Sprite;

public class IPs extends DefaultComponent {

	public IPs(int x, int y) {
		super(x, y, Sprite.IPS);
	}

	public void update() {
		render = false;
	}
}
