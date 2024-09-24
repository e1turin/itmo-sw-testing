package io.github.e1turin.fandom.common

import io.github.e1turin.fandom.pages.*
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.shouldBe
import org.openqa.selenium.WebDriver

fun buildMainPageTests(driverSetup: WebDriver) = stringSpec {
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

    fun onMainPage(steps: FandomMainPage.() -> Unit) {
        val mainPage = FandomMainPage(driver)
        with(mainPage) {
            open()
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

    "Open main page" {
        onMainPage {
            status() shouldBe CurrentPage
        }
    }

    "Open main page and remain here after recursive navigation" {
        onMainPage {
            goToHome()
            status() shouldBe CurrentPage
        }
    }

    "Go to main page from random page" {
        onSomePage {
            goToHome()
        }
        FandomMainPage(driver).status() shouldBe CurrentPage
    }

    "Open search bar and start searching" {
        val someSearchInputText = "kek"
        onMainPage {
            openSearchBar()
            search(someSearchInputText)
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
