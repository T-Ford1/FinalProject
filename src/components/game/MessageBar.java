/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components.game;


import graphics.Renderable;
import graphics.Sprite;
import graphics.SpriteSheet;
import java.util.ArrayList;

/**
 *
 * @author ford.terrell
 */
public class MessageBar extends Tab {

    private Renderable message;
    private double mX;
    private static final ArrayList<String> messages = new ArrayList<>();

    public MessageBar(int xPos, int yPos, int w, int h) {
        super(xPos, yPos, w, h);
    }

    public static final void displayMessage(String m) {
        messages.add(m);
    }

    public void update() {
        if (!messages.isEmpty() && message == null) {
            message = getMessage(messages.get(0), 81);
            mX = bounds.width;
        }
        if (message != null) {
            mX -= 6;
            if (mX + message.getWidth() < 0) {
                messages.remove(0);
                message = null;
            }
        }
    }

    public void renderAll() {
        super.renderAll();
        if (message != null) {
            renderMessage();
        }
    }

    private Renderable getMessage(String m, int size) {
        Sprite s = new Sprite(0xFF_FF_00_FF, size * m.length(), size);
        for (int i = 0, xOff = 0; i < m.length(); i++, xOff += size) {
            int index = SpriteSheet.ALPHA.indexOf(m.charAt(i) + "");
            if(index < 0) {
                System.out.println("found unparseable char: " + m.charAt(i));
                continue;
            }
            Sprite let = Sprite.scaleSprite(SpriteSheet.ALPHABET.getSprites()[index], size, size);
            for (int y = 0; y < let.getHeight(); y++) {
                for (int x = 0; x < let.getWidth(); x++) {
                    s.setPixel(x + xOff, y, let.getPixel(x, y));
                }
            }
        }
        return s;
    }
    
    private void renderMessage() {
        renderSprite((int) mX, 6, message);
    }
}
