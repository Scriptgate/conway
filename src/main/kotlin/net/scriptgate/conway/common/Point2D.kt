package net.scriptgate.conway.common

import java.lang.Integer.compare
import java.lang.Math.sqrt

class Point2D {

    var x: Int = 0
    var y: Int = 0

    val isZero: Boolean
        get() = this.x == 0 && this.y == 0

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun add(point: Point2D): Point2D {
        return this.add(point.x, point.y)
    }

    fun add(x: Int, y: Int): Point2D {
        this.x += x
        this.y += y
        return this
    }

    fun multiply(d: Double): Point2D {
        this.x *= d.toInt()
        this.y *= d.toInt()
        return this
    }

    fun multiply(point: Point2D): Point2D {
        this.x *= point.x
        this.y *= point.y
        return this
    }

    fun setZero() {
        this.x = 0
        this.y = 0
    }

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


    fun minimum(that: Point2D): Point2D {
        return Point2D(
                Math.min(this.x, that.x),
                Math.min(this.y, that.y)
        )
    }

    fun maximum(that: Point2D): Point2D {
        return Point2D(
                Math.max(this.x, that.x),
                Math.max(this.y, that.y)
        )
    }

    operator fun minus(that: Point2D): Point2D {
        return Point2D(this.x - that.x, this.y - that.y)
    }

    operator fun times(that: Point2D): Int {
        return this.x * that.y - this.y * that.x
    }

    fun normalize(point: Point2D): Point2D {
        return Point2D(
                compare(point.x, 0),
                compare(point.y, 0)
        )
    }

    fun distance(a: Point2D, b: Point2D): Double {
        return sqrt(((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y)).toDouble())
    }

    fun distance(x1: Int, y1: Int, x2: Int, y2: Int): Double {
        return sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)).toDouble())
    }

}
