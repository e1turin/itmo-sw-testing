package io.github.e1turin.fandom.common

import io.github.e1turin.fandom.pages.*
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

fun buildTestSuite(driverSetup: WebDriver) = stringSpec {
    lateinit var driver: WebDriver

    beforeTest {
        driver = driverSetup
    }

    afterTest {
        driver.quit()
    }

    fun onMainPage(steps: FandomMainPage.() -> Unit) {
        val mainPage = FandomMainPage(driver)
        with(mainPage) {
            open()
            steps()
        }
    }

    fun By.find(): WebElement = driver.findElement(this)

    "Open main page" {
        onMainPage {
            status() shouldBe CurrentPage
        }
    }

    "Go to main page and remain here" {
        onMainPage {
            goToHome()
            status() shouldBe CurrentPage
        }
    }

    "Open search bar and start searching" {
        val someSearchInputText = "kek"
        onMainPage {
            FandomPage.Navigation.search.find().click()
            FandomPage.Navigation.searchBarInput.find().apply {
                sendKeys(someSearchInputText, "\n")
            }
        }
        val searchResultPage = FandomSearchResultPage(driver)
        searchResultPage.status() shouldBe CurrentPage
    }

    "Start new wiki and create new community" {
        onMainPage {
            createNewWiki()
        }
        val createNewWikiPage = FandomCreateNewCommunityPage(driver)
        createNewWikiPage.status() shouldBe CurrentPage
    }

    "Go to sign in page" {
        onMainPage {
            startSignIn()
        }
        val authPage = FandomAuthPage(driver)
        authPage.status() shouldBe CurrentPage
    }
}
