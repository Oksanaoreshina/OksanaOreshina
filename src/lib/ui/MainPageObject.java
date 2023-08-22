package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;
    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    @Step("Waiting for the element")
    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    @Step("Clicking button to open lists")
    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts)
    {
        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts)
        {
            try { this.waitForElementAndClick(locator, error_message, 1);
            need_more_attempts = false; }
        catch(Exception e) {
            if (current_attempts > amount_of_attempts) {
                this.waitForElementAndClick(locator, error_message, 1);
            }
        }
            ++current_attempts;
    }
    }

    @Step("Checking element is exists")
    public boolean isElementPresent(String locator)
    {
        return getAmountOfElements(locator) > 0;
    }

    @Step("Waiting for element and click")
    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    @Step("Waiting for element and clicking")
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    @Step("Checking element is not exists")
    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


    @Step("Swipe up on Android")
    public void swipeUp(int timeOfSwipe)
    {
        if (driver instanceof AppiumDriver)
        {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int)(size.height * 0.8);
            int and_y = (int)(size.height * 0.2);
            action
                    .press(x, start_y)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, and_y)
                    .release()
                    .perform();
        } else {
            System.out.println("Method SwipeUp() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }

    }
    @Step("Swipe up")
    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    @Step("Scroll page")
    public void scrollWebPageUp()
    {
        if(Platform.getInstance().isMW())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0; 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }


    public void swipeUpToFindElement(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(By.id(locator)).size() == 0)
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
        {
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(String locator, String error_message) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
                    action.press(right_x, middle_y);
                    action.waitAction(150);

            if (Platform.getInstance().isAndroid()) {
                action.moveTo(left_x, middle_y);
            } else {
                int offset_x = (-1 * element.getSize().getWidth());
                action.moveTo(offset_x, 0);
            }
            action.release();
            action.perform();
        } else {
            System.out.println("Metod SwipeUp() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator)
    {
        List elements  = driver.findElements(By.id(locator));
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message)
    {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An Element '" + locator + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public static void assertElementPresent(AppiumDriver<MobileElement> driver, By by, String errorMessage) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            Assert.fail(errorMessage);
        }
    }

    private By getLocatorByString(String locator_with_type)  // подмена локаторов для ios и android
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath"))
        {
            return By.xpath(locator);
        } else if (by_type.equals("id"))
        {
            return By.id(locator);
        } else if (by_type.equals("css"))
        {
            return By.cssSelector(locator);
        }
        else
        {
            throw new IllegalArgumentException("Cannot get type of locator. Locator:" + locator_with_type);
        }
    }

    public String takeScreenshot(String name)
    {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getenv("user.dir") + "/" + name +"_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken:" + path);
        } catch (Exception e)
        {
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        } return path;
    }

    @Attachment
    public static byte [] screenshot (String path)
    {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e)
        {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        } return bytes;
    }
}
