package io.github.e1turin.fandom.pages

import io.kotest.property.arbitrary.ByteShrinker
import org.openqa.selenium.By
import org.openqa.selenium.InvalidArgumentException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
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

    val signInButton: By = By.xpath("//*[@id=\"global-sign-in-link\"]")

    protected fun By.find(): WebElement = driver.findElement(this)

    fun title(): String = driver.title ?: ""
    fun open(): Unit = siteUrl?.let { driver.get(it) }
        ?: throw InvalidArgumentException("The URL of the web page site to open is not specified")

    fun goToHome() = Navigation.home.find().click()
    fun openSearchBar() = Navigation.search.find().click()
    fun createNewWiki() = Navigation.startWiki.find().click()

    fun startSignIn() = signInButton.find().click()
}
