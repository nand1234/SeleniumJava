package woodruff360UI.tests;

import lib.BrowserFactory;
import lib.ElementFactory;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import java.io.IOException;
import java.util.List;

public class FunctionalTest
{
    private WebDriver driver;
    private ElementFactory element;

    @BeforeSuite
    public void setup() throws IOException, ParseException
    {
        BrowserFactory browser = new BrowserFactory();
        element = new ElementFactory();
        driver  = browser.GetBrowser();
    }

    @Test
    public void test1() throws InterruptedException
    {
        driver.get("https://automation01.vievusolutiontest.com/");
        List<WebElement> ele = element.FindElements("id","Login",driver);
        System.out.println(ele);
    }
    @Test
    public void test2() throws IOException, ParseException
    {
        BrowserFactory browser = new BrowserFactory();
        WebDriver driver  = browser.GetBrowser();
        driver.get("https://automation01.vievusolutiontest.com/");
    }
    @Test
    public void test3() throws IOException, ParseException
    {
        BrowserFactory browser = new BrowserFactory();
        WebDriver driver  = browser.GetBrowser();
        driver.get("https://automation01.vievusolutiontest.com/");
    }

    @AfterSuite
    public void QuitDriver()
    {
        driver.quit();
    }
}
