package woodruff360UI.tests;

import com.galenframework.api.Galen;
import com.galenframework.reports.model.LayoutReport;
import lib.BrowserFactory;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.util.Arrays;


public class UITest
{
    WebDriver driver;

    @BeforeTest
    public void test1() throws IOException, ParseException
    {
        BrowserFactory browser = new BrowserFactory();
        driver  = browser.GetBrowser();
        driver.get("https://www.swtestacademy.com/");
    }

    @Test
    public void test2() throws IOException
    {
        LayoutReport layoutReport = Galen.checkLayout(driver, "woodruff360UI/uispec/HomePage.gspec", Arrays.asList("desktop"));

    }
}
