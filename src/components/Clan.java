package components;

import graphics.Sprite;

public class Clan extends DefaultComponent {

	public Clan(int x, int y) {
		super(x, y, Sprite.CLAN);
	}

	public void update() {
		render = false;
	}

	public void render() {
		if(render) {
			renderAll();
		}
	}

	public void renderAll() {
		super.renderSprite(sprite, getX(), getY());
		render = false;
	}
}
