package io.github.e1turin.collection

public interface Stack<T> {
    public val size: Int
    public fun isEmpty(): Boolean
    public fun isNotEmpty(): Boolean
    public fun push(item: T): Boolean
    public fun pop(): Boolean
    public fun top(): T
    public fun peek(idx: Int): T
}

