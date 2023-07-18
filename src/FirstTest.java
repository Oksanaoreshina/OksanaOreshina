
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/79651/Desktop/javaAppiumAvtomation/javaAppiumAvtomation/apks/org.wikipedia_50444_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

  //  @Test
//    public void firstTest()
//       {
//         waitForElementAndClick(
//               By.xpath("//*[contains(@text,'Skip')]"),
//               "Cannot find Skip",
//                5
//        );
//
//          waitForElementAndClick(
//                  By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//              "Cannot find Search Wikipedia",
//              5
//       );
//
//       waitForElementAndSendKeys(
//               By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//              "Java",
//              "Cannot find search input",
//              10
//       );
//
//       waitForElementPresent(
//               By.xpath("//*[@class = 'android.view.ViewGroup']//*[@text = 'Object-oriented programming language']"),
//               "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
//               15
//        );
//       }

//    @Test
//    public void testCancelSearch() {
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
//                "Can not find Skip",
//                5
//        );
//
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Can not find 'search Wikipedia' input",
//                5
//        );
//
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Java",
//                "Cannot find search input",
//                5
//        );
//
//        waitForElementAndClick(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "Can not find X to cancel search",
//                5
//        );
//
//        waitForElementNotPresent(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "X is still present on the page",
//                5
//        );
//
//    }

    @Test
    public void testCompareArticleTitle()
    {

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Skip')]"),
                    "Cannot find Skip",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Java",
                    "Cannot find search input",
                    10
            );

           WebElement title_element = waitForElementPresent(
                   By.xpath("//*[contains(@bounds,'[48,312][864,377]')]"),
                   "Cannot find article title 1",
                   15
           );

           String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title 1!",
                "Java",
                article_title
        );

        WebElement title_element1 = waitForElementPresent(
                By.xpath("//*[contains(@bounds,'[48,536][864,601]')]"),
                "Cannot find article title2",
                15
        );

        String article_title1 = title_element1.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title2!",
                "Java",
                article_title
        );

        WebElement title_element2 = waitForElementPresent(
                By.xpath("//*[contains(@bounds,'[48,760][864,825]')]"),
                "Cannot find article title3",
                15
        );

        String article_title2 = title_element2.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title 3!",
                "Java",
                article_title
        );

    }

    /*@Test
    public void testSwipeArticle1()
    {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Skip')]"),
                "Cannot find Skip",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                20
        );

        waitForElementAndClick(
               By.xpath("//*[@class = 'android.widget.TextView'][@text = 'Appium']"),
               "Cannot find 'Appium' topic searching by 'Appium'",
              20
        );

        swipeUpToFindElement(
                By.xpath("//*[@text = 'View article in browser']"),
                "Cannot find the and of the article",
                20

        );
    }*/

    @Test
    public void testSwipeArticle(){


            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Skip')]"),
                    "Cannot find Skip",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia",
                    5
            );

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/search_src_text"),
                    "Java",
                    "Cannot find search input",
                    10
            );

            waitForElementAndClick(
                    By.xpath("//*[@class = 'android.view.ViewGroup']//*[@text = 'Object-oriented programming language']"),
                   "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                   15
            );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.view.View'][@content-desc = 'WORA']"),
                "Cannot find option to add article to reading list",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.view.View'][@content-desc = 'WORA']"), //кликаю 2 раза на кнопку, потому что там разные варианты кнопок отображаются и я хочу второй вариант
                "Cannot find option to add article to reading list",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to another reading list']"),
                "Cannot find 'Add to another reading list'",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'Create new']"),
                "Cannot find 'Create new' reading list",
                10
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text = 'Name of this list']"),
                "My reading list",
                "Cannot find 'Name of this list'",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.Button'][@text = 'OK']"),
                "Cannot find option to add article to reading list - 'OK'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']"), //назад
                "Cannot find option to back",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_description'][@text = 'High-level programming language']"), //поиск второй статьи
                "Cannot find 'High-level programming language' topic searching by 'Java'",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),      // вторая статья. нажать на Save
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to another reading list']"),      //добавить в лист
                "Cannot find 'Add to another reading list'",
                15
        );

        waitForElementAndClick(              //добавить в лист me reading list
                By.xpath("//*[@class = 'android.widget.TextView'][@text = 'My reading list']"),
                "Cannot find option to add article to 'My reading list'",
                5
        );


        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']"), //назад в список статей
                "Cannot find option to back",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']"), //назад в ленту, где есть кнопка со статьями
                "Cannot find option to back",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@class = 'android.widget.ImageView'][@bounds = '[288,1596][360,1668]']"), //клик на переход к спискам листов
                "Cannot find saved lists",
                5
        );
        //свайп вниз до моего листа
        swipeUpToFindElement(
                By.xpath("//*[@text = 'My reading list']"),
                "Cannot find my reading list",
                20

        );

        waitForElementAndClick(  //клик на мой лист
                By.xpath("//*[@text = 'My reading list']"),
                "Cannot find the button Saved",
                5
        );
        //удалить статью, смахнув влево

        swipeElementToLeft(
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot swipe to left to delete"
        );

        waitForElementNotPresent(  //проверить, что статья удалена
                By.xpath("//*[@text = 'Object-oriented programming language']"),
                "Cannot swipe to left to delete",
                5
        );

        waitForElementPresent( //проверить, что вторая статья осталась
                By.xpath("//*[@text = 'High-level programming language']"),
                "Cannot swipe to left to delete",
                5
        );

        }

        @Test
        public void testAmountOfNotEmptySearch()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Skip')]"),
                    "Cannot find Skip",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia",
                    5
            );

            String search_line = "Linkin Park discography";
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    search_line,
                    "Cannot find search input",
                    10
            );

            String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']";
            waitForElementPresent(
                    By.xpath(search_result_locator),
                    "Cannot find anything by the request" + search_line,
                    15
            );

            int amount_of_search_results = getAmountOfElements(
                    By.xpath(search_result_locator)
            );

            Assert.assertTrue(
                    "We found too few results!",
                    amount_of_search_results > 0
            );
        }

        @Test
        public void testAmountOfEmptySearch()
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Skip')]"),
                    "Cannot find Skip",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia",
                    5
            );

            String search_line = "gggiuy";
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    search_line,
                    "Cannot find search input",
                    10
            );

            String search_result_locator = "//*[@resource-id = 'org.wikipedia:id/search_results_list']";
            String empty_result_label = "//*[@text = 'No results']";
            waitForElementPresent(
                    By.xpath(empty_result_label),
                    "Cannot find empty result label by the request" + search_line,
                    15
            );

            assertElementNotPresent(
                    By.xpath(search_result_locator),
                    "We've hound some results by request " + search_line
            );
        }

        @Test
        public void assertTitlePresent(){
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Skip')]"),
                    "Cannot find Skip",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find Search Wikipedia",
                    5
            );

            String search_line = "Linkin Park discography";
            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    search_line,
                    "Cannot find search input",
                    10
            );

            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Linkin Park discography')]"),
                    "Cannot find 'Linkin Park discography' by search",
                    5
            );

            assertElementPresent(driver,
                    By.xpath("//*[contains(@text,'Linkin Park discography')]"),
                    "Элемент с текстом не найден.");

        }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    protected void swipeUp(int timeOfSwipe)
    {
      TouchAction action = new TouchAction(driver);
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
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        if (already_swiped > max_swipes)
        {
            waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
            return;
        }
        {
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
      WebElement element = waitForElementPresent(by, error_message, 10);
      int left_x = element.getLocation().getX();
      int right_x = left_x + element.getSize().getWidth();
      int upper_y = element.getLocation().getY();
      int lower_y = upper_y + element.getSize().getHeight();
      int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by)
    {
        List elements  = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An Element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private static void assertElementPresent(AppiumDriver<MobileElement> driver, By by, String errorMessage) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            Assert.fail(errorMessage);
        }
    }


}