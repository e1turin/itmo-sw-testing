package io.github.e1turin.fandom.pages

import org.openqa.selenium.WebDriver

const val fandomMainPageUrl = "https://www.fandom.com/"

class FandomMainPage(driver: WebDriver): FandomPage(driver, fandomMainPageUrl) {
    private val expectedTitle: String = "Fandom"

    override fun status(): PageStatus = onThisPageIf(title() == expectedTitle)
}
