package components;

import java.awt.Dimension;

import graphics.Sprite;

public class ToolBar extends GraphicsComponent {
	
	private final Add add;
	private final Remove remove;
	private final Upgrade upgrade;
	private final Music music;
	private final IPs ips;
	
	public ToolBar(Dimension screen) {
		super(screen.width - Sprite.TOOLBAR.getWidth(), screen.height - Sprite.TOOLBAR.getHeight(), Sprite.TOOLBAR);
		add = new Add(getX() + 5, getY() + 4);
		remove = new Remove(getX() + 73, getY() + 4);
		upgrade = new Upgrade(getX() + 142, getY() + 4);
		music = new Music(getX() + 211, getY() + 4);
		ips = new IPs(getX() + 280, getY() + 4);
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
			renderSprite(unpress, getX(), getY());
			render = false;
		}
		add.render();
		remove.render();
		upgrade.render();
		music.render();
		ips.render();
	}

	public void renderAll() {
		renderSprite(unpress, getX(), getY());
		add.renderAll();
		remove.renderAll();
		upgrade.renderAll();
		music.renderAll();
		ips.renderAll();
		render = false;
	}
}
