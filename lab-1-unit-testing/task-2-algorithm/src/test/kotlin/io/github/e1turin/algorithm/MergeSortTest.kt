package io.github.e1turin.algorithm

import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.checkAll
import io.kotest.property.forAll
import kotlin.random.nextInt

fun sortedListPermutationArb(size: Int) = arbitrary {
    val length = it.random.nextInt(0..size)
    val sortedList = mutableListOf<Int>().apply {
        var largest: Int = it.random.nextInt()
        for (i in 0 until length) {
            largest = it.random.nextInt(largest..<Int.MAX_VALUE)
            add(largest)
        }
    }
    sortedList to sortedList.shuffled()
}

@OptIn(ExperimentalKotest::class)
class MergeSortTest : StringSpec({


    "list size invariant" {
        Arb.list(Arb.int()).forAll() { list ->
            list.size == mergeSort(list).size
        }
    }

    "permutations of sorted list" {
        // wtf: PropTestConfig(seed = 3395651651490839401) ??? failed and repaired???
        sortedListPermutationArb(size = 1000).checkAll { (orig, perm) ->
            mergeSort(perm).forEachIndexed { i, it ->
                it shouldBeEqual orig[i]
            }
        }
    }
})
