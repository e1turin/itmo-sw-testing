package io.github.e1turin.story

public const val THIN_WHISTLE_VOLUME = 1
public const val ROAR_VOLUME = 100

public open class Noise(volume: Int = THIN_WHISTLE_VOLUME) {
    public var volume: Int = volume
        private set
    public fun makeLouder(log: (String)->Unit = ::println): Int {
        log("a*".repeat(volume))
        return ++volume
    }
}
