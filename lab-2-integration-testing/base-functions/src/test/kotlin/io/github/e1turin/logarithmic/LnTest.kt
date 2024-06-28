package io.github.e1turin.logarithmic

import io.github.e1turin.util.ERROR_RATE
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll

class LnTest : StringSpec({
    "compare with golden implementation" {
        Arb.double(min=-1.0, max = 1e3).checkAll { d ->
            val test = ln(d, ERROR_RATE)
            val gold = kotlin.math.ln(d)
            val diff = test - gold
            println(diff)
        }
    }
})