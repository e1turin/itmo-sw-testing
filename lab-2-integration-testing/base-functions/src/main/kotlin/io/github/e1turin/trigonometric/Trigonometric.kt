package io.github.e1turin.trigonometric

public fun cos(x: Double, eps: Double): Double {
    return sin(x - Math.PI/2, eps)
}

public fun csc(x: Double, eps: Double): Double {
    return 1.0 / sin(x, eps)
}
