package io.github.e1turin.trigonometric

import io.github.e1turin.util.ERROR_RATE
import kotlin.math.abs
import kotlin.math.pow

public fun sin(x: Double, eps: Double = ERROR_RATE): Double {
    val sign = x < 0
    val x = abs(x) % (Math.PI / 2)
    var sum = x
    var tmp = x
    var n = 1
    while (tmp > eps) {
        tmp = -tmp * x.pow(2) / (2 * n * (2 * n + 1))
        sum += tmp
        n++
    }
    return if (sign) {
        -sum
    } else {
        sum
    }
}