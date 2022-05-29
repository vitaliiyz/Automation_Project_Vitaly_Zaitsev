package PageObject.Pages;

import PageObject.Base_Page;
import org.openqa.selenium.By;

public class MobileCatalog_Page extends Base_Page {

    private final By localityPopup = findByCss("[class *= 'popover-style_visible']");
    private final By localityYesBtn = findByCss("[class *= 'button-style']");
    private final By superPriceLabel = findByCss("[class = schema-product__hot]");
    private final By superPricePopover = findByCss("[class *= 'popover-style_visible']");
    private final By addProductToCardBtn = findByPartialLinkText("В корзину");
    private final By goToCartButton = findByPartialLinkText("Перейти в корзину");
    private final By productLinkInCart = findByXpath("//div[contains(@class, 'cart-form__offers-part_data')]//a");
    private final By removeProductFromCartBtn = findByCss(".cart-form__offers-part_remove .button-style");
    private final By textAfterRemovingProduct = findByCss("[class *= 'cart-form__description_condensed-extra']");
    private final By closeRemovedProductLink = findByPartialLinkText("Закрыть");
    private final By cartTitle = findByCss(".cart-message__title_big");

    public MobileCatalog_Page checkLocalityPopupText(String expectedText) {
        logger.debug("checking locality popup text");
        assertText(localityPopup, expectedText);
        return this;
    }

    public MobileCatalog_Page clickLocalityYesButton() {
        logger.debug("clicking Yes on locality popup");
        clickButton(localityYesBtn);
        return this;
    }

    public MobileCatalog_Page clickOnLabel() {
        logger.debug("clicking on price label");
        click(superPriceLabel);
        return this;
    }

    public MobileCatalog_Page checkPopoverText(String expectedText) {
        logger.debug("checking popover text");
        assertText(superPricePopover, expectedText);
        return this;
    }

    public MobileCatalog_Page clickProductLink(String productName) {
        logger.debug("going to a product page");
        By productLink = findByPartialLinkText(productName);
        clickLink(productLink);
        return this;
    }

    public MobileCatalog_Page clickAddToCartBtn() {
        logger.debug("adding a product to the cart");
        clickButton(addProductToCardBtn);
        return this;
    }

    public MobileCatalog_Page clickGoToCartBtn() {
        logger.debug("going to the cart");
        clickButton(goToCartButton);
        return this;
    }

    public MobileCatalog_Page checkProductInCart(String productName) {
        logger.debug("checking product in the cart");
        assertTrue(getText(productLinkInCart).contains(productName));
        return this;
    }

    public MobileCatalog_Page removeProductFromCart() {
        logger.debug("removing product from the cart");
        moveToElement(productLinkInCart);
        clickButton(removeProductFromCartBtn);
        return this;
    }

    public MobileCatalog_Page checkTextAfterRemovingProduct(String containedText) {
        logger.debug("checking text after removing the product");
        assertTrue(getText(textAfterRemovingProduct).contains(containedText));
        return this;
    }

    public MobileCatalog_Page clickCloseRemovedProductLink() {
        logger.debug("closing removed product");
        clickLink(closeRemovedProductLink);
        return this;
    }

    public MobileCatalog_Page checkCartTitleText(String containedText) {
        logger.debug("checking that cart is empty");
        assertTrue(getText(cartTitle).contains(containedText));
        return this;
    }

}
