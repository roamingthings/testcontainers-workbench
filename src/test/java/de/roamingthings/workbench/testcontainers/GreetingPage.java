package de.roamingthings.workbench.testcontainers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GreetingPage extends PageObject {

    @FindBy(id = "name")
    private WebElement nameField;

    public GreetingPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return nameField.isDisplayed();
    }

    public String name() {
        return nameField.getText();
    }

}
