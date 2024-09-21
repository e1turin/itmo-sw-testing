package io.github.e1turin.fandom.chrome

import io.github.e1turin.fandom.afterEachTemplate
import io.github.e1turin.fandom.pages.Current
import io.github.e1turin.fandom.pages.FandomMainPage
import io.github.e1turin.fandom.setupChromeWebDriver
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.openqa.selenium.WebDriver

class FandomChromeMainPageTest : StringSpec({
    lateinit var driver: WebDriver

    beforeEach {
        driver = setupChromeWebDriver()
    }

    afterEach(afterEachTemplate(driver))

    "Open main page" {
        with(FandomMainPage(driver)) {
            open()
            status() shouldBe Current
        }
    }
})
