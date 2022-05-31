package NegativeTest;

import BaseObjects.BaseTest;
import PageObject.Pages.Home_Page;
import PageObject.Pages.Registration_Page;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Negative_Test extends BaseTest {

    @Parameters("page")
    @BeforeMethod(description = "Opening page from test parameter")
    public void preconditions(String page) {
        get(Home_Page.class).open(page);
    }

    @Test(priority = 1, description = "Login with incorrect data", enabled = true)
    public void incorrectLogin_Test() {
        get(Home_Page.class)
                .clickLoginButtonOnHomePage()
                .enterLogin("asdqwezxc")
                .enterPassword("22446688")
                .clickLoginButton()
                .checkErrorText("Неверный логин или пароль");
    }

    @Test(priority = 1, description = "Email exceeds the number of allowed characters", enabled = true)
    public void emailMoreThanAllowedCharacters_Test() {
        get(Registration_Page.class)
                .enterEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@mail.ru")
                .checkEmailDescription("Некорректный e-mail");
    }
}
