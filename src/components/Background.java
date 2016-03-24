package components;

import graphics.Renderable;
import java.awt.Dimension;

import graphics.Sprite;
import graphics.SpriteSheet;
import java.awt.Point;
import java.util.Stack;

import frame.Window;

public class Background extends GraphicsComponent {

    private final Type type;
    private final boolean[][] numbers;
    private int update;
    private final Stack<Point> points;

    public Background(Dimension s, Type t) {
        super(0, 72, s.width, s.height, Sprite.GAME_STATIC, SpriteSheet.GAME.getSprite(0, 0), SpriteSheet.GAME.getSprite(1, 0));
        type = t;
        int wd = getWidth() / press.getWidth() + 1;
        int he = getHeight() / press.getHeight() + 1;
        numbers = new boolean[he][wd];
        points = new Stack<>();
        for (int y = 0; y < numbers.length; y++) {
            for (int x = 0; x < numbers[y].length; x++) {
                numbers[y][x] = (y + x) % 2 == 0;
                points.push(new Point(x, y));
            }
        }
        
        update = 0;
    }

    public void update() {
        if (type != Type.STATIC & (++update % (random.nextInt(90) + 20) == 0)) {
            update = 0;
            int y = random.nextInt(numbers.length);
            int x = random.nextInt(numbers[y].length);
            numbers[y][x] = !numbers[y][x];
            points.push(new Point(x, y));
        }
        render = !points.isEmpty();
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
        while(!points.isEmpty()) {
            Point p = points.pop();
            int yPos = p.y * 128 - yOff;
            int xPos = p.x * 72 - xOff;
            if (yPos + 128 < 0 | yPos >= getHeight() | xPos + 72 < 0 | xPos >= getWidth()) {
                continue;
            }
            renderSprite(numbers[p.y][p.x] ? hover : press, xPos, yPos);
        }
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
    	if(replaced == 0xFF_00_00_00 || replaced == 0xFF_30_BB_2D) {
    		Window.renderPixel(xPos, yPos, rgb);
    	}
    }
}
