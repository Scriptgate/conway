package net.scriptgate.conway

import net.scriptgate.conway.field.Field
import net.scriptgate.engine.Application
import net.scriptgate.engine.Engine
import net.scriptgate.engine.InputComponent
import net.scriptgate.engine.Renderer

class Conway : Application {

    private var field: Field? = null

    override fun initialize() {
        val fieldWidth = Engine.WIDTH / Field.TILE_SIZE
        val fieldHeight = Engine.HEIGHT / Field.TILE_SIZE

        this.field = Field(fieldWidth, fieldHeight)

        FieldMapper()
                .loadCell("glidergun")
                .forEach { cell -> field!!.mutate(cell) }
    }

    override fun onTick(inputComponent: InputComponent?, elapsedTime: Double) {
        field!!.update()
    }

    override fun render(renderer: Renderer) {
        field!!.render(renderer)
    }

}
