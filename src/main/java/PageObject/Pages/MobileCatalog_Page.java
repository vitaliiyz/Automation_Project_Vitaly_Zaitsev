package PageObject.Pages;

import PageObject.Base_Page;
import org.openqa.selenium.By;

public class MobileCatalog_Page extends Base_Page {

    private final By localityPopup = findByCss("[class *= 'popover-style_visible']");
    private final By localityYesButton = findByCss("[class *= 'button-style']");
    private final By superPriceLabel = findByCss("[class = schema-product__hot]");
    private final By superPricePopover = findByCss("[class *= 'popover-style_visible']");
    private final By addProductToCardBtn = findByPartialLinkText("В корзину");
    private final By goToCartButton = findByPartialLinkText("Перейти в корзину");
    private final By productLinkInCart = findByXpath("//div[contains(@class, 'cart-form__offers-part_data')]//a");

    public MobileCatalog_Page checkLocalityPopupText(String expectedText) {
        logger.debug("check locality popup text");
        assertText(localityPopup, expectedText);
        return this;
    }

    public MobileCatalog_Page clickLocalityYesButton() {
        logger.debug("click Yes on locality popup");
        clickButton(localityYesButton);
        return this;
    }

    public MobileCatalog_Page clickOnLabel() {
        logger.debug("click on price label");
        waitVisibilityOfElement(superPriceLabel);
        click(superPriceLabel);
        return this;
    }

    public MobileCatalog_Page checkPopoverText(String expectedText) {
        logger.debug("checking popover text");
        assertText(superPricePopover, expectedText);
        return this;
    }

    public MobileCatalog_Page clickProductLink(String productName) {
        logger.debug("clicking on product link");
        By productLink = findByPartialLinkText(productName);
        waitVisibilityOfElement(productLink);
        clickLink(productLink);
        return this;
    }

    public MobileCatalog_Page clickAddToCartBtn() {
        logger.debug("clicking add product to cart");
        clickButton(addProductToCardBtn);
        return this;
    }

    public MobileCatalog_Page clickGoToCartBtn() {
        logger.debug("clicking on go to cart");
        waitVisibilityOfElement(goToCartButton);
        clickButton(goToCartButton);
        return this;
    }

    public MobileCatalog_Page checkProductInCart(String productName) {
        logger.debug("checking product in cart");
        assertTrue(getText(productLinkInCart).contains(productName));
        return this;
    }

}
