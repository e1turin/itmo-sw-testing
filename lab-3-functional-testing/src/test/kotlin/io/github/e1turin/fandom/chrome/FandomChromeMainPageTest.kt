package io.github.e1turin.fandom.chrome

import io.github.e1turin.fandom.pages.*
import io.github.e1turin.fandom.setupChromeWebDriver
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

object FandomChromeMainPageTest : StringSpec({
    lateinit var driver: WebDriver

    beforeTest {
        driver = setupChromeWebDriver()
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
                sendKeys(someSearchInputText)
                sendKeys("\n")
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
})
