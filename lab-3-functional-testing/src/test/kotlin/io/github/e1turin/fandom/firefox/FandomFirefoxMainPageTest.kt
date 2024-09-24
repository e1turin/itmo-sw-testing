package io.github.e1turin.fandom.firefox

import io.github.e1turin.fandom.common.buildMainPageTests
import io.github.e1turin.fandom.setupFirefoxWebDriver
import io.kotest.core.spec.style.StringSpec

class FandomFirefoxMainPageTest : StringSpec({
    include(buildMainPageTests(driverSetup = setupFirefoxWebDriver()))
})
