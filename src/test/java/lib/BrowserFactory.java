package lib;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


public class BrowserFactory
{
    private WebDriver driver;
    private String browserName;
    private String gridUrl = "";
    private String executionType = "";

    /**
     * Launch Browser Based on Config Settings/Values
     * @return Browser Instance
     * @throws IOException
     * @throws ParseException
     */
    public WebDriver GetBrowser() throws IOException, ParseException {

        SetExecutionType();
        if (executionType.equals("local")) {
            switch (browserName) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    ChromeDriverService service = new ChromeDriverService.Builder()
                            .usingDriverExecutable(new File("./src/test/java/resources/chromedriver.exe"))
                            .usingAnyFreePort()
                            .build();
                    driver = new ChromeDriver(service, options);
                    break;
                case "firefox":
                    String firefoxPath = "./src/java/resources/geckodriver.exe";
                    System.setProperty("webdriver.gecko.driver", firefoxPath);
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "ie":
                    InternetExplorerDriverService ieService = new InternetExplorerDriverService.Builder()
                            .usingDriverExecutable(new File("./src/test/java/resources/IEDriverServer.exe"))
                            .usingAnyFreePort()
                            .build();
                    driver = new InternetExplorerDriver(ieService);
                    driver.manage().window().maximize();
                    break;
                case "grid":
                    driver = new RemoteWebDriver(new URL(gridUrl), getBrowserCapabilities(browserName));
                    break;
                default:
                    System.out.println("browser : is invalid, Launching Firefox as browser of choice..");
                    String defaultPath = "./src/test/java/resources/geckodriver.exe";
                    System.setProperty("webdriver.gecko.driver", defaultPath);
                    driver = new FirefoxDriver();
            }
        }

        return driver;
    }

    /**
     *
     * @param browserType
     * @return
     */
    private  DesiredCapabilities getBrowserCapabilities(String browserType)
    {
        switch (browserType) {
            case "firefox":
                System.out.println("Opening firefox driver");
                return DesiredCapabilities.firefox();
            case "chrome":
                System.out.println("Opening chrome driver");
                return DesiredCapabilities.chrome();
            case "IE":
                System.out.println("Opening IE driver");
                return DesiredCapabilities.internetExplorer();
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                return DesiredCapabilities.firefox();
        }
    }

    /**
     *
     * @throws IOException
     * @throws ParseException
     */
    private void SetExecutionType() throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("config.json"));
        JSONObject jObj = (JSONObject) obj;
        JSONObject execution = (JSONObject)jObj.get("execution");
        executionType = (String) execution.get("type");
        if (executionType.equals("local")) {
            JSONObject local = (JSONObject)jObj.get("local");
            browserName = (String)local.get("browser_name");
        }
        else {

            JSONObject grid = (JSONObject) jObj.get("selenium_grid");
            gridUrl = (String) grid.get("grid_url");
            browserName = (String) grid.get("browser_name");
        }
    }
}


