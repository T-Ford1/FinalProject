package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ford.terrell
 */
public class SpriteSheet {

    public static final SpriteSheet GAME = new SpriteSheet("res/containers/game.png", 72, 128);
    public static final SpriteSheet COMPONENTS = new SpriteSheet("res/components/components.png", 64);
    public static final SpriteSheet TITLEBAR = new SpriteSheet("res/containers/titlebar.png", 201, 61);
    public static final SpriteSheet DIGIT_SHIFT = new SpriteSheet("res/animations/digitshift.png", 72, 128);
    public static final SpriteSheet NUMBER_64 = new SpriteSheet("res/animations/number.png", 64, 120);
    public static final SpriteSheet ALPHABET = new SpriteSheet("res/animations/alpha.png", 32, 32);
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!?()-=+[]%' .";

    public final int COLUMNS, ROWS;
    private final Sprite[] sprites;

    public SpriteSheet(String p, int w, int h) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(p));
            int[] pixels = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        } catch (IOException ex) {
            image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
        }
        COLUMNS = image.getWidth() / w;
        ROWS = image.getHeight() / h;
        sprites = new Sprite[COLUMNS * ROWS];
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                sprites[y * COLUMNS + x] = new ColorSprite(x * w, y * h, w, h, image);
            }
        }
    }

    public SpriteSheet(String p, int s) {
        this(p, s, s);
    }

    public Sprite getSprite(int x, int y) {
        return sprites[y * COLUMNS + x];
    }
    
    public Sprite[] getSprites() {
        return sprites;
    }
    
    public void setSprite(Sprite s, int x, int y) {
        sprites[y * COLUMNS + x] = s;
    }
}
