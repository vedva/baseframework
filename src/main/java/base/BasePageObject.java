package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected BaseTest t;
    Logger logger;


    public BasePageObject(BaseTest baseTest) {
        this.t = baseTest;
        this.driver = baseTest.getDriver();
        this.wait = baseTest.getWait();
        this.logger = baseTest.getLogger();

    }
}
