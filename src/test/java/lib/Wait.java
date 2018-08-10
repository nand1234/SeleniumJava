package lib;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class Wait
{
    private static final int GLOBAL_TIME_OUT = 3; // In Minute
    private static final int GLOBAL_POLLING_TIME = 5; // In Seconds
    private static final int GLOBAL_EXP_CONDITION_TIME_OUT = 60; // In Seconds

    /**
     * Wait for Element before performing Action
     * @param locator Element locator value
     * @param driver WebDriver Instance
     * @return Found element
     */
    public WebElement waitForElement(By locator , WebDriver driver) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMinutes(GLOBAL_TIME_OUT))
                .pollingEvery(Duration.ofSeconds(GLOBAL_POLLING_TIME))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                }
        );
        return element;
    }

    /**
     * Wait for Element before performing Action
     * @param locator Element locator value
     * @param driver WebDriver Instance
     * @return Found List of Elements
     */
    public List<WebElement> waitForElements(By locator, WebDriver driver) throws InterruptedException
    {
        int i = 0;
        List<WebElement> elements = null;

        do
            {
            try {
                elements = driver.findElements(locator);
            } catch (NoSuchElementException ex) {
                Thread.sleep(GLOBAL_POLLING_TIME * 1000);
                i = i + GLOBAL_POLLING_TIME * 1000;
            }
        }
        while (i==GLOBAL_TIME_OUT * 60000);

        return  elements;
    }


    /**
     * Wait Until Element is clickable
     * @param elementLocator Element to be wait for
     * @param driver WebDriver Instance
     * @return WebElement
     */
    public WebElement WaitElementToBeClickable(By elementLocator, WebDriver driver)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, GLOBAL_EXP_CONDITION_TIME_OUT);
            return wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
