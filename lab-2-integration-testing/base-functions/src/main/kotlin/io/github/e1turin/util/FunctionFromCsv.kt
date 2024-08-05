package io.github.e1turin.util

import java.io.File

public class FunctionFromCsv(
    private val file: File
): (Double) -> Double {
    private val values: Map<Double, Double> by lazy { loadValues() }

    override fun invoke(x: Double): Double {
        return values[x] ?: Double.NaN
    }

    private fun loadValues(): Map<Double, Double> {
        val readValues = mutableMapOf<Double, Double>()
        file.bufferedReader().use {
            val header = it.readLine()
            it.lineSequence()
                .filter { it.isNotBlank() }
                .forEach {
                    val (x, fx) = it.split(",", limit = 2)
                    readValues[x.toDouble()] = fx.toDouble()
                }
        }
        return readValues
    }

}