package io.github.e1turin.logarithmic

import io.github.e1turin.util.ERROR_RATE
import kotlin.math.abs
import kotlin.math.pow

public class Ln(
   private val eps: Double
): (Double) -> Double {
   override fun invoke(x: Double): Double {
      return ln(x, eps = eps)
   }
}

public fun ln(x: Double, eps: Double = ERROR_RATE): Double {
   when {
      x == Double.POSITIVE_INFINITY -> return Double.POSITIVE_INFINITY
      x == 0.0 -> return Double.NEGATIVE_INFINITY
      x == 1.0 -> return 0.0
      x.isNaN() || x < 0.0 -> return Double.NaN
   }

   var diff = (x - 1) / (x + 1)
   val tmpX = diff.pow(2)
   var sum = diff
   var n = 1

   while (abs(diff) > eps) {
      diff = (2 * n - 1) * diff * tmpX / (2 * n + 1)
      sum += diff
      n++
   }

   return sum * 2
}
