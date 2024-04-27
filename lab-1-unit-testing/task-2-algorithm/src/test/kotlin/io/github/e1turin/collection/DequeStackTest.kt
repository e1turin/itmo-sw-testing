package io.github.e1turin.collection

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DequeStackTest {

    @Test
    fun `save order`() {
        val stack = DequeStack<Int>()
        for (i in 0..9) {
            stack.push(i)
        }

        for (i in 9 downTo 0) {
            assertEquals(i, stack.top())
            assertTrue(stack.pop())
        }
    }
}