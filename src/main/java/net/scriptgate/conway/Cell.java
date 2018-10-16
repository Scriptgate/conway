package net.scriptgate.conway;

import net.scriptgate.conway.common.Point2D;

public class Cell {

    public final Point2D position;
    public final boolean value;

    public Cell(int x, int y, boolean value) {
        this.position = new Point2D(x, y);
        this.value = value;
    }

    public static Cell createLiveCell(int x, int y) {
        return new Cell(x, y, true);
    }

    public static Cell createDeadCell(int x, int y) {
        return new Cell(x, y, false);
    }

}
