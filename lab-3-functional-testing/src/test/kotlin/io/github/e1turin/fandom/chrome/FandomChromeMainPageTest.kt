package io.github.e1turin.fandom.chrome

import io.github.e1turin.fandom.common.buildTestSuite
import io.github.e1turin.fandom.setupChromeWebDriver
import io.kotest.core.spec.style.StringSpec


object FandomChromeMainPageTest : StringSpec({
    include(buildTestSuite(driverSetup = setupChromeWebDriver()))
})
