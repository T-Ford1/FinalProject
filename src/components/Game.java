package components;

import java.awt.Color;
import java.awt.Dimension;

import graphics.ColorSprite;

public class Game extends ComponentBranch {
	
	public Game(Dimension screen) {
		super(0, 72, new ColorSprite(Color.blue, screen.width, screen.height - 144));
		priority = 2;
	}
}
