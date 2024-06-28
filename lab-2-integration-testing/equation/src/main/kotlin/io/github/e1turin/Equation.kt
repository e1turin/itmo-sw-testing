package io.github.e1turin

import io.github.e1turin.logarithmic.*
import io.github.e1turin.trigonometric.cos
import io.github.e1turin.trigonometric.csc
import kotlin.math.pow


public class Equation

public class GeneralFunction(
    private val ln: (Double) -> Double,
    private val sin: (Double) -> Double,
    private val log2: (Double) -> Double,
    private val log3: (Double) -> Double,
    private val log5: (Double) -> Double,
    private val log10: (Double) -> Double,
    private val cos: (Double) -> Double,
    private val csc: (Double) -> Double,
) : (Double) -> Double {
    override fun invoke(x: Double): Double {
        return if (x <= 0) {
            (cos(x) - csc(x)).pow(2) + cos(x)
        } else {
            (((((log3(x) - log3(x)) * log10(x)).pow(3)) / ln(x))
                    / (((log2(x) - (ln(x) + log10(x))) - log10(x)) / (log5(x) + log5(x))))
        }
    }
}

public fun generalFunction(x: Double, eps: Double): Double {
    return if (x <= 0) {
        (cos(x, eps) - csc(x, eps)).pow(2) + cos(x, eps)
    } else {
        (((((log3(x, eps) - log3(x, eps)) * log10(x, eps)).pow(3)) / ln(x, eps))
        / (((log2(x, eps) - (ln(x, eps) + log10(x, eps))) - log10(x, eps)) / (log5(x, eps) + log5(x, eps))))
    }
}