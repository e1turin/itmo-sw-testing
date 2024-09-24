package io.github.e1turin.fandom.common

import io.github.e1turin.fandom.getFandomProfileLogin
import io.github.e1turin.fandom.getFandomProfilePassword
import io.github.e1turin.fandom.pages.*
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

fun buildAuthPageTests(driverSetup: WebDriver) = stringSpec {
    lateinit var driver: WebDriver

    val randomSiteURL = "https://matrix.fandom.com/wiki/Neo"
    val someFandomPage = object : FandomPage(driver, randomSiteURL) {
        override fun status(): PageStatus = onThisPageIf(false)
    }

    beforeTest {
        driver = driverSetup
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
                hitSignInButton()
            }
            status() shouldBe CurrentPage
        }

    }
}
