package io.github.e1turin.fandom

import io.kotest.core.spec.AfterEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

fun setupChromeWebDriver() = ChromeDriver().apply {
    setup()
}

fun WebDriver.setup() {
    // setup WebDriver
}

fun afterEachTemplate(driver: WebDriver, also: (() -> Unit)? = null): AfterEach {
    return {
        driver.quit()
        also?.invoke()
    }
}