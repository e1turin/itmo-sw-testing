package io.github.e1turin.fandom.firefox

import io.github.e1turin.fandom.common.buildAuthPageTests
import io.github.e1turin.fandom.setupFirefoxWebDriver
import io.kotest.core.spec.style.StringSpec

object FandomFirefoxAuthPageTest: StringSpec({
    include(buildAuthPageTests(driverSetup = setupFirefoxWebDriver()))
})
