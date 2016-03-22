package components;

import java.awt.Dimension;

import graphics.Sprite;
import graphics.SpriteSheet;

public class MenuBar extends GraphicsComponent {

    private final DefaultComponent setting;
    private final DefaultComponent profile;
    private final DefaultComponent clan;
    private final DefaultComponent shield;
    private final DefaultComponent cpu;
    private final DefaultComponent mBar;
    private final DefaultComponent temp;

    public MenuBar(Dimension screen) {
        super(0, 0, Sprite.MENUBAR);
        SpriteSheet sprites = SpriteSheet.COMPONENTS;
        setting = new DefaultComponent(5, 4, sprites.getSprite(0, 0), sprites.getSprite(0, 1), sprites.getSprite(0, 2));
        profile = new DefaultComponent(74, 4, sprites.getSprite(1, 0), sprites.getSprite(1, 1), sprites.getSprite(1, 2));
        clan = new DefaultComponent(143, 4, sprites.getSprite(2, 0), sprites.getSprite(2, 1), sprites.getSprite(2, 2));
        shield = new DefaultComponent(212, 4, sprites.getSprite(3, 0), sprites.getSprite(3, 1), sprites.getSprite(3, 2));
        int cX = 278;
        int cL = Math.max(174, screen.width / 3 - 278);
        cpu = new Tab(Sprite.TAB, 278, 0, cL);
        int mX = cX + cL;
        int mL = Math.max(174, (screen.width - 278) / 2);
        mBar = new Tab(Sprite.TAB, mX, 0, mL);
        int tX = mX + mL;
        temp = new Tab(Sprite.TAB, tX, 0, Math.max(174, screen.width - tX));
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
        if (render) {
            renderSprite();
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
        renderSprite();
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
