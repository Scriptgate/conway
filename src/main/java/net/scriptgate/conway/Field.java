package net.scriptgate.conway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.scriptgate.conway.common.Point2D;
import static net.scriptgate.engine.Engine.HEIGHT;
import static net.scriptgate.engine.Engine.WIDTH;
import net.scriptgate.engine.Renderer;

public class Field {

    public static final int TILE_SIZE = 5;
    private final int width;
    private final int height;
    private final Map<Point2D, Cell> cells;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new HashMap<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void mutate(Cell cell) {
        cells.put(cell.position, cell);
    }

    public boolean isLiveCell(int x, int y) {
        Cell cell = this.cells.get(new Point2D(x, y));
        if (cell == null) {
            return false;
        }
        return cell.value;
    }

    public void render(Renderer renderer) {
        renderer.setColor(1, 1, 1);
        renderer.setOpacity(1f);
        renderer.fillRect(0, 0, WIDTH, HEIGHT);
        for (Cell cell : cells.values()) {
            if (cell.value) {
                renderer.setColor(0, 0, 0);
            } else {
                renderer.setColor(0.8f, 0.8f, 0.8f);
            }
            renderer.fillRect(cell.position.x * TILE_SIZE, cell.position.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        /*
         for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
         //renderer.setOpacity(0.5f);
         //renderer.setColor(0, 0, 0);
         //renderer.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
         //renderer.setColor(1,1,1);
         //renderer.drawString(x* TILE_SIZE, y* TILE_SIZE, valueOf(getLiveNeighbours(x, y)));
         Cell cell = cells.get(new Point2D(x, y));

         if (cell != null) {
         if (cell.value) {
         renderer.setColor(0, 0, 0);
         } else {
         renderer.setColor(0.8f, 0.8f, 0.8f);
         }

                    
         } else {
         renderer.setColor(0.8f, 0, 0);
         }
         renderer.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
         }
         }*/
    }

    public void update() {
        List<Cell> cells = new ArrayList<>();

        
         for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
         Cell cell = updateCell(x, y);
         if (cell != null) {
         cells.add(cell);
         }
         }
         }
        for (Cell cell : cells) {
            mutate(cell);
            //mutateNeighbours(cell);
        }
    }

    private int mutateNeighbours(Cell cell) {
        int liveCells = 0;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int tx = cell.position.x + xOffset;
                int ty = cell.position.y + yOffset;

                if (tx == cell.position.x && ty == cell.position.y) {
                    continue;
                }
                if (isLiveCell(tx, ty)) {
                    liveCells++;
                }
            }
        }

        return liveCells;
    }

    public Cell updateCell(int x, int y) {
        if (isLiveCell(x, y) && (isCellUnderPopulated(x, y) || isCellOverPopulated(x, y))) {
            return new Cell(x, y, false);
        } else if (doesCellReproduce(x, y)) {
            return new Cell(x, y, true);
        }
        return null;
    }

    private boolean isCellUnderPopulated(int x, int y) {
        return getLiveNeighbours(x, y) < 2;
    }

    private boolean isCellOverPopulated(int x, int y) {
        return getLiveNeighbours(x, y) > 3;
    }

    private boolean doesCellReproduce(int x, int y) {
        return getLiveNeighbours(x, y) == 3;
    }

    private int getLiveNeighbours(int x, int y) {
        int liveCells = 0;

        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                int tx = x + xOffset;
                int ty = y + yOffset;

                if (tx == x && ty == y) {
                    continue;
                }
                if (isLiveCell(tx, ty)) {
                    liveCells++;
                }
            }
        }

        return liveCells;
    }

}
