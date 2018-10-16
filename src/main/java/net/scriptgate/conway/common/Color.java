package net.scriptgate.conway.common;

public class Color extends ColorRGBA {

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    public int r, g, b, a;

    public Color() {
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    @Override
    public int intValue() {
        return a << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }
}
