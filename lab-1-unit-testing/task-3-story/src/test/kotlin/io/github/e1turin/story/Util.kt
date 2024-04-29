package io.github.e1turin.story

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.string
import kotlin.random.nextInt

val characterArb = arbitrary {
    buildList<Character> {
        for (i in 0..it.random.nextInt(0..1000))
            add(Character(Arb.string().bind()))
    }
}
