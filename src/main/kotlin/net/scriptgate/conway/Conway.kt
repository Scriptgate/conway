package net.scriptgate.conway

import net.scriptgate.common.Point
import net.scriptgate.conway.field.Cell
import net.scriptgate.conway.field.Field
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer
import java.util.*

class Conway : Application {

    private var radius = 10

    private lateinit var field: Field

    private var lastPosition: Point = Point()

    override fun initialize() {
        val fieldWidth = Engine.WIDTH / Field.TILE_SIZE
        val fieldHeight = Engine.HEIGHT / Field.TILE_SIZE

        this.field = Field(fieldWidth, fieldHeight)

        FieldMapper()
                .loadCells("glidergun")
                .forEach { cell -> field.mutate(cell) }
    }

    override fun onClick(x: Int, y: Int) {
        lastPosition = Point(x / Field.TILE_SIZE, y / Field.TILE_SIZE)

        FieldMapper()
                .loadCells("pulsar")
                .forEach { cell ->
                    field.mutate(cell.translate(x/ Field.TILE_SIZE-8,y/Field.TILE_SIZE-8))
                }

    }

    override fun onUpdate(ticks: Int, frames: Int) {

    }

    override fun onTick(inputComponent: InputComponent, elapsedTime: Double) {
        field.update()
        inputComponent.pressedKeys.forEach { key ->
            when (key.keyName) {
                "q" -> {
                    val random = Random()

                    val x = lastPosition.x + random.nextInt(radius*2) - random.nextInt(radius*2)
                    val y = lastPosition.y + random.nextInt(radius*2) - random.nextInt(radius*2)
                    field.mutate(Cell.createLiveCell(x, y))
                }
            }
        }
    }

    override fun render(renderer: Renderer) {
        field.render(renderer)
    }
}