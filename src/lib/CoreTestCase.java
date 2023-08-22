package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.RemoteWebDriver;


public class CoreTestCase  {
    protected RemoteWebDriver driver;
    protected Platform Platform;
    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
    }
    @After
    @Step("Remove driver and session")
    public void tearDown()  {
        driver.quit();
        this.openWikiWebPageForMobileWeb();
    }

    @Step("Open wikipedia URL for Mobile Web (this method does nothing for IOS and Android)")
    protected void openWikiWebPageForMobileWeb()
    {
        if(Platform.getInstance().isMW())
        {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

}
