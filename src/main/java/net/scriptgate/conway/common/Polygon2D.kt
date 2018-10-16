package net.scriptgate.conway.common

import java.lang.Math.abs
import java.util.ArrayList

class Polygon2D {

    private val points: MutableList<Point2D>

    constructor() {
        this.points = ArrayList()
    }

    constructor(points: MutableList<Point2D>) {
        this.points = points
    }

    fun addPoint(point: Point2D) {
        this.points.add(point)
    }

    fun addPoint(x: Int, y: Int) {
        addPoint(Point2D(x, y))
    }

    fun getPoints(): List<Point2D> {
        return this.points
    }

    fun intersects(other: Polygon2D): Boolean {
        for (i in 0 until points.size - 1) {
            for (j in 0 until other.points.size - 1) {
                if (doLinesIntersect(
                                other.points[j],
                                other.points[j + 1],
                                points[i],
                                points[i + 1])) {
                    return true
                }
            }
            if (doLinesIntersect(
                            other.points[other.points.size - 1],
                            other.points[0],
                            points[i],
                            points[i + 1])) {
                return true
            }
        }

        for (j in 0 until other.points.size - 1) {
            if (doLinesIntersect(
                            other.points[j],
                            other.points[j + 1],
                            points[points.size - 1],
                            points[0])) {
                return true
            }
        }
        return if (doLinesIntersect(
                        other.points[other.points.size - 1],
                        other.points[0],
                        points[points.size - 1],
                        points[0])) {
            true
        } else false
    }

    /*
     * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     */
    operator fun contains(point: Point2D): Boolean {
        var i: Int
        var j: Int
        var result = false
        i = 0
        j = points.size - 1
        while (i < points.size) {
            if (points[i].y > point.y != points[j].y > point.y && point.x < (points[j].x - points[i].x) * (point.y - points[i].y) / (points[j].y - points[i].y) + points[i].x) {
                result = !result
            }
            j = i++
        }
        return result
    }

    fun translate(x: Int, y: Int) {
        for (point in this.points) {
            point.add(x, y)
        }
    }

    companion object {

        private val EPSILON = 0.000001

        private fun doLinesIntersect(a1: Point2D, a2: Point2D, b1: Point2D, b2: Point2D): Boolean {
            return (doBoundingBoxesIntersect(a1, a2, b1, b2)
                    && lineSegmentTouchesOrCrossesLine(a1, a2, b1, b2)
                    && lineSegmentTouchesOrCrossesLine(b1, b2, a1, a2))
        }

        private fun doBoundingBoxesIntersect(a1: Point2D, a2: Point2D, b1: Point2D, b2: Point2D): Boolean {
            val minA = a1.min(a1, a2)
            val maxA = a1.max(a1, a2)

            val minB = b1.min(b1, b2)
            val maxB = b1.max(b1, b2)

            return (minA.x <= maxB.x
                    && maxA.x >= minB.x
                    && minA.y <= maxB.y
                    && maxA.y >= minB.y)
        }

        private fun lineSegmentTouchesOrCrossesLine(a1: Point2D, a2: Point2D, b1: Point2D, b2: Point2D): Boolean {
            return (isPointOnLine(a1, a2, b1)
                    || isPointOnLine(a1, a2, b2)
                    || isPointRightOfLine(a1, a2, b1) xor isPointRightOfLine(a1, a2, b2))
        }

        private fun isPointOnLine(a1: Point2D, a2: Point2D, b: Point2D): Boolean {
            return abs(a1.crossProduct(a2.difference(a2, a1), b.difference(b, a1))) < EPSILON
        }

        private fun isPointRightOfLine(a1: Point2D, a2: Point2D, b: Point2D): Boolean {
            return a1.crossProduct(a2.difference(a2, a1), b.difference(b, a1)) < 0
        }
    }
}
