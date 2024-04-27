package io.github.e1turin.math

import kotlin.math.pow

public fun arcsin(x: Double): Double {
    val eps = 1E-9
    val res = tailorArcsin(x, eps)
    return res
}

/**
 *  Calculation with Tailor's series:
 *  \arcsin(x) = \sum_{n=0}^\infty ((2n)! * x^(2n+1))
 *                                        /
 *                               (4^n * (n!)^2 * (2n+1))
 *
 *           // reduce with initial state:
 *          = x + \sum_{n=1}^\infty ((2n)! * x^(2n+1))
 *                                          /
 *                                (4^n * (n!)^2 * (2n+1))
 *  // algorithmize with temporary variable:
 *  T_n = T_{n-1} * [{((2n-1) * 2n) * x^2} / {4 * n^2}]
 *      // reduce numerator and denominator
 *      = T_{n-1} * [{(2n-1) * x^2} / {2 * n}]
 *  R_n = T_n / (2n+1)
 */

private fun tailorArcsin(x: Double, eps: Double = 1E-9): Double {
    require(x in -1.0..1.0) { "Arcsin domain is range -1.0..1.0 but given x=$x is out of range." }
    require(eps >= 1E-9) { "Eps must be positive and not very small, current eps is $eps." }

    var n: Double = 1.0
    var tmp: Double = x
    var rem: Double = 1.0
    var sum: Double = x

    while (rem > eps) {
        tmp = tmp * (2 * n - 1) * x.pow(2) / (2 * n)
        rem = tmp / (2 * n + 1)
        sum += rem
        n++
    }

    return sum
}