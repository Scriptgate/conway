package net.scriptgate.conway.common

class Point2D(var x: Int, var y: Int) {

    override fun toString(): String {
        return "[$x,$y]"
    }

    override fun equals(other: Any?): Boolean {
        var result = false
        if (other is Point2D) {
            val that = other as Point2D?
            result = that!!.canEqual(this) && this.x == that.x && this.y == that.y
        }
        return result
    }

    override fun hashCode(): Int {
        return 41 * (41 + x) + y
    }

    private fun canEqual(other: Any): Boolean {
        return other is Point2D
    }

    operator fun minus(that: Point2D): Point2D {
        return Point2D(this.x - that.x, this.y - that.y)
    }

    operator fun times(that: Point2D): Int {
        return this.x * that.y - this.y * that.x
    }
}
