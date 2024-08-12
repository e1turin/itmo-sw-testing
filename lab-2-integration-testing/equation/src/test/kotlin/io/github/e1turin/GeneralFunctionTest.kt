package io.github.e1turin

import io.github.e1turin.util.ERROR_RATE
import io.github.e1turin.util.RANGE_INSPECT_STEP
import io.github.e1turin.util.RANGE_PERIODIC_UP
import io.github.e1turin.util.logToCsv
import io.github.e1turin.util.logCompleteFunctionToCsv
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll
import java.io.File
import kotlin.math.*

class GeneralFunctionTest : StringSpec({

    val goldenLn = ::ln
    val goldenSin = ::sin
    val goldenLog2 = ::log2
    val goldenLog3 = { x: Double -> log(x, 3.0) }
    val goldenLog5 = { x: Double -> log(x, 5.0) }
    val goldenLog10 = ::log10
    val goldenCos = ::cos
    val goldenCsc = { x: Double -> 1 / sin(x) }

    val goldenGeneralFunction = GeneralFunction(
        ln = goldenLn,
        sin = goldenSin,
        log2 = goldenLog2,
        log3 = goldenLog3,
        log5 = goldenLog5,
        log10 = goldenLog10,
        cos = goldenCos,
        csc = goldenCsc,
    )

    val testingLn = { x: Double -> io.github.e1turin.logarithmic.ln(x, ERROR_RATE) }
    val testingSin = { x: Double -> io.github.e1turin.trigonometric.sin(x, ERROR_RATE) }
    val testingLog2 = { x: Double -> io.github.e1turin.logarithmic.log2(x, ERROR_RATE) }
    val testingLog3 = { x: Double -> io.github.e1turin.logarithmic.log3(x, ERROR_RATE) }
    val testingLog5 = { x: Double -> io.github.e1turin.logarithmic.log5(x, ERROR_RATE) }
    val testingLog10 = { x: Double -> io.github.e1turin.logarithmic.log10(x, ERROR_RATE) }
    val testingCos = { x: Double -> io.github.e1turin.trigonometric.cos(x, ERROR_RATE) }
    val testingCsc = { x: Double -> io.github.e1turin.trigonometric.csc(x, ERROR_RATE) }

    "Hand test strange values" {
        val func = GeneralFunction()
        func(1.0) shouldBe Double.NaN
    }

    "Test general function with stdlib implementation" {
        val func = GeneralFunction()
        Arb.double().checkAll { d ->
            val res = func(d)
            print(d)
            print(' ')
            println(res)
            if (d > 0 && d != 1.0) res shouldBeIn -ERROR_RATE..ERROR_RATE
        }
    }

    "Inspect domain ranges" {
        val generalFunction = GeneralFunction()

        val filePeriodicUp = File("test/resources/GeneralFunction-full_stdlib-RANGE_PERIODIC_UP.csv")
            .also { it.createNewFile() }

        logToCsv(
            file = filePeriodicUp,
            range = RANGE_PERIODIC_UP,
            step = RANGE_INSPECT_STEP,
            functionName = "GeneralFunction(x)",
            function = generalFunction
        )
    }

    "Dump golden impl values" {
        val file = File("src/test/resources/GeneralFunction_golden.csv")
            .also { it.createNewFile() }

        logCompleteFunctionToCsv(
            file = file,
            functionName = "generalFunction",
            function = goldenGeneralFunction
        )
    }

    "Automatic testing & logging for various implementations" {
        val binarySequenceGenerator = true
        val generalFunction = GeneralFunction(
            ln = if (binarySequenceGenerator) goldenLn else testingLn,
            sin = if (binarySequenceGenerator) goldenSin else testingSin,
            log2 = if (binarySequenceGenerator) goldenLog2 else testingLog2,
            log3 = if (binarySequenceGenerator) goldenLog3 else testingLog3,
            log5 = if (binarySequenceGenerator) goldenLog5 else testingLog5,
            log10 = if (binarySequenceGenerator) goldenLog10 else testingLog10,
            cos = if (binarySequenceGenerator) goldenCos else testingCos,
            csc = if (binarySequenceGenerator) goldenCsc else testingCsc,
        )
    }
})