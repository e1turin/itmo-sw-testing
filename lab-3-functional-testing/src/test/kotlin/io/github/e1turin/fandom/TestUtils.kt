package io.github.e1turin.fandom

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.time.Duration

fun WebDriver.setup() {
    with(manage()) {
        timeouts().implicitlyWait(Duration.ofSeconds(5))
    }
}

fun setupChromeWebDriver() = ChromeDriver().apply {
    setup()
}

fun setupFirefoxWebDriver() = FirefoxDriver().apply {
    setup()
}

const val fandomProfileLoginEnvKey = "fandom_auth_login"
const val fandomProfilePasswordEnvKey = "fandom_auth_password"

fun getFandomProfileLogin(): String {
    return System.getenv(fandomProfileLoginEnvKey)
        ?: throw IllegalArgumentException("No '$fandomProfileLoginEnvKey' environment variable found")
}
fun getFandomProfilePassword(): String {
    return System.getenv(fandomProfilePasswordEnvKey)
        ?: throw IllegalArgumentException("No '$fandomProfilePasswordEnvKey' environment variable found")
}
