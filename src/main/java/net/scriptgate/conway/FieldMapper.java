package net.scriptgate.conway;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import net.scriptgate.conway.field.Cell;
import net.scriptgate.engine.image.BufferedImageLoader;

public class FieldMapper {

    private final BufferedImageLoader imageLoader;

    public FieldMapper() {
        imageLoader = new BufferedImageLoader();
    }

    public List<Cell> loadCell(String cellImagePath) {

        BufferedImage cellImage = imageLoader.getTexture(cellImagePath);
        String[] hexArray = imageLoader.imageToHexArray(cellImage);
        final int width = cellImage.getWidth();

        return IntStream.range(0, hexArray.length)
                .filter(i -> hexArray[i].equals("000000"))
                .mapToObj(i -> Cell.createLiveCell(i % width, i / width))
                .collect(Collectors.toList());

    }

}
