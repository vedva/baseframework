package base;

import com.beust.jcommander.Parameters;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class PriorTest {
    public static Logger logger = Logger.getLogger(PriorTest.class.getClass());
    protected ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    protected ThreadLocal<WebDriverWait> threadLocalWebDriverWait = new ThreadLocal<>();

    static {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.setLayout(new PatternLayout("%d [%p|%c|%C{1}] %m%n"));
        consoleAppender.activateOptions();
        Logger.getRootLogger().addAppender(consoleAppender);
    }

    @BeforeSuite
    void suiteSetUp() {
    }

    @BeforeClass
    void classSetUp() {

    }

    @Parameters({"browser"})
    @BeforeMethod
    protected void methodSetUp(String browser) {


        //logger.info("logger is working");

        logger.info("method set up");
//        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
//        driver = new FirefoxDriver();
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        driver = new ChromeDriver();

        threadLocalWebDriver.set(MyBrowserFactory.getDriver(browser, logger));
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
