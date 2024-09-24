package io.github.e1turin.fandom.common

import io.github.e1turin.fandom.getFandomProfileLogin
import io.github.e1turin.fandom.getFandomProfilePassword
import io.github.e1turin.fandom.pages.*
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.ElementNotInteractableException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.Wait
import java.time.Duration


fun buildAuthPageTests(driverSetup: WebDriver) = stringSpec {
    lateinit var driver: WebDriver
    lateinit var someFandomPage: FandomPage

    val randomSiteURL = "https://matrix.fandom.com/wiki/Neo"

    beforeTest {
        driver = driverSetup
        someFandomPage = object : FandomPage(driver, randomSiteURL) {
            override fun status(): PageStatus = onThisPageIf(title().contains("Neo"))
        }
    }

    afterTest {
        driver.quit()
    }

    fun By.find(): WebElement = driver.findElement(this)
    fun onAuthPage(steps: FandomAuthPage.() -> Unit) {
        val authPage = FandomAuthPage(driver)
        with(authPage) {
            open()
            authPage.status() shouldBe CurrentPage
            steps()
        }
    }
    fun onSomePage(steps: FandomPage.() -> Unit) {
        with(someFandomPage) {
            open()
            someFandomPage.status() shouldBe CurrentPage
            steps()
        }
    }

    "Successful signing in" {
        onSomePage {
            startSignIn()
            onAuthPage {
                enterLogin(getFandomProfileLogin())
                enterPassword(getFandomProfilePassword())

                FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(ElementNotInteractableException::class.java)
                    .until<Boolean> {
                        hitSignInButton()
                        true
                    }
            }
            // strange behaviour in chrome under selenium driver, redirects to main page:
            status() shouldBe NotCurrentPage
        }
        val mainPage = FandomMainPage(driver)

        FluentWait(driver)
            .withTimeout(Duration.ofSeconds(5))
            .pollingEvery(Duration.ofMillis(300))
            .until<Boolean> {
                mainPage.status() == CurrentPage
            }

        mainPage.status() shouldBe CurrentPage
    }
}
