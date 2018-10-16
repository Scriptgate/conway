package net.scriptgate.conway

import net.scriptgate.conway.field.Cell
import net.scriptgate.engine.image.BufferedImageLoader

class FieldMapper {

    private val imageLoader: BufferedImageLoader = BufferedImageLoader()

    fun loadCell(cellImagePath: String): List<Cell> {

        val cellImage = imageLoader.getTexture(cellImagePath)
        val hexArray = imageLoader.imageToHexArray(cellImage)
        val width = cellImage.width

        return (0 until hexArray.size)
                .filter { i -> hexArray[i] == "000000" }
                .map { i -> Cell.createLiveCell(i % width, i / width) }

    }

}
