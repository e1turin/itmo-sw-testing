package io.github.e1turin

import io.github.e1turin.math.arcsin
import kotlin.math.asin
import kotlin.test.Test

class Task1MathTest {

    @Test
    fun `golden test arcsin`() {
        val x = 0.0
        assert(arcsin(x) == asin(x))
    }
}

