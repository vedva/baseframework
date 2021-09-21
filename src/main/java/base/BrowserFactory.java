package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver getDriver(String browser, Logger logger) {
        WebDriver driver;
        logger.info("Starting " + browser + " driver");
        switch (browser) {
            case "firefox" -> {
                System.setProperty("webdriver.firefox.silentOutput", "true");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "chrome" -> {
//                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//                driver = new ChromeDriver();
                System.setProperty("webdriver.chrome.silentOutput", "true");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            default -> throw new IllegalStateException("Unexpected value: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}
