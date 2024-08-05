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

    "Test golden impl" {
        val generalFunction = GeneralFunction(
            ln = ::ln,
            sin = ::sin,
            log2 = ::log2,
            log3 = { x -> log(x, 3.0) },
            log5 = { x -> log(x, 5.0) },
            log10 = ::log10,
            cos = ::cos,
            csc = { x -> 1 / sin(x) },
        )

        val file = File("src/test/resources/GeneralFunction_golden.csv")
            .also { it.createNewFile() }

        logCompleteFunctionToCsv(
            file = file,
            functionName = "generalFunction",
            function = generalFunction
        )
    }
})