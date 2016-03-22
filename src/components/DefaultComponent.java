package components;

import graphics.Renderable;
import graphics.Sprite;

public class DefaultComponent extends GraphicsComponent {
	
	public DefaultComponent(int x, int y, int width, int height, Renderable u, Renderable p, Renderable h) {
		super(x, y, width, height, u, p, h);
	}
	
	public DefaultComponent(int x, int y, Renderable u, Renderable p, Renderable h) {
		this(x, y, u.getWidth(), u.getHeight(), u, p, h);
	}

	public DefaultComponent(int x, int y, Renderable s) {
		this(x, y, s, s, s);
	}
	
	public DefaultComponent(int x, int y) {
		this(x, y, Sprite.DEFAULT);
	}
	
	public DefaultComponent() {
		this(0, 0);
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
		renderSprite();
		render = false;
	}
}
