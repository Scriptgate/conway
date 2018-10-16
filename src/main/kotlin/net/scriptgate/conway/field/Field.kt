package net.scriptgate.conway.field

import java.util.ArrayList
import java.util.HashMap

import net.scriptgate.conway.common.Point2D

import net.scriptgate.common.Color3f.BLACK
import net.scriptgate.common.Color3f.WHITE
import net.scriptgate.engine.Engine.HEIGHT
import net.scriptgate.engine.Engine.WIDTH

import net.scriptgate.engine.Renderer

class Field(val width: Int, val height: Int) {
    private val cells: MutableMap<Point2D, Cell>

    init {
        this.cells = HashMap()
    }

    fun mutate(cell: Cell) {
        cells[cell.position] = cell
    }

    fun isLiveCell(x: Int, y: Int): Boolean {
        val cell = this.cells[Point2D(x, y)] ?: return false
        return cell.alive
    }

    fun render(renderer: Renderer) {
        renderer.setColor(WHITE)
        renderer.setOpacity(1f)
        renderer.fillRect(0, 0, WIDTH, HEIGHT)

        renderer.setColor(BLACK)
        cells.values.stream()
                .filter { cell -> cell.alive }
                .forEach { cell -> renderer.fillRect(cell.position.x * TILE_SIZE, cell.position.y * TILE_SIZE, TILE_SIZE, TILE_SIZE) }

    }

    fun update() {
        val cells = ArrayList<Cell>()

        for (x in 0 until width) {
            for (y in 0 until height) {
                val cell = updateCell(x, y)
                if (cell != null) {
                    cells.add(cell)
                }
            }
        }
        for (cell in cells) {
            mutate(cell)
            //mutateNeighbours(cell);
        }
    }

    private fun mutateNeighbours(cell: Cell): Int {
        var liveCells = 0

        for (xOffset in -1..1) {
            for (yOffset in -1..1) {
                val tx = cell.position.x + xOffset
                val ty = cell.position.y + yOffset

                if (tx == cell.position.x && ty == cell.position.y) {
                    continue
                }
                if (isLiveCell(tx, ty)) {
                    liveCells++
                }
            }
        }

        return liveCells
    }

    fun updateCell(x: Int, y: Int): Cell? {
        if (isLiveCell(x, y) && (isCellUnderPopulated(x, y) || isCellOverPopulated(x, y))) {
            return Cell(x, y, false)
        } else if (doesCellReproduce(x, y)) {
            return Cell(x, y, true)
        }
        return null
    }

    private fun isCellUnderPopulated(x: Int, y: Int): Boolean {
        return getLiveNeighbours(x, y) < 2
    }

    private fun isCellOverPopulated(x: Int, y: Int): Boolean {
        return getLiveNeighbours(x, y) > 3
    }

    private fun doesCellReproduce(x: Int, y: Int): Boolean {
        return getLiveNeighbours(x, y) == 3
    }

    private fun getLiveNeighbours(x: Int, y: Int): Int {
        var liveCells = 0

        for (xOffset in -1..1) {
            for (yOffset in -1..1) {
                val tx = x + xOffset
                val ty = y + yOffset

                if (tx == x && ty == y) {
                    continue
                }
                if (isLiveCell(tx, ty)) {
                    liveCells++
                }
            }
        }

        return liveCells
    }

    companion object {

        val TILE_SIZE = 5
    }

}
