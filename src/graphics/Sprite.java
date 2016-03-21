
package graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author ford.terrell
 */

public abstract class Sprite implements Renderable {
	
	public static final Sprite TEMPERATURE = new ColorSprite("res/images/messages.png");
	public static final Sprite MESSAGEBAR = new ColorSprite("res/images/messages.png");
	public static final Sprite SETTINGS = new ColorSprite("res/images/settings.png");
	public static final Sprite PROFILE = new ColorSprite("res/images/profile.png");
	public static final Sprite RAINBOW = new ColorSprite("res/images/rainbow.png");
	public static final Sprite MENUBAR = new ColorSprite("res/images/menubar.png");
	public static final Sprite TOOLBAR = new ColorSprite("res/images/toolbar.png");
	public static final Sprite UPGRADE = new ColorSprite("res/images/upgrade.png");
	public static final Sprite REMOVE = new ColorSprite("res/images/remove.png");
	public static final Sprite SHIELD = new ColorSprite("res/images/shield.png");
	public static final Sprite CPU = new ColorSprite("res/images/messages.png");
	public static final Sprite MUSIC = new ColorSprite("res/images/music.png");
	public static final Sprite CLAN = new ColorSprite("res/images/clan.png");
	public static final Sprite GAME = new ColorSprite("res/images/game.png");
	public static final Sprite DEFAULT = new ColorSprite(Color.blue, 32, 32);
	public static final Sprite ADD = new ColorSprite("res/images/add.png");
	public static final Sprite IPS = new ColorSprite("res/images/ips.png");

	private final int WIDTH, HEIGHT;
	private final int[] pixels;

	protected Sprite(int xOff, int yOff, int w, int h, BufferedImage img) {
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		img.getRGB(xOff, yOff, w, h, pixels, 0, w);
	}

	public Sprite(Color c, int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		setColor(c);
	}

	public Sprite(String p) {
		//System.out.println("beginning sprite init of " + p.substring(1 + p.lastIndexOf("/")));
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(p));
		} catch (IOException e) {
			e.printStackTrace();
			img = DEFAULT.getImage();
		} finally {
			pixels = new int[(HEIGHT = img.getHeight()) * (WIDTH = img.getWidth())];
			img.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		}
	}
	
	public BufferedImage getImage() {
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		img.setRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
		return img;
	}
	
	public int[] getPixels() {
		return pixels;
	}

	public int getPixel(int x, int y) {
		return pixels[y * WIDTH + x];
	}
	
	public void setColor(Color c) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = pixels[i] != 0xFF_FF_00_FF ? c.getRGB() : pixels[i];
		}
	}

	public void removeColor(Color c) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = pixels[i] == c.getRGB() ? 0xFF_FF_00_FF : pixels[i];
		}
	}

	public void replaceColor(Color old, Color next) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = pixels[i] == old.getRGB() ? next.getRGB() : pixels[i];
		}
	}
	
	public abstract void update();
	
	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}
}