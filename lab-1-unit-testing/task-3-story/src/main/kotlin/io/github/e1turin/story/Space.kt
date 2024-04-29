package io.github.e1turin.story

public object Space {
    public fun <T: Any, O> collect(thing: T, effect: Effect<T, O>, log: (String)->Unit = ::println) {
        log("The ${effect.apply(thing)} was thrown away in space 🌟")
    }
    public fun <T : Any, O> collectAll(things: Collection<T>, effect: Effect<T, O>, log: (String)->Unit = ::println): Unit {
        log(
            "The ${
                things.map(effect::apply).joinToString(separator = ", ") { it.toString() }
            } was thrown away in space ✨"
        )
    }
    public val decoration: Iterable<Point> = object : Iterable<Point> {
        override fun iterator(): Iterator<Point> = object : Iterator<Point> {
            override fun hasNext(): Boolean = true
            override fun next() = Point()
        }
    }
}

public class Point {
    public val color: ByteArray = ByteArray(3) { 255.toByte() }
}
