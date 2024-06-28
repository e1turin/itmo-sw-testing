package io.github.e1turin.trigonometric

import io.github.e1turin.logarithmic.ln
import io.github.e1turin.util.ERROR_RATE
import kotlin.math.abs
import kotlin.math.pow


public class Sin(
    private val eps: Double
): (Double) -> Double {
    override fun invoke(x: Double): Double {
        return sin(x, eps = eps)
    }
}

public fun sin(x: Double, eps: Double = ERROR_RATE): Double {
    val sign = x < 0
    val tmpX = abs(x) % (Math.PI / 2)
    var sum = tmpX
    var diff = tmpX
    var n = 1
    while (abs(diff) > eps) {
        diff = -diff * tmpX.pow(2) / (2 * n * (2 * n + 1))
        sum += diff
        n++
    }
    return if (sign) {
        -sum
    } else {
        sum
    }
}