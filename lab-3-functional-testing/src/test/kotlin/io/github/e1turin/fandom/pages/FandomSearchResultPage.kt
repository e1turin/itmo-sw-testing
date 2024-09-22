package io.github.e1turin.fandom.pages

import org.openqa.selenium.WebDriver

class FandomSearchResultPage(driver: WebDriver): FandomPage(driver) {
    private val expectedTitle = "Search Results"
    override fun status(): PageStatus = onThisPageIf(title().contains(expectedTitle))
}
