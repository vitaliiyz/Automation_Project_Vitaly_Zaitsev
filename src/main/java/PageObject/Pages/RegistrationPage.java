package PageObject.Pages;

import PageObject.BasePage;
import org.openqa.selenium.By;

public class RegistrationPage extends BasePage {

    private final By passwordField = By.xpath("//input[contains(@placeholder, 'Придумайте пароль')]");
    private final By passwordDescription = By.cssSelector("[class^='auth-form__description auth-form__description_primary']");

    public RegistrationPage enterPassword(String passwordText) {
        enterText(passwordField, passwordText);
        return this;
    }

    public RegistrationPage checkPasswordDescription(String expectedText) {
        assertText(passwordDescription, expectedText);
        return this;
    }
}
