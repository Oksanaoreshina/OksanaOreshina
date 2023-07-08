
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:/Users/79651/Desktop/javaAppiumAvtomation/javaAppiumAvtomation/apks/org.wikipedia_50444_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities );
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test
    public void firstTest()
    {
        waitForElementByXPathAndClick(
                "//*[contains(@text,'Skip')]",
                "Cannot find Skip",
                5
        );

        waitForElementByXPathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find Search Wikipedia",
                5
        );

        waitForElementByXPathAndSendKeys(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cat",
                "Cannot find Cat",
                5
        );

        assertElementHasText(
                "//*[@index = '2']//*[@text = 'British musician (born 1948)']",
                "Cannot find 'British musician (born 1948)'",
                15
        );
    }
    private WebElement assertElementHasText(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement assertElementHasText(String xpath, String error_message)
    {
        return assertElementHasText(xpath, error_message, 5);
    }

    private WebElement waitForElementByXPathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element = assertElementHasText(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementByXPathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = assertElementHasText(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}
