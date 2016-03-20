package components;

import java.awt.Dimension;

import graphics.Sprite;

public class MenuBar extends ComponentBranch {
	
	public MenuBar(Dimension screen) {
		super(0, 0, Sprite.MENUBAR);
		items.add(new Settings(2, 4));
		//items.add(new Settings(71, 4));
		//items.add(new Settings(140, 4));
		//items.add(new Settings(209, 4));
		int cpuX = 278;
		int cpuL = 300;
		items.add(new CPU(278, 0, cpuL));
		int mX = cpuX + cpuL;
		int mL = (screen.width - 278) / 3;
		items.add(new MessageBar(mX, 0, mL));
		int cputX = mX + mL;
		items.add(new CPUTemp(cputX, 0, screen.width - cputX));
		priority = 3;
	}
}