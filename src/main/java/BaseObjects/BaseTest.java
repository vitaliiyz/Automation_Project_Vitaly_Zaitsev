package BaseObjects;

import BaseObjects.WebDriver.DriverManager;
import BaseObjects.WebDriver.DriverManagerFactory;
import Utils.Listener;
import Utils.Properties.PropertyReader;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

@Listeners({Listener.class})
public abstract class BaseTest {
    protected DriverManager driverManager;
    protected WebDriver driver;
    protected ITestContext context;

    @BeforeTest
    public void setUp(ITestContext context) {
        this.context = context;
        this.driverManager = DriverManagerFactory.getManager(DriverManagerType.valueOf(PropertyReader.getProperties().getProperty("browser").toUpperCase(Locale.ROOT)));
        this.driver = DriverManager.getDriver();
    }

    protected <T> T get(Class<T> page) {
        T instance = null;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @AfterTest
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
