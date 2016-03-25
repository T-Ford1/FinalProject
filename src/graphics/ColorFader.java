package graphics;

public class ColorFader {

    private final int[] colors;
    private int index;

    public ColorFader(ColorFader f) {
        colors = f.colors;
        index = 0;
    }

    public ColorFader(Sprite c) {
        colors = c.getPixels();
        index = 0;
    }

    public void update() {
        index++;
    }
    
    public int get(int index) {
        return colors[index];
    }
    
    public int get() {
        return colors[index];
    }
    
    public int length() {
        return colors.length;
    }
    
    public int getIndex() {
        return index;
    }
}
