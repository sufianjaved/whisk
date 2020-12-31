package utils.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DefaultConfiguration;

public class EnvironmentSetup {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public EnvironmentSetup() {
        PageFactory.initElements(driver, this);
    }

    public static void initializeLocalBrowser() {
        initializeLocalBrowser(DefaultConfiguration.getDefaultBrowserName());
    }

    @SuppressWarnings("deprecation")
    public static void initializeLocalBrowser(String defaultBrowserValue) {

        switch(defaultBrowserValue){
            case "Chrome":
                System.setProperty("webdriver.chrome.silentOutput", "true");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "Opera":
                System.setProperty("webdriver.opera.silentOutput", "true");
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 15);

    }
}
