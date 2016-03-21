package components;

import java.awt.Dimension;

import graphics.Sprite;

public class MenuBar extends GraphicsComponent {
	
	private final Settings setting;
	private final Profile profile;
	private final Clan clan;
	private final Shield shield;
	private final CPU cpu;
	private final MessageBar mBar;
	private final Temperature temp;
	
	public MenuBar(Dimension screen) {
		super(0, 0, Sprite.MENUBAR);
		setting = new Settings(5, 4);
		profile = new Profile(73, 4);
		clan = new Clan(142, 4);
		shield = new Shield(211, 4);
		int cX = 278;
		int cL = screen.width / 3 - 278;
		cpu = new CPU(278, 0, cL);
		int mX = cX + cL;
		int mL = (screen.width - 278) / 2;
		mBar = new MessageBar(mX, 0, mL);
		int tX = mX + mL;
		temp = new Temperature(tX, 0, screen.width - tX);
	}

	public void update() {
		setting.update();
		profile.update();
		clan.update();
		shield.update();
		cpu.update();
		mBar.update();
		temp.update();
		render = false;
	}

	public void render() {
		if(render) {
			renderSprite(sprite, getX(), getY());
			render = false;
		}
		setting.render();
		profile.render();
		clan.render();
		shield.render();
		cpu.render();
		mBar.render();
		temp.render();
	}

	public void renderAll() {
		renderSprite(sprite, getX(), getY());
		setting.renderAll();
		profile.renderAll();
		clan.renderAll();
		shield.renderAll();
		cpu.renderAll();
		mBar.renderAll();
		temp.renderAll();
		render = false;
	}
}