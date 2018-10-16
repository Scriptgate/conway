package net.scriptgate.conway.common;

import static java.lang.Integer.compare;
import static java.lang.Math.sqrt;

public class Point2D {

    public int x;
    public int y;

    public Point2D() {
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D point) {
        this.x = point.x;
        this.y = point.y;
    }

    public Point2D add(Point2D point) {
        return this.add(point.x, point.y);
    }
    
    public Point2D add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Point2D multiply(double d) {
        this.x *= d;
        this.y *= d;
        return this;
    }
    
    public Point2D multiply(Point2D point) {
        this.x *= point.x;
        this.y *= point.y;
        return this;
    }

    public void setZero() {
        this.x = 0;
        this.y = 0;
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Point2D) {
            Point2D that = (Point2D) other;
            result = (that.canEqual(this) && this.x == that.x && this.y == that.y);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return (41 * (41 + x) + y);
    }

    private boolean canEqual(Object other) {
        return (other instanceof Point2D);
    }

    public static Point2D min(Point2D a, Point2D b) {
        return new Point2D(
                Math.min(a.x, b.x),
                Math.min(a.y, b.y)
        );
    }

    public static Point2D max(Point2D a, Point2D b) {
        return new Point2D(
                Math.max(a.x, b.x),
                Math.max(a.y, b.y)
        );
    }
    
    public static Point2D sum(Point2D a, Point2D b) {
        return new Point2D(a.x + b.x, a.y + b.y);
    }
    
    public static Point2D difference(Point2D a, Point2D b) {
        return new Point2D(a.x - b.x, a.y - b.y);
    }

    public static Point2D normalize(Point2D point) {
        return new Point2D(
                compare(point.x, 0),
                compare(point.y, 0)
        );
    }
    
    public static int crossProduct(Point2D a, Point2D b) {
        return a.x*b.y - a.y*b.x;
    }
    
    public static double distance(Point2D a, Point2D b) {
        return sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
    }
    
    public static double distance(int x1, int y1, int x2, int y2) {
        return sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}
