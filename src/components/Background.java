package components;

import frame.Window;
import graphics.Renderable;

import java.awt.Color;
import java.awt.Dimension;

import graphics.Sprite;
import graphics.SpriteSheet;

import graphics.BGSprite;
import graphics.ColorFader;
import graphics.ColorSprite;
import java.util.ArrayList;

public class Background extends GraphicsComponent {

    private final BGSprite zero, one;

    private final Type type;
    private final boolean[][] numbers;
    private int update;
    public static ArrayList<BGSprite> renders;

    public Background(Dimension s, Type t) {
        super(0, 72, s.width, s.height - 72, Sprite.GAME_STATIC, SpriteSheet.GAME.getSprite(0, 0), SpriteSheet.GAME.getSprite(1, 0));
        type = t;
        int wd = getWidth() / press.getWidth() + 1;
        int he = getHeight() / press.getHeight() + 1;
        numbers = new boolean[he][wd];
        renders = new ArrayList<>();
        for (int y = 0; y < numbers.length; y++) {
            for (int x = 0; x < numbers[y].length; x++) {
                numbers[y][x] = (y + x) % 2 == 0;
                renders.add(numbers[y][x] ? new BGSprite(this, hover, 1, x, y) : new BGSprite(this, press, 1, x, y));
            }
        }
        update = 0;
        int[] btg = new int[120];
        for (int i = 0; i < btg.length; i++) {
            int r = (int) Math.round(48. * i / btg.length);
            int g = (int) Math.round(187. * i / btg.length);
            int b = (int) Math.round(45. * i / btg.length);
            btg[i] = new Color(r, g, b).getRGB();
        }
        btg[btg.length - 1] = 0xFF_30_BB_2D;
        int[] gtb = new int[btg.length];
        for (int i = 0; i < gtb.length; i++) {
            gtb[i] = btg[btg.length - 1 - i];
        }
        ColorSprite a = new ColorSprite(SpriteSheet.DIGIT_SHIFT.getSprite(0, 0));
        ColorSprite b = new ColorSprite(SpriteSheet.DIGIT_SHIFT.getSprite(1, 0));
        a.addColorScheme(0xFF_00_00_00, new ColorFader(btg));
        b.addColorScheme(0xFF_00_00_00, new ColorFader(btg));
        a.addColorScheme(0xFF_30_BB_2D, new ColorFader(gtb));
        b.addColorScheme(0xFF_30_BB_2D, new ColorFader(gtb));
        zero = new BGSprite(this, b, btg.length, 0, 0);
        one = new BGSprite(this, a, btg.length, 0, 0);
    }

    private boolean containsPos(int x, int y) {
        for (BGSprite s : renders) {
            if (s.getX() == x && s.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        press.update();
        hover.update();
        if (type == Type.SHIFTING) {
            if (++update % (random.nextInt(1) + 20) == 0) {
                update = 0;
                int y = random.nextInt(numbers.length);
                int x = random.nextInt(numbers[y].length);
                if (!containsPos(x, y)) {
                    numbers[y][x] = !numbers[y][x];
                    renders.add(numbers[y][x] ? zero.copyOf(this).setPosition(x, y) : one.copyOf(this).setPosition(x, y));
                }
            }
            for (int i = 0; i < renders.size(); i++) {
                BGSprite s = renders.get(i);
                s.update();
                if (s.renderedLast()) {
                    renders.remove(i--);
                }
            }
            render = render | !renders.isEmpty() | alwaysRender;
        } else if (type == Type.MOVING) {
            render = render | alwaysRender;
        } else {
            render = render | alwaysRender;
        }
    }

    public void render() {
        if (!render) {
            return;
        }
        renderAll();
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
            if (yPos < 0 | yPos >= bounds.height) {
                continue;
            }
            for (int x = 0; x < r.getWidth(); x++) {
                int xPos = x + xOff;
                if (xPos < 0 | xPos >= bounds.width) {
                    continue;
                }
                renderPixel(xPos, yPos, r.getPixel(x, y));
            }
        }
    }

    protected void renderPixel(int xPos, int yPos, int rgb) {
        //int replaced = super.getPixel(xPos, yPos);
        //if ((replaced == 0xFF_00_00_00 || replaced == 0xFF_30_BB_2D)) {
        super.renderPixel(xPos, yPos, rgb);
        //}
    }
}
