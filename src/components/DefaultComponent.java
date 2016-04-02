package components;

import frame.Window;
import graphics.Renderable;
import graphics.SpriteSheet;

public class DefaultComponent extends GraphicsComponent {

    public DefaultComponent(int x, int y, int w, int h, Renderable... s) {
        super(x, y, w, h, s);
    }

    public DefaultComponent(int x, int y, Renderable... s) {
        super(x, y, s);
    }

    public DefaultComponent(int x, int y, int index) {
        super(x, y, SpriteSheet.COMPONENTS.getSprite(index, 0), SpriteSheet.COMPONENTS.getSprite(index, 1), SpriteSheet.COMPONENTS.getSprite(index, 2));
    }

    public void update() {
        super.update();
        render = render | hovered | pressed;
        if (pressed) {
            onClick();
        }
        setHover(isInside(Window.mouse.getPoint()));
        setPressed(hovered && Window.mouse.isPressed());
    }

    public void render() {
        renderAll();
    }

    public void renderAll() {
        renderSprite();
        render = false;
    }

    protected void onClick() {
    }
}
