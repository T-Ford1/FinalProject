package components;

import java.awt.Dimension;

import graphics.Sprite;

public class MenuBar extends DefaultComponent {

    public MenuBar(Dimension screen) {
        super(0, 0, Sprite.MENUBAR);
        new DefaultComponent(5, 4, 0);
        new DefaultComponent(74, 4, 1);
        new DefaultComponent(143, 4, 2);
        new DefaultComponent(212, 4, 3);
        int cX = 278;
        int cL = Math.max(174, screen.width / 3 - 278);
        new Tab(Sprite.TAB, 278, 0, cL);
        int mX = cX + cL;
        int mL = Math.max(174, (screen.width - 278) / 2);
        new Tab(Sprite.TAB, mX, 0, mL);
        int tX = mX + mL;
        new Tab(Sprite.TAB, tX, 0, Math.max(174, screen.width - tX));
    }

    public void update() {
    }
}
