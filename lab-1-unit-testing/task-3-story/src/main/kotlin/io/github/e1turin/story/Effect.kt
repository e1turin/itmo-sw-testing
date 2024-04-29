package io.github.e1turin.story


public fun interface Effect <in T, out R> {
    public fun apply(to: T): R
}

public object Confetti : Effect<Any, String> {
    override fun apply(to: Any): String = "🎊 $to 🎊"
}
