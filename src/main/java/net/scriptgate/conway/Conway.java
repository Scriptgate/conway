package net.scriptgate.conway;

import static net.scriptgate.conway.field.Field.TILE_SIZE;

import net.scriptgate.conway.field.Cell;
import net.scriptgate.conway.field.Field;
import net.scriptgate.engine.Application;
import net.scriptgate.engine.Engine;
import net.scriptgate.engine.InputComponent;
import net.scriptgate.engine.Renderer;

public class Conway implements Application {

    private Field field;

    @Override public void initialize() {
        int fieldWidth = Engine.WIDTH / TILE_SIZE;
        int fieldHeight = Engine.HEIGHT / TILE_SIZE;

        this.field = new Field(fieldWidth, fieldHeight);

        new FieldMapper()
                .loadCell("glidergun")
                .forEach(cell -> field.mutate(cell));
    }

    @Override
    public void onTick(InputComponent inputComponent, double elapsedTime) {
        field.update();
    }

    @Override
    public void render(Renderer renderer) {
        field.render(renderer);
    }

}
