package io.github.e1turin

import io.github.e1turin.math.arcsin
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll
import org.junit.jupiter.api.assertThrows
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.asin
import kotlin.math.sqrt
import kotlin.test.Test

internal class Task1MathKotlinTest {

    @Test
    fun `golden test asin`() {
        val x = 0.0
        assert(arcsin(x) == asin(x))
    }

}

internal class Task1MathKotest : AnnotationSpec() {
    @Test
    suspend fun `golden test asin`() {
        Arb.double(min = -1.0, max = 1.0).checkAll { d ->
            val value = arcsin(d)
            if (value !in -1E-9..1E-9)
                abs((value - asin(d)) / value) shouldBeIn (0.0..0.35)
        }
    }
}

internal class Task2MathPropertyTest : FunSpec({

    context("special values: near zero") {
        Arb.double(min = -1E-9, max = 1E-9).checkAll { d ->
            arcsin(d) shouldBeIn (-1E-5..1E-5)
        }
    }

    context("special values: real zero") {
        arcsin(-0.0) shouldBeIn (-1E-12..1E-12)
        arcsin(0.0) shouldBeIn (-1E-12..1E-12)
    }

    context("special values: well known points") {
        val points = listOf<Pair<Double,Double>>(
            -1.0 to -PI / 2,
            1.0 to PI / 2,
            -1.0 / 2 to -PI / 6,
            1.0 / 2 to PI / 6,
            sqrt(3.0) / 2 to PI / 3,
            -sqrt(3.0) / 2 to -PI / 3,
        )
        withData(points) { (x, y) ->
            abs(arcsin(x) - y) shouldBeIn (0.0..1E-3)
        }
    }

    context("invalid input") {
        assertThrows<IllegalArgumentException> {
            arcsin(Double.NaN)
        }
        Arb.double(min = 1.0, includeNonFiniteEdgeCases = false).checkAll { d ->
            if (d != 1.0) assertThrows<IllegalArgumentException> {
                println(d)
                println(arcsin(d))
            }
        }
        Arb.double(max = -1.0, includeNonFiniteEdgeCases = false).checkAll { d ->
            if (d != -1.0) assertThrows<IllegalArgumentException> {
                arcsin(d)
            }
        }
    }

})