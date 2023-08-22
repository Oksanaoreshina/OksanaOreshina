package lib.ui;

import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE1 = "xpath://*[contains(@bounds,'[48,312][864,377]')]",
            TITLE2 = "xpath://*[contains(@text,'Linkin Park discography')]",

            FOOTER_ELEMENT = "xpath://*[@text = 'View article in browser']",
            OPTION_BUTTON = "xpath://*[@class = 'android.view.View'][@content-desc = 'WORA']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text = 'Add to another reading list']",
            OPTION_CREAT_NEW_BUTTON = "xpath://*[@text = 'Create new']",
            INPUT_FIELD_NAME_OF_LIST = "xpath://*[@text = 'Name of this list']",
            MY_LIST_OK_BUTTON = "xpath://*[@class = 'android.widget.Button'][@text = 'OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']",
            PAGE_SAVE_BUTTON = "id:org.wikipedia:id/page_save",
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "";





    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Waiting for title '{TITLE1}'")
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE1, "Cannot find article title on page", 15);
    }

    @Step("Getting article Title '{text}' ")
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {return title_element.getText(); }

    }

    @Step("Swipe to footer '{FOOTER_ELEMENT}' ")
    public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
        this.swipeUpToFindElement(
               FOOTER_ELEMENT,
                "Cannot find the and of article",
                20
        );
    } else {
            this.scrollWebPageTitleElementNotVisible(
                    FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    @Step("Saving article to my lists '{OPTION_ADD_TO_MY_LIST_BUTTON}' ")
    public void addArticlesToMySaved()
    {
        if(Platform.getInstance().isMW())
        {this.removeArticleFromSavedIfItAdded(); }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot find option to add to List", 5);
    }

    @Step("Delete article from my list, if it added before")
    public void removeArticleFromSavedIfItAdded()
    {
        if(this.isElementPresent(OPTION_ADD_TO_MY_LIST_BUTTON))
        {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove an article from saved", 1);
            this.waitForElementPresent(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved lest after removing it from this list before");
        }
    }



    @Step("Add article to My new list on Android")
    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                OPTION_BUTTON, //кликаю 2 раза на кнопку, потому что там разные варианты кнопок отображаются и я хочу второй вариант
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find 'Add to another reading list'",
                15
        );

        this.waitForElementAndClick(
                OPTION_CREAT_NEW_BUTTON,
                "Cannot find 'Create new' reading list",
                10
        );

        this.waitForElementAndSendKeys(
                INPUT_FIELD_NAME_OF_LIST,
                name_of_folder,
                "Cannot find 'Name of this list'",
                10
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find option to add article to reading list - 'OK'",
                5
        );

    }

    @Step("Add article to My saved list on Android")
    public void addArticleToMyExistingList(String name_of_folder){
        this.waitForElementAndClick(
                PAGE_SAVE_BUTTON,      // вторая статья. нажать на Save
                "Cannot find option to add article to reading list",
                10
        );

        this.waitForElementAndClick(
                PAGE_SAVE_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,      //добавить в лист
                "Cannot find 'Add to another reading list'",
                15
        );

        this.waitForElementAndClick(              //добавить в существующий лист
                name_of_folder,
                "Cannot find option to add article to 'My  list'",
                5
        );

    }

    @Step("Close article")
    public void closeArticle()
    {
        if(Platform.getInstance().isAndroid())
        {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON, //назад
                    "Cannot find option to back",
                    5
            );
        } else {
            System.out.println("Method closeArticle() do nothing for Platform" + Platform.getInstance().getPlatformVar());
        }

    }

    public void assertTitlePresent()
    {
        this.assertElementPresent(
               driver,
               By.xpath(TITLE2),
                "Элемент с текстом не найден.");
    }

    public boolean isElementLocatedOnTheScreen(String locator)
    {
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find for  element by locator", 1).getLocation().getY();
        if(Platform.getInstance().isMW())
        {
            javascriptExecutor JSExecutor = (JavascriptExecutor)driver;
            Object is_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y <screen_size_by_y;
    }

    public void scrollWebPageTitleElementNotVisible(String locator, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

}
