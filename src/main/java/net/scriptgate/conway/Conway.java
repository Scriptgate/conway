package net.scriptgate.conway;

import java.util.List;
import static net.scriptgate.conway.Field.TILE_SIZE;
import net.scriptgate.engine.Renderer;

public class Conway {

    private final Field field;

    public Conway(int width, int height) {
        this.field = new Field(width / TILE_SIZE, height / TILE_SIZE);
        
        FieldMapper mapper = new FieldMapper();
        List<Cell> cells = mapper.loadCell("glidergun");
        for(Cell cell : cells) {
            field.mutate(cell);
        }
    }

    public void render(Renderer renderer) {
        field.render(renderer);
    }

    public void update() {
        field.update();
    }

}
