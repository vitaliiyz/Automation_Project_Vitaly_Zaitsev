package PageObject.Pages;

import PageObject.Base_Page;
import org.openqa.selenium.By;

public class Registration_Page extends Base_Page {

    private final By passwordField = findByXpath("//input[contains(@placeholder, 'Придумайте пароль')]");
    private final By passwordDescription = findByCss("[class^='auth-form__description auth-form__description_primary']");
    private final By emailField = findByCss("[type = email]");
    private final By emailDescription = findByCss(".auth-form__description_extended-other");

    public Registration_Page enterPassword(String passwordText) {
        logger.debug("Enter password " + passwordText);
        sendKeys(passwordField, passwordText);
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

    public Registration_Page enterEmail(String email) {
        logger.debug("Enter email" + email);
        sendKeys(emailField, email);
        return this;
    }

    public Registration_Page checkEmailDescription(String expectedEmailDescr) {
        logger.debug("Check email description");
        assertText(emailDescription, expectedEmailDescr);
        return this;
    }

}
