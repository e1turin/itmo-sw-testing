package io.github.e1turin.util

import java.io.File


public fun logToCsv(
    file: File,
    range: ClosedRange<Double>,
    step: Double,
    functionName: String,
    function: (Double) -> Double,
) {
    require(file.isFile) { "CSV can be only written into file" }
    require(range.endInclusive > range.start) { "Given range must be ordered properly" }
    require(step > 0) { "Step must be positive" }

    file.printWriter().use { out ->
        out.println("x,$functionName(x)")

        var currX = range.start
        var funValue = function(currX)

        while (currX <= range.endInclusive) {
            out.println("${currX},${funValue}")
            currX += step
            funValue = function(currX)
        }
    }
}
