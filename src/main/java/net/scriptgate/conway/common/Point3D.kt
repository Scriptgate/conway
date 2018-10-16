package net.scriptgate.conway.common

class Point3D {

    var x: Int = 0
    var y: Int = 0
    var z: Int = 0

    constructor() {}

    constructor(x: Int, y: Int, z: Int) {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(other: Point3D) : this(other.x, other.y, other.z) {}

}
