package io.github.e1turin.fandom.chrome

import io.github.e1turin.fandom.common.buildMainPageTests
import io.github.e1turin.fandom.setupChromeWebDriver
import io.kotest.core.spec.style.StringSpec

class FandomChromeMainPageTest : StringSpec({
    include(buildMainPageTests(driverSetup = setupChromeWebDriver()))
})
