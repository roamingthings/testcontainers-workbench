package de.roamingthings.workbench.testcontainers.kt

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class WelcomePage(driver: WebDriver) : PageObject(driver) {

    @FindBy(id = "name")
    private val nameField: WebElement? = null

    @FindBy(id = "submit")
    private val submitButton: WebElement? = null

    val isInitialized: Boolean
        get() = submitButton!!.isDisplayed

    fun submit(): GreetingPage {
        submitButton!!.click()
        return GreetingPage(driver)
    }

    fun enterName(name: String) {
        nameField!!.clear()
        nameField.sendKeys(name)
    }

    fun name(): String {
        return nameField!!.getAttribute("value")
    }

}
