package io.github.e1turin.util

public const val ERROR_RATE: Double = 1e-6

/*
 * (-2π;-π)v(-π;0]v(0;1)v(1;5)
 */

public val RANGE_PERIODIC_UP: ClosedRange<Double> = (-2 * Math.PI + 1e-3)..(-Math.PI - 1e-3)
public val RANGE_PERIODIC_DOWN: ClosedRange<Double> = (-Math.PI + 1e-3)..-0.0
public val RANGE_LOGARITHMIC_LESS_THAN_1: ClosedRange<Double> = (0.0 + ERROR_RATE)..(1 - ERROR_RATE)
public val RANGE_LOGARITHMIC_GREATER_THAN_1: ClosedRange<Double> = (1 + ERROR_RATE)..5.0
public val RANGE_INSPECT_STEP: Double = 0.1
