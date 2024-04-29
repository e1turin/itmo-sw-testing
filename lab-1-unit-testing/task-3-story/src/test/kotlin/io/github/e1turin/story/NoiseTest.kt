package io.github.e1turin.story

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class NoiseTest : StringSpec({
    "increase volume semi-invariant" {
        Arb.int(min = THIN_WHISTLE_VOLUME, max = ROAR_VOLUME).checkAll { i ->
            val n = Noise(i)
            val previousVolume = n.volume
            val newVolume = n.makeLouder()
            n.volume shouldBeEqual previousVolume + 1
            newVolume shouldBeEqual previousVolume + 1
        }
    }
    "side effect appeared" {
        Arb.int(min = THIN_WHISTLE_VOLUME, max = ROAR_VOLUME).checkAll { i ->
            var cntSideEffect = 0
            val n = Noise(i)
            val previousVolume = n.volume
            n.makeLouder(log = { s: String -> cntSideEffect++ })
            cntSideEffect shouldBeEqual 1
        }
    }
})