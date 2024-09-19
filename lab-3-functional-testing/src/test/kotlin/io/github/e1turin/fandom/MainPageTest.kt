package io.github.e1turin.fandom

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldContain
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class FandomChromeTest : StringSpec({
    lateinit var driver: WebDriver

    beforeEach {
        driver = ChromeDriver()
    }

    afterEach {
        driver.quit()
    }

    "Open main page" {
        driver.get(FandomWebPage.siteUrl);

        val title = driver.title;

        title shouldContain "Fandom"
    }
})
