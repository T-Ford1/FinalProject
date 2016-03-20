package components;

import java.awt.Color;
import java.awt.Dimension;

import graphics.ColorSprite;

public class ToolBar extends ComponentBranch {
	
	public ToolBar(Dimension screen) {
		super(0, screen.height - 72, new ColorSprite(Color.gray, screen.width, 72));
		priority = 1;
	}
}
