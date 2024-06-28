package io.github.e1turin

import io.github.e1turin.util.ERROR_RATE
import io.github.e1turin.util.RANGE_INSPECT_STEP
import io.github.e1turin.util.RANGE_PERIODIC_UP
import io.github.e1turin.util.logToCsv
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll
import java.io.File

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

    fun logFunctionToCsv(){ TODO() }

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
})