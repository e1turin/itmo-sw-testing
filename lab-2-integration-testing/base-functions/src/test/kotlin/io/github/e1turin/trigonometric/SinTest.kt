package io.github.e1turin.trigonometric

import io.github.e1turin.util.ERROR_RATE
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll

class SinTest : StringSpec({
    "compare with golden implementation" {
        Arb.double().checkAll { d ->
            val test = sin(d, ERROR_RATE)
            val gold = kotlin.math.sin(d)
            val diff = test - gold
            println(diff)
        }
    }
})