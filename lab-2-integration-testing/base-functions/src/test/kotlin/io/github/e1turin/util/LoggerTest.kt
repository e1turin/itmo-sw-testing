package io.github.e1turin.util

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

class LoggerTest : StringSpec({
    "Writes to file with given name" {
        val fileName = "kek.txt"
        val file = File(fileName)
        file.createNewFile()

        logToCsv(
            file = file,
            range = -3.0..4.0,
            step = 0.5,
            functionName = "id",
            function = { x -> x }
        )
    }
    "append text to file" {
        val fileName = "src/test/resources/kek.txt"
        val file = File(fileName)
            .also { it.createNewFile() }

        val firstRange = -3.0..4.0
        val secondRange = 6.0..10.0
        val step = 0.5

        logToCsv(
            file = file,
            range = firstRange,
            step = step,
            functionName = "id",
            appendToExistingFile = false,
            function = { x -> x }
        )
        logToCsv(
            file = file,
            range = secondRange,
            step = step,
            functionName = "id",
            printHeader = false,
            function = { x -> x }
        )

        val func = FunctionFromCsv(file)
        var x = firstRange.start
        while (x in firstRange) {
            func(x) shouldBe x
            x += step
        }
        while (x < secondRange.start) {
            func(x) shouldBe Double.NaN
            x += step
        }
        x = secondRange.start
        while (x in secondRange) {
            func(x) shouldBe x
            x += step
        }
    }
})
