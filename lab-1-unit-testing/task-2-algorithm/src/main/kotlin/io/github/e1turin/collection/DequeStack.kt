package io.github.e1turin.collection

public class DequeStack<T> : Stack<T> {
    private val deque: ArrayDeque<T>// ArrayDeque<T>(collection)

    public constructor() {
        deque = ArrayDeque()
    }

    public constructor(collection: Collection<T>) {
        deque = ArrayDeque(collection)
    }

    public constructor(size: Int) {
        deque = ArrayDeque(size)
    }

    override val size: Int
        get() = deque.size

    override fun isEmpty(): Boolean {
        return deque.isEmpty()
    }
    public override fun isNotEmpty(): Boolean = !deque.isEmpty()

    override fun push(item: T): Boolean {
        return deque.add(item)
    }

    override fun pop(): Boolean {
        return deque.removeLastOrNull() != null
    }

    override fun top(): T {
        return deque.last()
    }

    override fun peek(idx: Int): T {
        return deque.get(deque.size - 1 - idx)
    }
}