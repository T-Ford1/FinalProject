
package components.title;

import components.GraphicsComponent;
import graphics.Sprite;
import graphics.SpriteSheet;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author ford.terrell
 */
public class Title extends GraphicsComponent {
    
    private int update;
    
    public Title(Dimension size) {
        super(0, 0, size.width, size.height, new Sprite(Color.black, size.width, size.height));
        String[] names = {"Play", "Options", "Credits", "Help", "Exit"};
        int height = 70;
        int x = (size.width - 201) / 2;
        int y = (size.height - height * names.length) / 2;
        new Play(names[0], x, y + 0 * height);
        new Button(names[1], x, y + 1 * height);
        new Button(names[2], x, y + 2 * height);
        new Button(names[3], x, y + 3 * height);
        new Exit(names[4], x, y + 4 * height);
    }

    public void update() {
        update++;
        if(update % 5 == 0) {
            new TNumber(bounds, SpriteSheet.NUMBER_64.getSprite(random.nextInt(10), 0), random.nextDouble() * 2);
        }
    }

    public void render() {
        renderAll();
    }

    public void renderAll() {
        renderSprite(0, 0, renderable[0]);
    }
}
