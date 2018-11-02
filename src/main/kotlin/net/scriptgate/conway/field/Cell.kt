package net.scriptgate.conway.field

import net.scriptgate.conway.common.Point2D

class Cell internal constructor(x: Int, y: Int, internal val alive: Boolean) {

    internal val position: Point2D = Point2D(x, y)

    fun translate(x: Int, y: Int): Cell {
        return Cell(position.x + x, position.y + y, alive)
    }

    companion object {
        fun createLiveCell(x: Int, y: Int): Cell {
            return Cell(x, y, true)
        }
    }

}
