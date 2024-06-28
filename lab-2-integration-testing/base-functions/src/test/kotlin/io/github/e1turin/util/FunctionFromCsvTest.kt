package io.github.e1turin.util

import io.kotest.core.spec.style.StringSpec
import java.io.File

class FunctionFromCsvTest : StringSpec({
    "Read CSV file" {
        val file = File("kek.txt")
        val func = FunctionFromCsv(file)
        val range = -3.0..4.0
        val step = 0.5
        val functionName = "id"
        var currX = range.start
        while (currX <= range.endInclusive) {
            val value = func(currX)
            assert(value == currX)
            currX += step
        }
    }
})