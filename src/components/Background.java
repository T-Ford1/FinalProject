package components;

import java.awt.Dimension;

import graphics.Sprite;
import graphics.SpriteSheet;

public class Background extends GraphicsComponent {

    private final Type type;
    private boolean[][] numbers;

    public Background(Dimension s, Type t) {
        super(0, 0, s.width, s.height, SpriteSheet.GAME.getSprite(0,0), SpriteSheet.GAME.getSprite(1,0), Sprite.GAME_STATIC);
        type = t;
        
        if(type == Type.MOVING | type == Type.SHIFTING) {
            int wd = s.width / unpress.getWidth() + 1;
            int he = s.height / unpress.getHeight() + 1;
            numbers = new boolean[wd][he];
            for (int y = 0; y < numbers.length; y++) {
                for (int x = 0; x < numbers[y].length; x++) {
                    numbers[y][x] = (y + x) % 2 == 0;
                }
            }
        } else {
            numbers = new boolean[0][0];
        }
    }

    public void update() {
        render = false;
    }

    public void render() {
        if (render) {
            renderAll();
        }
    }

    public void renderAll() {
        if (type == Type.STATIC) {
            int xOff = (press.getWidth() - getWidth()) / 2;
            int yOff = (press.getHeight() - getHeight()) / 2;
            for (int y = 0; y < getHeight(); y++) {
                int yPos = y + yOff;
                if (yPos < 0 | yPos >= press.getHeight()) {
                    continue;
                }
                for (int x = 0; x < getWidth(); x++) {
                    int xPos = x + xOff;
                    if (xPos < 0 | xPos >= press.getWidth()) {
                        continue;
                    }
                    renderPixel(x, y, press.getPixel(xPos, yPos));
                }
            }
        } else if(type == Type.SHIFTING) {
            
        }
        render = false;
    }
    
    private void renderStatic() {
        
    }
}
