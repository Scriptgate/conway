package net.scriptgate.conway.common;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import static net.scriptgate.conway.common.Point2D.crossProduct;
import static net.scriptgate.conway.common.Point2D.difference;
import static net.scriptgate.conway.common.Point2D.max;
import static net.scriptgate.conway.common.Point2D.min;

public class Polygon2D {

    private final List<Point2D> points;

    public Polygon2D() {
        this.points = new ArrayList<>();
    }

    public Polygon2D(List<Point2D> points) {
        this.points = points;
    }

    public void addPoint(Point2D point) {
        this.points.add(point);
    }

    public void addPoint(int x, int y) {
        addPoint(new Point2D(x, y));
    }

    public List<Point2D> getPoints() {
        return this.points;
    }

    public boolean intersects(Polygon2D other) {
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = 0; j < other.points.size() - 1; j++) {
                if (doLinesIntersect(
                        other.points.get(j),
                        other.points.get(j + 1),
                        points.get(i),
                        points.get(i + 1))) {
                    return true;
                }
            }
            if (doLinesIntersect(
                    other.points.get(other.points.size() - 1),
                    other.points.get(0),
                    points.get(i),
                    points.get(i + 1))) {
                return true;
            }
        }

        for (int j = 0; j < other.points.size() - 1; j++) {
            if (doLinesIntersect(
                    other.points.get(j),
                    other.points.get(j + 1),
                    points.get(points.size() - 1),
                    points.get(0))) {
                return true;
            }
        }
        if (doLinesIntersect(
                other.points.get(other.points.size() - 1),
                other.points.get(0),
                points.get(points.size() - 1),
                points.get(0))) {
            return true;
        }
        return false;
    }

    /*
     * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     */
    public boolean contains(Point2D point) {
        int i, j;
        boolean result = false;
        for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            if ((points.get(i).y > point.y) != (points.get(j).y > point.y) && (point.x < (points.get(j).x - points.get(i).x) * (point.y - points.get(i).y) / (points.get(j).y - points.get(i).y) + points.get(i).x)) {
                result = !result;
            }
        }
        return result;
    }

    public void translate(int x, int y) {
        for (Point2D point : this.points) {
            point.add(x, y);
        }
    }

    private static final double EPSILON = 0.000001;

    private static boolean doLinesIntersect(Point2D a1, Point2D a2, Point2D b1, Point2D b2) {
        return doBoundingBoxesIntersect(a1, a2, b1, b2)
                && lineSegmentTouchesOrCrossesLine(a1, a2, b1, b2)
                && lineSegmentTouchesOrCrossesLine(b1, b2, a1, a2);
    }

    private static boolean doBoundingBoxesIntersect(Point2D a1, Point2D a2, Point2D b1, Point2D b2) {
        Point2D minA = min(a1, a2);
        Point2D maxA = max(a1, a2);

        Point2D minB = min(b1, b2);
        Point2D maxB = max(b1, b2);

        return minA.x <= maxB.x
                && maxA.x >= minB.x
                && minA.y <= maxB.y
                && maxA.y >= minB.y;
    }

    private static boolean lineSegmentTouchesOrCrossesLine(Point2D a1, Point2D a2, Point2D b1, Point2D b2) {
        return isPointOnLine(a1, a2, b1)
                || isPointOnLine(a1, a2, b2)
                || (isPointRightOfLine(a1, a2, b1) ^ isPointRightOfLine(a1, a2, b2));
    }

    private static boolean isPointOnLine(Point2D a1, Point2D a2, Point2D b) {
        return abs(crossProduct(difference(a2, a1), difference(b, a1))) < EPSILON;
    }

    private static boolean isPointRightOfLine(Point2D a1, Point2D a2, Point2D b) {
        return crossProduct(difference(a2, a1), difference(b, a1)) < 0;
    }
}
