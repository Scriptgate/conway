package net.scriptgate.conway.field

import net.scriptgate.conway.common.Point2D

class Cell internal constructor(x: Int, y: Int, internal val alive: Boolean) {

    internal val position: Point2D

    init {
        this.position = Point2D(x, y)
    }

    companion object {

        fun createLiveCell(x: Int, y: Int): Cell {
            return Cell(x, y, true)
        }

        fun createDeadCell(x: Int, y: Int): Cell {
            return Cell(x, y, false)
        }
    }

}
