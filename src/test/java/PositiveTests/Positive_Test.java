package PositiveTests;

import BaseObjects.BaseTest;
import PageObject.Pages.RegistrationPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Positive_Test extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        get(RegistrationPage.class)
                .openRegistrationPage();
    }

    @Test(priority = 1, description = "checking boundary values in password field",
            enabled = true)
    public void boundaryValues_Test() {
        get(RegistrationPage.class)
                .enterPassword("1234567")
                .checkPasswordDescription("Минимум 8 символов")
                .clearPasswordField()
                .enterPassword("12345678")
                .checkPasswordDescription("Ненадежный пароль, 8 символов");
    }

}
