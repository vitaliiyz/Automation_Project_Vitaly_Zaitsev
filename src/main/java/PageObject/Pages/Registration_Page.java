package PageObject.Pages;

import PageObject.Base_Page;
import org.openqa.selenium.By;

public class Registration_Page extends Base_Page {

    private final By passwordField = findByXpath("//input[contains(@placeholder, 'Придумайте пароль')]");
    private final By passwordDescription = findByCss("[class^='auth-form__description auth-form__description_primary']");

    public Registration_Page enterPassword(String passwordText) {
        logger.debug("Enter password " + passwordText);
        enterText(passwordField, passwordText);
        return this;
    }

    public Registration_Page checkPasswordDescription(String expectedPswrdDescr) {
        logger.debug("Check password description");
        assertTrue(findElement(passwordDescription).getText().contains(expectedPswrdDescr));
        return this;
    }

    public Registration_Page clearPasswordField() {
        logger.debug("Clear password field");
        clearTextArea(passwordField);
        return this;
    }

}
