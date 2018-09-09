package de.roamingthings.workbench.testcontainers.kt

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class GreetingPage(driver: WebDriver) : PageObject(driver) {

    @FindBy(id = "name")
    private val nameField: WebElement? = null

    val isInitialized: Boolean
        get() = nameField!!.isDisplayed

    fun name(): String {
        return nameField!!.text
    }

}
