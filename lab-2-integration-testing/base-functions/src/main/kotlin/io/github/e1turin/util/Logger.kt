package io.github.e1turin.util

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter


public fun logToCsv(
    file: File,
    range: ClosedRange<Double>,
    step: Double,
    functionName: String,
    function: (Double) -> Double,
    printHeader: Boolean = true,
    appendToExistingFile: Boolean = true,
) {
    require(file.isFile) { "CSV can be only written into file" }
    require(range.endInclusive > range.start) { "Given range must be ordered properly" }
    require(step > 0) { "Step must be positive" }

    FileWriter(file, appendToExistingFile).use {
        PrintWriter(it).use { out ->
            if (printHeader) {
                out.println("x,$functionName(x)")
            }

            var currX = range.start
            var funValue = function(currX)

            while (currX <= range.endInclusive) {
                out.println("${currX},${funValue}")
                currX += step
                funValue = function(currX)
            }
        }
    }
}

public fun logCompleteFunctionToCsv(
    file: File,
    functionName: String,
    function: (Double) -> Double
) {
    logToCsv(
        file = file,
        range = RANGE_PERIODIC_UP,
        step = RANGE_INSPECT_STEP,
        functionName = functionName,
        function = function,
        appendToExistingFile = false,
        printHeader = true
    )
    logToCsv(
        file = file,
        range = RANGE_PERIODIC_DOWN,
        step = RANGE_INSPECT_STEP,
        functionName = functionName,
        function = function,
        appendToExistingFile = true,
        printHeader = false
    )
    logToCsv(
        file = file,
        range = RANGE_LOGARITHMIC_LESS_THAN_1,
        step = RANGE_INSPECT_STEP,
        functionName = functionName,
        function = function,
        appendToExistingFile = true,
        printHeader = false
    )
    logToCsv(
        file = file,
        range = RANGE_LOGARITHMIC_GREATER_THAN_1,
        step = RANGE_INSPECT_STEP,
        functionName = functionName,
        function = function,
        appendToExistingFile = true,
        printHeader = false
    )
}
