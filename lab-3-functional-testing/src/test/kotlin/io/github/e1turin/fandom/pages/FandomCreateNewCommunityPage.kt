package io.github.e1turin.fandom.pages

import org.openqa.selenium.WebDriver

const val createNewCommunityPageUrl = "https://createnewwiki.fandom.com/wiki/Special:CreateNewWiki"

class FandomCreateNewCommunityPage(driver: WebDriver): FandomPage(driver, createNewCommunityPageUrl) {
    private val expectedTitle = "Create New Community"
    override fun status(): PageStatus = onThisPageIf(title().contains(expectedTitle))
}
