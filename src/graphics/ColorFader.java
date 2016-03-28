package graphics;

public class ColorFader {

	private final int[] colors;
	private int index;

	public ColorFader(ColorFader f) {
		this(f.colors);
	}

	public ColorFader(Sprite c) {
		this(c.getPixels());
	}

	public ColorFader(int[] c) {
		colors = new int[c.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = c[i];
		}
		index = 0;
	}

	public void update() {
		index++;
	}

	public int get(int index) {
		return colors[index];
	}

	public int get() {
		return colors[index = index >= colors.length ? 0 : index];
	}

	public int length() {
		return colors.length;
	}

	public int getIndex() {
		return index;
	}
}
