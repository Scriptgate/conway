package net.scriptgate.conway;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import static java.lang.Integer.toHexString;
import java.util.ArrayList;
import java.util.List;
import net.scriptgate.engine.image.BufferedImageLoader;

public class FieldMapper {

    private final BufferedImageLoader imageLoader;

    public FieldMapper() {
        imageLoader = new BufferedImageLoader();
    }

    public List<Cell> loadCell(String cellImagePath) {
        List<Cell> cellChanges = new ArrayList<>();

        BufferedImage cellImage = imageLoader.getTexture(cellImagePath);

        String[] hexArray = imageLoader.imageToHexArray(cellImage);

        final int width = cellImage.getWidth();

        for (int i = 0; i < hexArray.length; i++) {
            if (hexArray[i].equals("000000")) {
                cellChanges.add(new Cell(i % width, i / width, true));
            }
        }

        return cellChanges;
    }

}
