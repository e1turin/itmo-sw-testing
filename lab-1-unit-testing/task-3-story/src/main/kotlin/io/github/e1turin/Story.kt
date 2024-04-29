package io.github.e1turin


public open class Engine {
    public fun makeNoise(): Noise {
       return Noise(THIN_WHISTLE_VOLUME)
    }
}

public const val THIN_WHISTLE_VOLUME = 1
public const val ROAR_VOLUME = 100

public open class Noise(volume: Int = 0) {
    public var volume = volume
        private set
    public fun makeLouder(): Int {
        println("a*".repeat(volume))
        return ++volume
    }
}

public open class Character(public val name: String)

public data object Ford : Character("Ford")
public data object Artur : Character("Artur")

public class Point {
    public val color: ByteArray = ByteArray(3) { 255.toByte() }
}

public object Space {
    public fun <T: Any, O> collect(thing: T, effect: Effect<T, O>) {
        println("The ${effect.apply(thing)} was thrown away in space 🌟")
    }
    public fun <T : Any, O> collectAll(things: Collection<T>, effect: Effect<T, O>): Unit {
        println(
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

public fun interface Effect <in T, out R> {
    public fun apply(to: T): R
}

public object Confetti : Effect<Any, String> {
    override fun apply(to: Any): String = "🎊 $to 🎊"
}

public object Story {
    public fun begin() {
        val motor = Engine()
        val noise = motor.makeNoise()
        while (noise.volume < ROAR_VOLUME) {
            Space.collect(noise.makeLouder()) { println("*silence*") }
        }
        Space.collect(listOf(Ford, Artur), effect = Confetti)
    }
}