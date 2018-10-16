package net.scriptgate.conway.field;

import net.scriptgate.conway.common.Point2D;

public class Cell {

    final Point2D position;
    final boolean alive;

    Cell(int x, int y, boolean alive) {
        this.position = new Point2D(x, y);
        this.alive = alive;
    }

    public static Cell createLiveCell(int x, int y) {
        return new Cell(x, y, true);
    }

    public static Cell createDeadCell(int x, int y) {
        return new Cell(x, y, false);
    }

}
