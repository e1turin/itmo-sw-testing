package io.github.e1turin.story

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class SpaceTest : StringSpec({
    "collects one thing once" {
        Arb.int(min = THIN_WHISTLE_VOLUME, max = ROAR_VOLUME).checkAll { i ->
            var cntUnstatedSideEffect = 0
            var cntStatedSideEffect = 0
            val effect = { thing: Any -> ++cntStatedSideEffect }
            Space.collect(
                Noise(i).makeLouder(log = {}),
                effect = effect,
                log = { s: String -> ++cntUnstatedSideEffect }
            )
            cntStatedSideEffect shouldBeEqual 1
            cntUnstatedSideEffect shouldBeEqual 1
        }
    }
    "collects everything" {
        characterArb.checkAll { characters ->
            var cntUnstatedSideEffect = 0
            var cntStatedSideEffect = 0
            val effect = { thing: Any -> ++cntStatedSideEffect }
            Space.collectAll(
                characters,
                effect = effect,
                log = { s: String -> ++cntUnstatedSideEffect }
            )
            cntStatedSideEffect shouldBeEqual characters.size
            cntUnstatedSideEffect shouldBeEqual 1
        }

    }
})