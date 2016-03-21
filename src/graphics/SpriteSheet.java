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
	
	public static final SpriteSheet NUMBER_64 = new SpriteSheet("res/images/number_64.png", 64, 120);
    public static final SpriteSheet NUMBER_32 = new SpriteSheet("res/images/number_32.png", 32, 60);
    public static final SpriteSheet NUMBER_16 = new SpriteSheet("res/images/number_16.png", 16, 30);
    public static final SpriteSheet NUMBER_8 = new SpriteSheet("res/images/number_8.png", 8, 15);
    public static final SpriteSheet LETTER_8 = new SpriteSheet("res/images/letter_8.png", 8, 8);
    public static final SpriteSheet SYMBOL_8 = new SpriteSheet("res/images/symbol_8.png", 8, 8);

    private final String path;
    public final int COLUMNS, ROWS;
    private final Sprite[] sprites;

    public SpriteSheet(String p, int w, int h) {
        path = p;
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
            int[] pixels = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
            
        } catch (IOException ex) {
        	image = null;
            ex.printStackTrace();
        }
        COLUMNS = image.getWidth() / w;
        ROWS = image.getHeight() / h;
        sprites = new Sprite[COLUMNS * ROWS];
        for(int y = 0; y < ROWS; y++) {
        	for(int x = 0; x < COLUMNS; x++) {
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
}