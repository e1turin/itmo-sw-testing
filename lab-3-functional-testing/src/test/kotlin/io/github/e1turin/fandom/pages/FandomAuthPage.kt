package io.github.e1turin.fandom.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

const val fandomAuthPageUrl = "https://auth.fandom.com/signin"

class FandomAuthPage(driver:WebDriver): FandomPage(driver, fandomAuthPageUrl) {
    private val expectedTitle = "Fandom Auth"
    override fun status(): PageStatus = onThisPageIf(title() == expectedTitle)

    val loginInput: By = By.xpath("//*[@id=\"identifier\"]")
    val passwordInput: By = By.xpath("//*[@id=\"password\"]")
    val signInFormButton: By = By.xpath("//*[@id=\"method\"]/parent::div") // interactable is not button but parent div

    fun enterLogin(login: String) = loginInput.find().sendKeys(login)
    fun enterPassword(password: String) = passwordInput.find().sendKeys(password)
    fun hitSignInButton() = signInFormButton.find().click()
}
