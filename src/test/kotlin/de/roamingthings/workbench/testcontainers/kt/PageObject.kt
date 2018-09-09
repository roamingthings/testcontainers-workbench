package de.roamingthings.workbench.testcontainers.kt

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

open class PageObject(protected var driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }
}
