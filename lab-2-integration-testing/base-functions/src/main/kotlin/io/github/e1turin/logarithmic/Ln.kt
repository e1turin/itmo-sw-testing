package io.github.e1turin.logarithmic

import io.github.e1turin.util.ERROR_RATE
import kotlin.math.abs
import kotlin.math.pow

public fun ln(x: Double, eps: Double = ERROR_RATE): Double {
   when {
      x == Double.POSITIVE_INFINITY -> return Double.POSITIVE_INFINITY
      x == 0.0 -> return Double.NEGATIVE_INFINITY
      x == 1.0 -> return 0.0
      x.isNaN() || x < 0.0 -> return Double.NaN
   }

   val tmp = (x - 1).pow(2) / (x + 1).pow(2)
   var sum = 0.0
   var cur = (x - 1) / (x + 1)
   var n = 1

   while (abs(cur) > eps) {
      sum += cur
      cur = (2 * n - 1) * cur * tmp / (2 * n + 1)
      n++
   }

   return sum * 2
}
