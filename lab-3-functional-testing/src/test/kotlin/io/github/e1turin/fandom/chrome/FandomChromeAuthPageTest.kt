package io.github.e1turin.fandom.chrome

import io.github.e1turin.fandom.common.buildAuthPageTests
import io.github.e1turin.fandom.setupChromeWebDriver
import io.kotest.core.spec.style.StringSpec

object FandomChromeAuthPageTest : StringSpec({
    include(buildAuthPageTests(driverSetup = setupChromeWebDriver()))
})
