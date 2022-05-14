package PageObject;

import BaseObjects.WebDriver.DriverManager;
import Utils.Properties.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Properties;

public class BasePage {
    public WebDriver driver;
    public Properties properties;
    public Logger logger;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.properties = PropertyReader.getProperties();
        this.logger = Logger.getLogger(BasePage.class);
    }

    public BasePage open(String pageName) {
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

    protected void enterText(By el, String text) {
        findElement(el).sendKeys(text);
    }

    protected void assertText(By el, String expectedText) {
        Assert.assertEquals(findElement(el).getText(), expectedText);
    }

    protected void assertTrue(Boolean condition) {
        Assert.assertTrue(condition);
    }

    public void clearTextArea(By el) {
        findElement(el).clear();
    }

}
