package io.github.e1turin.story


public object Story {
    public fun begin(log: (String) -> Unit = ::println) {
        val motor = Engine()
        val noise = motor.makeNoise()
        while (noise.volume < ROAR_VOLUME) {
            Space.collect(noise.makeLouder(), { log("*silence*") })
        }
        Space.collect(listOf(Ford, Artur), effect = Confetti)
    }
}
