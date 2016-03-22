package components;

import java.awt.Dimension;

import graphics.Sprite;
import graphics.SpriteSheet;

public class ToolBar extends GraphicsComponent {
	
	private final DefaultComponent add;
	private final DefaultComponent remove;
	private final DefaultComponent upgrade;
	private final DefaultComponent music;
	private final DefaultComponent ips;
	
	public ToolBar(Dimension screen) {
		super(screen.width - Sprite.TOOLBAR.getWidth(), screen.height - Sprite.TOOLBAR.getHeight(), Sprite.TOOLBAR);
		SpriteSheet sprites = SpriteSheet.COMPONENTS;
		add = new DefaultComponent(getX() + 5, getY() + 4, sprites.getSprite(4, 0), sprites.getSprite(4, 1), sprites.getSprite(4, 2));
		remove = new DefaultComponent(getX() + 73, getY() + 4, sprites.getSprite(5, 0), sprites.getSprite(5, 1), sprites.getSprite(5, 2));
		upgrade = new DefaultComponent(getX() + 142, getY() + 4, sprites.getSprite(6, 0), sprites.getSprite(6, 1), sprites.getSprite(6, 2));
		music = new DefaultComponent(getX() + 211, getY() + 4, sprites.getSprite(7, 0), sprites.getSprite(7, 1), sprites.getSprite(7, 2));
		ips = new DefaultComponent(getX() + 280, getY() + 4, sprites.getSprite(8, 0), sprites.getSprite(8, 1), sprites.getSprite(8, 2));
	}

	public void update() {
		render = false;
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
