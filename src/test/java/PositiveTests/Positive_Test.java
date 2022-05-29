package PositiveTests;

import BaseObjects.BaseTest;
import PageObject.Pages.Home_Page;
import PageObject.Pages.MobileCatalog_Page;
import PageObject.Pages.Registration_Page;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Positive_Test extends BaseTest {

    @Parameters("page")
    @BeforeMethod(description = "Opening page from test parameter")
    public void preconditions(String page) {
        get(Home_Page.class).open(page);
    }

    @Test(priority = 1, description = "checking boundary values in password field", enabled = true)
    public void boundaryValues_Test() {
        get(Registration_Page.class)
                .enterPassword("1234567")
                .checkPasswordDescription("Минимум 8 символов")
                .clearPasswordField()
                .enterPassword("12345678")
                .checkPasswordDescription("Ненадежный пароль, 8 символов");
    }

    @Test(priority = 2, description = "checking dialog window", enabled = true)
    public void dialogWindow_Test() {
        get(MobileCatalog_Page.class)
                .checkLocalityPopupText("Ваш населенный пункт — Минск?\n" + "Да, верно Нет, другой")
                .clickLocalityYesButton();
    }

    @Test(priority = 3, description = "checking popup message", enabled = true)
    public void popupMessage_Test() {
        get(MobileCatalog_Page.class)
                .clickOnLabel()
                .checkPopoverText("Суперцена\n" +
                        "Мы каждый день анализируем цены и помечаем все выгодные предложения за последние 30 дней");
    }

    @Test(priority = 4, description = "adding product to cart", enabled = true)
    public void addProductToCart_Test() {
        String productName = "Huawei";

        get(MobileCatalog_Page.class)
                .clickProductLink(productName)
                .clickAddToCartBtn()
                .clickGoToCartBtn()
                .checkProductInCart(productName);
    }

    @Test(priority = 5, description = "removing product from cart", enabled = true, dependsOnMethods = "addProductToCart_Test")
    public void removeProductFromCart_Test() {
        get(MobileCatalog_Page.class)
                .removeProductFromCart()
                .checkTextAfterRemovingProduct("Вы удалили")
                .clickCloseRemovedProductLink()
                .checkCartTitleText("Ваша корзина пуста");
    }

}
