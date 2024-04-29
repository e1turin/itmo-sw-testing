package io.github.e1turin.story

public open class Engine {
    public fun makeNoise(): Noise {
       return Noise(THIN_WHISTLE_VOLUME)
    }
}
