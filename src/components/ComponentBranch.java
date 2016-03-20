package components;

import java.util.ArrayList;
import java.util.Collections;

import graphics.Renderable;

public class ComponentBranch extends GraphicsComponent {
	
	protected ArrayList<GraphicsComponent> items;

	public ComponentBranch(int x, int y, Renderable s) {
		super(x, y, s);
		items = new ArrayList<>();
	}

	public void update() {
		Collections.sort(items);
		for(GraphicsComponent c : items) {
			c.update();
		}
		render = false;
	}

	public void render() {
		if(render) {
			renderSprite(sprite, getX(), getY());
		}
		for(int i = 0; i < items.size(); i++) {
			items.get(i).render();
		}
		render = false;
	}

	public void renderAll() {
		renderSprite(sprite, getX(), getY());
		for(int i = 0; i < items.size(); i++) {
			items.get(i).renderAll();
		}
		render = false;
	}
}