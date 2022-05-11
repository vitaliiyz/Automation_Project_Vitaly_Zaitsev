package PageObject.Pages;

import PageObject.BasePage;
import org.openqa.selenium.By;

public class RegistrationPage extends BasePage {

    private final By passwordField = By.xpath("//input[contains(@placeholder, 'Придумайте пароль')]");
    private final By passwordDescription = By.cssSelector("[class^='auth-form__description auth-form__description_primary']");

    public RegistrationPage enterPassword(String passwordText) {
        logger.debug("Enter password " + passwordText);
        enterText(passwordField, passwordText);
        return this;
    }

    public RegistrationPage checkPasswordDescription(String expectedPswrdDescr) {
        logger.debug("Check password description");
        assertTrue(findElement(passwordDescription).getText().contains(expectedPswrdDescr));
        return this;
    }

    public RegistrationPage openRegistrationPage() {
        open(properties.getProperty("registrationPageUrl"));
        return this;
    }

    public RegistrationPage clearPasswordField() {
        logger.debug("Clear password field");
        clearTextArea(passwordField);
        return this;
    }

}
