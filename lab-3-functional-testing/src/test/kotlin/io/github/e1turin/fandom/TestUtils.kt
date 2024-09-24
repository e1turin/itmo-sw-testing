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

const val fandomProfileLoginEnvKey = "fandom-login"
const val fandomProfilePasswordEnvKey = "fandom-password"

fun getFandomProfileLogin(): String {
    return System.getenv(fandomProfileLoginEnvKey)
        ?: throw IllegalArgumentException("No '$fandomProfileLoginEnvKey' environment variable found")
}
fun getFandomProfilePassword(): String {
    return System.getenv(fandomProfilePasswordEnvKey)
        ?: throw IllegalArgumentException("No '$fandomProfilePasswordEnvKey' environment variable found")
}
