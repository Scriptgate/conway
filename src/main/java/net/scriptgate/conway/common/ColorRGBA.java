package net.scriptgate.conway.common;


public abstract class ColorRGBA {

    public static class Float extends ColorRGBA {
        public float r, g, b, a;

        public Float() {
        }

        public Float(float r, float g, float b, float a) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }

        @Override
        public int intValue() {
            return (int) (a * 255) << 24 | ((int) (r * 255) & 0xFF) << 16 | ((int) (g * 255) & 0xFF) << 8 | (int) (b * 255) & 0xFF;
        }
    }

    public abstract int intValue();


}
