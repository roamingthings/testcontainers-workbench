package de.roamingthings.workbench.testcontainers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends PageObject {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return submitButton.isDisplayed();
    }

    public GreetingPage submit(){
        submitButton.click();
        return new GreetingPage(driver);
    }

    public void enterName(String name) {
        nameField.clear();
        nameField.sendKeys(name);
    }

    public String name() {
        return nameField.getAttribute("value");
    }

}
