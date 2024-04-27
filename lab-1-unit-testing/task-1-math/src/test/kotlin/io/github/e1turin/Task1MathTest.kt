package io.github.e1turin

import io.github.e1turin.math.arcsin
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll
import kotlin.math.abs
import kotlin.math.asin
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
            abs((value - asin(d)) / value) shouldBeIn (0.0..0.35)
        }
    }
}

internal class Task2MathPropertyTest : StringSpec({

    "kek" {
        "hello".length shouldBe 5
    }

})