package base;

import org.apache.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.ConfigReader;

import java.util.concurrent.TimeUnit;

public class PriorTest {
    public static Logger logger=LoggerService.getInstance();
    protected ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    protected ThreadLocal<WebDriverWait> threadLocalWebDriverWait = new ThreadLocal<>();


    @BeforeSuite
    void suiteSetUp() {
    }

    @BeforeClass
    void classSetUp() {

    }
    @Parameters({"browser"})
    @BeforeMethod
    protected void methodSetUp(String browser) {

        logger.info("method set up");

        threadLocalWebDriver.set(BrowserFactory.getDriver(browser, logger));
        threadLocalWebDriver.get().manage().window().maximize();
        threadLocalWebDriver.get().manage().timeouts().implicitlyWait(Long.parseLong(ConfigReader.getProperty("implicitWait")), TimeUnit.SECONDS);

        threadLocalWebDriverWait.set(new WebDriverWait(threadLocalWebDriver.get(), Long.parseLong(ConfigReader.getProperty("defaultWait"))));

    }

    @AfterMethod
    protected void methodTearDown() {
        logger.info("method tear down");
        if (threadLocalWebDriver.get() != null) {
            threadLocalWebDriver.get().quit();
        }
    }
}
