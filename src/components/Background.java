package components;

import graphics.Renderable;
import java.awt.Dimension;

import graphics.Sprite;
import graphics.SpriteSheet;

import frame.Window;
import graphics.BGSprite;
import graphics.ColorFader;
import graphics.ColorSprite;
import java.awt.Color;
import java.util.ArrayList;

public class Background extends GraphicsComponent {
    
    private final ColorSprite zero_to_one, one_to_zero;

    private final Type type;
    private final boolean[][] numbers;
    private int update;
    public static ArrayList<BGSprite> renders;
    

    public Background(Dimension s, Type t) {
        super(0, 72, s.width, s.height, Sprite.GAME_STATIC, SpriteSheet.GAME.getSprite(0, 0), SpriteSheet.GAME.getSprite(1, 0));
        type = t;
        int wd = getWidth() / press.getWidth() + 1;
        int he = getHeight() / press.getHeight() + 1;
        numbers = new boolean[he][wd];
        renders = new ArrayList<>();
        for (int y = 0; y < numbers.length; y++) {
            for (int x = 0; x < numbers[y].length; x++) {
                numbers[y][x] = (y + x) % 2 == 0;
                renders.add(numbers[y][x] ? new BGSprite(hover, 1, x, y) : new BGSprite(press, 1, x, y));
            }
        }
        zero_to_one = new ColorSprite(SpriteSheet.DIGIT_SHIFT.getSprite(0, 0));
        one_to_zero = new ColorSprite(SpriteSheet.DIGIT_SHIFT.getSprite(1, 0));
        zero_to_one.addColorScheme(0xFF_00_00_00, new ColorFader(Sprite.BLACK_TO_GREEN));
        zero_to_one.addColorScheme(0xFF_30_BB_2D, new ColorFader(Sprite.GREEN_TO_BLACK));
        one_to_zero.addColorScheme(0xFF_00_00_00, new ColorFader(Sprite.BLACK_TO_GREEN));
        one_to_zero.addColorScheme(0xFF_30_BB_2D, new ColorFader(Sprite.GREEN_TO_BLACK));
        update = 0;
    }

    public void update() {
        press.update();
        hover.update();
        if (type != Type.STATIC & (++update % (random.nextInt(90) + 20) == 0)) {
            update = 0;
            int y = random.nextInt(numbers.length);
            int x = random.nextInt(numbers[y].length);
            numbers[y][x] = !numbers[y][x];
            renders.add(numbers[y][x] ? new BGSprite(zero_to_one, 51, x, y) : new BGSprite(one_to_zero, 51, x, y));
        }
        for (int i = 0; i < renders.size(); i++) {
            BGSprite s = renders.get(i);
            s.update();
            if(s.renderedLast()) {
                renders.remove(i--);
            }
        }
        render = !renders.isEmpty();
    }

    public void render() {
        if (render) {
            renderAll();
        }
    }

    public void renderAll() {
        if (type == Type.STATIC) {
            renderStatic();
        } else if (type == Type.SHIFTING) {
            renderShifting();
        } else {
            renderMoving();
        }
        render = false;
    }

    private void renderStatic() {
        int xOff = (unpress.getWidth() - getWidth()) / 2;
        int yOff = (unpress.getHeight() - getHeight()) / 2;
        for (int y = 0; y < getHeight(); y++) {
            int yPos = y + yOff;
            if (yPos < 0 | yPos >= unpress.getHeight()) {
                continue;
            }
            for (int x = 0; x < getWidth(); x++) {
                int xPos = x + xOff;
                if (xPos < 0 | xPos >= unpress.getWidth()) {
                    continue;
                }
                renderPixel(x, y, unpress.getPixel(xPos, yPos));
            }
        }
    }

    private void renderShifting() {
        int xOff = (numbers[0].length * 72 - getWidth()) / 2;
        int yOff = (numbers.length * 128 - getHeight()) / 2;
        renders.stream().forEach(s -> {
            int yPos = s.getY() * 128 - yOff;
            int xPos = s.getX() * 72 - xOff;
            if (!(yPos + 128 < 0 | yPos >= getHeight() | xPos + 72 < 0 | xPos >= getWidth())) {
                renderSprite(s, xPos, yPos);
            }
            s.updateRender();
        });
    }

    private void renderMoving() {
        renderShifting();
    }
    
    protected void renderSprite(Renderable r, int xOff, int yOff) {
        for (int y = 0; y < r.getHeight(); y++) {
            int yPos = y + yOff;
            if(yPos < 0 | yPos >= bounds.height) continue;
            for (int x = 0; x < r.getWidth(); x++) {
                int xPos = x + xOff;
                if(xPos < 0 | xPos >= bounds.width) continue;
                renderPixel(xPos, yPos, r.getPixel(x, y));
            }
        }
    }
    
    protected void renderPixel(int xPos, int yPos, int rgb) {
    	int replaced = Window.getPixel(xPos, yPos);
    	if(rgb != 0xFF_FF_00_FF && (replaced == 0xFF_00_00_00 || replaced == 0xFF_30_BB_2D)) {
    		Window.renderPixel(xPos, yPos, rgb);
    	}
    }
}
