package net.scriptgate.conway.common

class Color(var r: Int, var g: Int, var b: Int, var a: Int = 255) {


    fun intValue(): Int {
        return a shl 24 or (r and 0xFF shl 16) or (g and 0xFF shl 8) or (b and 0xFF)
    }

    companion object {

        val RED = Color(255, 0, 0)
        val GREEN = Color(0, 255, 0)
        val BLUE = Color(0, 0, 255)
        val WHITE = Color(255, 255, 255)
        val BLACK = Color(0, 0, 0)
    }
}
