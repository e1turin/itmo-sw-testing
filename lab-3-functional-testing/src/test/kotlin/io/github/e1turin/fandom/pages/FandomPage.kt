package io.github.e1turin.fandom.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

abstract class FandomPage(
    protected val driver: WebDriver,
    private val siteUrl: String
) {
    init {
        PageFactory.initElements(driver, this)
    }

    fun title(): String? = driver.title
    fun open(): Unit = driver.get(siteUrl)

    abstract fun status(): PageStatus
}
