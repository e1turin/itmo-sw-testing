package io.github.e1turin.fandom

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.time.Duration

fun WebDriver.setup() {
    with(manage()) {
        timeouts().implicitlyWait(Duration.ofSeconds(2))
    }
}

fun setupChromeWebDriver() = ChromeDriver().apply {
    setup()
}

fun setupFirefoxWebDriver() = FirefoxDriver().apply {
    setup()
}
