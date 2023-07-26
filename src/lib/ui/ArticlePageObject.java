package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE1 = "//*[contains(@bounds,'[48,312][864,377]')]",
            TITLE2 = "//*[contains(@text,'Linkin Park discography')]",

            FOOTER_ELEMENT = "//*[@text = 'View article in browser']",
            OPTION_BUTTON = "//*[@class = 'android.view.View'][@content-desc = 'WORA']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "//*[@text = 'Add to another reading list']",
            OPTION_CREAT_NEW_BUTOON = "//*[@text = 'Create new']",
            INPUT_FIELD_NAME_OF_LIST = "//*[@text = 'Name of this list']",
            MY_LIST_OK_BUTTON = "//*[@class = 'android.widget.Button'][@text = 'OK']",
            CLOSE_ARTICLE_BUTTON = "//*[@class = 'android.widget.ImageButton'][@content-desc = 'Navigate up']",
            PAGE_SAVE_BUTTON = "org.wikipedia:id/page_save";




    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(TITLE1), "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");

    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the and of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON), //кликаю 2 раза на кнопку, потому что там разные варианты кнопок отображаются и я хочу второй вариант
                "Cannot find option to add article to reading list",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to another reading list'",
                15
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_CREAT_NEW_BUTOON),
                "Cannot find 'Create new' reading list",
                10
        );

        this.waitForElementAndSendKeys(
                By.xpath(INPUT_FIELD_NAME_OF_LIST),
                name_of_folder,
                "Cannot find 'Name of this list'",
                10
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find option to add article to reading list - 'OK'",
                5
        );

    }

    public void addArticleToMyExistingList(String name_of_folder){
        this.waitForElementAndClick(
                By.id(PAGE_SAVE_BUTTON),      // вторая статья. нажать на Save
                "Cannot find option to add article to reading list",
                10
        );

        this.waitForElementAndClick(
                By.id(PAGE_SAVE_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),      //добавить в лист
                "Cannot find 'Add to another reading list'",
                15
        );

        this.waitForElementAndClick(              //добавить в существующий лист
                By.xpath(name_of_folder),
                "Cannot find option to add article to 'My  list'",
                5
        );

    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON), //назад
                "Cannot find option to back",
                5
        );
    }

    public void assertTitlePresent()
    {
        this.assertElementPresent(
                driver,
                By.xpath(TITLE2),
                "Элемент с текстом не найден.");
    }

}
