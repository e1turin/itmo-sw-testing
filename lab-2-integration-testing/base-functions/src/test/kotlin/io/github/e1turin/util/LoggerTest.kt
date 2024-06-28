package io.github.e1turin.util

import io.kotest.core.spec.style.StringSpec
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
        ) { x -> x }
    }
})
