package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;
import utilities.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest extends PriorTest {

    /**
     * Class contains common functionality
     * @tdiachuk
     */

    public WebElement find(String xpath) {
        try {
            return getDriver().findElement(By.xpath(xpath));
        } catch (Exception e) {
            logger.warn("xpath is not found");
            return null;
        }
    }

    public boolean click(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            logger.warn("can't click on element");
            return false;
        }
    }

    public boolean click(String xpath) {
        WebElement element = find(xpath);
        if (element == null) {
            return false;
        }
        return this.click(element);
    }

    public boolean setText(WebElement element, String inputText) {
        try {
            if (inputText == null) {
                System.out.println("Can't set text - text is null");
                return false;
            }
            element.sendKeys(inputText);
            return true;
        } catch (Exception e) {
            logger.warn("Can't set text: [" + inputText + "] to webelement");
            return false;
        }
    }

    public boolean setText(String xpath, String inputText) {
        WebElement element = find(xpath);
        if (element == null) {
            return false;
        }
        return
                this.setText(element, inputText);
    }

    //my new common methods

    /**
     * Find WebElements by xpath
     *
     * @param xpath
     * @return Empty list if no element present
     */
    public List<WebElement> findElements(String xpath) {
        try {
            return getDriver().findElements(By.xpath(xpath));
        } catch (Exception e) {
            logger.warn("Can't find elements by xpath: [" + xpath + "] - exception");
            return new ArrayList<WebElement>();
        }
    }

    /**
     * Getting to URL with webdriver
     *
     * @param url
     * @return success
     */
    public boolean getToUrl(String url) {
        if (getDriver() == null || url == null) return false;
        try {
            getDriver().get(url);
            return true;
        } catch (Exception e) {
            logger.warn("Can't get to URL: [" + url + "] - exception \n" + StringUtil.getFullExceptionMessage(e));
            return false;
        }
    }

    /**
     * Verifying WebElement present in the DOM by xpath
     *
     * @param xpath
     */
    public boolean verifyElement(String xpath) {
        return this.findElements(xpath).size() != 0;
    }


    /**
     * Verifying element is selected
     *
     * @param element
     * @return
     */
    public Boolean isSelected(WebElement element) {
        if (element == null) {
            return null;
        }
        try {
            return element.isSelected();
        } catch (Exception e) {
            logger.warn("Can't select element: [" + element + "] - exception /n" + StringUtil.getShortExceptionMessage(e));
            return null;
        }

    }

    /**
     * Verifying element is selected
     *
     * @param xpath
     */
    public Boolean isSelected(String xpath) {
        if (xpath == null) {
            return null;
        }
        return this.isSelected(this.find(xpath));
    }

    /**
     * Finding element in element
     */
    public WebElement findInElement(WebElement element, String findElementXpath) {
        try {
            return element.findElement(By.xpath("." + findElementXpath));
        } catch (Exception e) {
            logger.warn("Can't find element in element - exception" + StringUtil.getFullExceptionMessage(e));
            return null;
        }
    }

    /**
     * Finding element in element
     */
    public WebElement findInElement(String xpathToSearchIn, String findElementXpath) {
        WebElement element = find(xpathToSearchIn);
        if (element == null) {
            return null;
        }
        return findInElement(element, findElementXpath);
    }

    /**
     * Finding elements in element
     */
    public List<WebElement> findElementsInElement(WebElement element, String xpath) {
        try {
            return element.findElements(By.xpath("." + xpath));
        } catch (Exception e) {
            logger.warn("Can't find elements in element - exception" + StringUtil.getFullExceptionMessage(e));
            return new ArrayList<WebElement>();
        }
    }

    /**
     * Finding elements in element
     */
    public List<WebElement> findElementsInElement(String xpathToSearchIn, String xpath) {
        WebElement element = find(xpathToSearchIn);
        if (element == null) {
            return new ArrayList<WebElement>();
        }
        return findElementsInElement(element, xpath);
    }

    /**
     * Getting current webdriver
     */
    public WebDriver getDriver() {
        return threadLocalWebDriver.get();
    }

    /**
     * Getting current WebDriverWait
     */
    public WebDriverWait getWait() {
        return threadLocalWebDriverWait.get();
    }

    /**
     * Setting implicit timeout in seconds
     *
     * @param implicitTimeoutInSeconds
     * @return success
     */
    public boolean setImplicitTimeout(Long implicitTimeoutInSeconds) {
        try {
            getDriver().manage().timeouts().implicitlyWait(implicitTimeoutInSeconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            logger.warn("Can't set implicit timeout - exception" + StringUtil.getFullExceptionMessage(e));
            return false;
        }
    }

    /**
     * Setting implicit timeout to default
     */
    public boolean setImplicitTimeoutToDefault() {
        return setImplicitTimeout(Long.parseLong(ConfigReader.getProperty("implicitWait")));
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * Select element by value
     */
    public boolean selectByValue(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            return true;
        } catch (Exception e) {
            logger.warn("Can't select by value - exception" + StringUtil.getFullExceptionMessage(e));
            return false;
        }
    }

    /**
     * Selecting element by xpath
     */
    public boolean selectByValue(String xpath, String value) {
        WebElement element = find(xpath);
        if (element == null) {
            return false;
        }
        return selectByValue(element, value);
    }

    /**
     * Selecting by visible text
     */
    public boolean selectByVisibleText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            return true;
        } catch (Exception e) {
            logger.warn("Can't select by visible text - exception" + StringUtil.getFullExceptionMessage(e));
            return false;
        }
    }

    /**
     * Selecting by visible text
     */
    public boolean selectByVisibleText(String xpath, String text) {
        WebElement element = find(xpath);
        if (element == null) {
            return false;
        }
        return selectByVisibleText(element, text);
    }

    public String getPageURL(String pageName) {
        String baseDomain = ConfigReader.getProperty("baseDomain");
        String url;
        switch (pageName) {
            case "hp":
                url = "https://www." + baseDomain + "/";
                break;
            default:
                logger.warn("Can't get page URL - wrong page name parameter: [" + pageName + "]");
                return null;
        }
        return url;
    }
}

