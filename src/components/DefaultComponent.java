package components;

import graphics.Renderable;

public abstract class DefaultComponent extends GraphicsComponent {

	public DefaultComponent(int x, int y, Renderable s) {
		super(x, y, s);
	}

	public void render() {
		if(render) {
			renderAll();
		}
	}

	public void renderAll() {
		renderSprite(sprite, getX(), getY());
		render = false;
	}
}
