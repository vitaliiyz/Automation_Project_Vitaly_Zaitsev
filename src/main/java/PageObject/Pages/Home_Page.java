package PageObject.Pages;

import PageObject.Base_Page;
import org.openqa.selenium.By;

public class Home_Page extends Base_Page {

    private final By loginButtonOnHomePage = findByCss(".auth-bar__item--text");
    private final By loginInput = findByCss("[placeholder='Ник или e-mail']");
    private final By passwordInput = findByCss("[type=password]");
    private final By loginButton = findByCss(".auth-form__button_width_full");
    private final By loginError = findByCss("[class*='auth-form__description_error']");

    public Home_Page clickLoginButtonOnHomePage() {
        logger.debug("going to login form");
        clickButton(loginButtonOnHomePage);
        return this;
    }

    public Home_Page enterLogin(String login) {
        logger.debug("entering login");
        sendKeys(loginInput, login);
        return this;
    }

    public Home_Page enterPassword(String password) {
        logger.debug("entering password");
        sendKeys(passwordInput, password);
        return this;
    }

    public Home_Page clickLoginButton() {
        logger.debug("clicking login button");
        clickButton(loginButton);
        return this;
    }

    public Home_Page checkErrorText(String expectedText) {
        assertTrue(getText(loginError).contains(expectedText));
        return this;
    }

}
