package PageObject;

import BaseObjects.WebDriver.DriverManager;
import Utils.Properties.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Base_Page {
    protected WebDriver driver;
    protected Properties properties;
    protected Logger logger;
    protected WebDriverWait webDriverWait;

    protected Base_Page() {
        this.driver = DriverManager.getDriver();
        this.properties = PropertyReader.getProperties();
        this.logger = Logger.getLogger(Base_Page.class);
    }

    protected By findByCss(String css) {
        return By.cssSelector(css);
    }

    protected By findByXpath(String xpath) {
        return By.xpath(xpath);
    }

    protected By findByClass(String className) {
        return By.className(className);
    }

    protected By findByPartialLinkText(String text) {
        return By.partialLinkText(text);
    }

    public Base_Page open(String pageName) {
        logger.debug("Open " + pageName);
        driver.get(properties.getProperty(pageName + "PageUrl"));
        return this;
    }

    protected WebElement findElement(By el) {
        return driver.findElement(el);
    }

    protected void clickButton(By el) {
        findElement(el).click();
    }

    protected void click(By el) {
        findElement(el).click();
    }

    protected void clickLink(By el) {
        findElement(el).click();
    }

    protected void enterText(By el, String text) {
        findElement(el).sendKeys(text);
    }

    protected void assertText(By el, String expectedText) {
        Assert.assertEquals(findElement(el).getText(), expectedText);
    }

    protected void assertTrue(Boolean condition) {
        Assert.assertTrue(condition);
    }

    protected void clearTextArea(By el) {
        findElement(el).clear();
    }

    protected WebDriverWait getWaiter() {
        this.webDriverWait = (WebDriverWait) new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
        return webDriverWait;
    }

    protected void waitVisibilityOfElement(By el) {
        getWaiter().until(ExpectedConditions.visibilityOfElementLocated(el));
    }

    protected String getText(By el) {
        return findElement(el).getText();
    }

}