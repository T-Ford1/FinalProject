package components;

import java.awt.Dimension;

import graphics.Sprite;

public class ToolBar extends GraphicsComponent {
	
	private final DefaultComponent add;
	private final DefaultComponent remove;
	private final DefaultComponent upgrade;
	private final DefaultComponent music;
	private final DefaultComponent ips;
	
	public ToolBar(Dimension screen) {
		super(screen.width - Sprite.TOOLBAR.getWidth(), screen.height - Sprite.TOOLBAR.getHeight(), Sprite.TOOLBAR);
		add = new DefaultComponent(getX() + 5, getY() + 4, 4);
		remove = new DefaultComponent(getX() + 73, getY() + 4, 5);
		upgrade = new DefaultComponent(getX() + 142, getY() + 4, 6);
		music = new DefaultComponent(getX() + 211, getY() + 4, 7);
		ips = new DefaultComponent(getX() + 280, getY() + 4, 8);
		render = false;
	}

	public void update() {
		add.update();
		remove.update();
		upgrade.update();
		music.update();
		ips.update();
	}

	public void render() {
		if(render) {
			renderSprite();
			render = false;
		}
		add.render();
		remove.render();
		upgrade.render();
		music.render();
		ips.render();
	}

	public void renderAll() {
		renderSprite();
		add.renderAll();
		remove.renderAll();
		upgrade.renderAll();
		music.renderAll();
		ips.renderAll();
		render = false;
	}
}
