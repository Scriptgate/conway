package net.scriptgate.conway.common;

public class Point3D {

    public int x;
    public int y;
    public int z;

    public Point3D() {
    }

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D other) {
        this(other.x, other.y, other.z);
    }

}
