package net.scriptgate.conway.common;

public class ZColor2D {

    private Point2D position;
    private int color;

    public ZColor2D(Point2D position, int color) {
        this.position = position;
        this.color = color;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
