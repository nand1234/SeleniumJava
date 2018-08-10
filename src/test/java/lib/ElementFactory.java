package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.Exception;
import java.util.List;

public class ElementFactory
{
    private By element(String locatorType, String locatorValue)
    {
        By webElement = null;

        try {
            switch (locatorType) {
                case "id":
                    webElement = By.id(locatorValue);
                    break;
                case "name":
                    webElement = By.name(locatorValue);
                    break;
                case "className":
                    webElement = By.className(locatorValue);
                    break;
                case "xpath":
                    webElement = By.xpath(locatorValue);
                    break;
                case "cssSelector":
                    webElement = By.cssSelector(locatorValue);
                    break;
                default:
                    throw new Exception("Locator Type not found");
            }
        }
        catch (Exception ex)
        {
        }

        return  webElement;
    }


    /**
     * Find Element On Browser to perform Action
     * @param locatorType Locator Type to search
     * @param locatorValue Locator Value to be search by
     * @param driver WebDriver Instance
     * @return WebElement
     */
    public WebElement FindElement(String locatorType, String locatorValue, WebDriver driver)
    {
        Wait wait = new Wait();
        By locator = null;
        try {
            locator = element(locatorType, locatorValue);
        }
        catch (Exception ex)
        {
            throw ex;
        }

        return wait.waitForElement(locator,driver);
    }

    /**
     * Find Elements as List On Browser to perform Action
     * @param locatorType Locator Type to search
     * @param locatorValue Locator Value to be search by
     * @param driver WebDriver Instance
     * @return WebElement
     * @throws InterruptedException
     */
    public List<WebElement> FindElements(String locatorType, String locatorValue, WebDriver driver) throws InterruptedException
    {
        Wait wait = new Wait();
        By locator = null;

        try {
            locator = element(locatorType, locatorValue);
        }
        catch (Exception ex)
        {
            throw ex;
        }

        return wait.waitForElements(locator,driver);
    }
}
