package io.github.e1turin.fandom.pages

import org.openqa.selenium.By
import org.openqa.selenium.InvalidArgumentException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

abstract class FandomPage(
    protected val driver: WebDriver,
    private val siteUrl: String? = null
) {
    init {
        PageFactory.initElements(driver, this)
    }

    abstract fun status(): PageStatus

    object Navigation {
        val home: By = By.xpath("//a[@class='global-navigation__logo']")
        val search: By = By.xpath("/html/body/div[2]/div[1]/nav/a[2]")
        val searchBarInput: By = By.cssSelector(".search-input")
        val wikis: By = By.xpath("//div[@data-tracking-label='link.wikis']")
        val wikisExploreWikis: By = By.cssSelector(
            "div.global-navigation__link > div:nth-child(2) >"
                    + " ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"
        )
        val startWiki: By = By.xpath("//a[contains(@href, 'createnewwiki')]")
    }

    fun title(): String = driver.title ?: ""
    fun open(): Unit = siteUrl?.let { driver.get(it) }
        ?: throw InvalidArgumentException("The URL of the web page site to open is not specified")

    fun goToHome() = driver.findElement(Navigation.home).click()
    fun openSearchBar() = driver.findElement(Navigation.search).click()
    fun createNewWiki() = driver.findElement(Navigation.startWiki).click()
}
