package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObject extends MainPageObject{
    public static final String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    SECOND_TITLE_OF_MY_LIST,
    REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Getting  '{FOLDER_NAME}'")
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    @Step("delete article from saved lists '{REMOVE_FROM_SAVED_BUTTON}'")
    private static String getRemoveButtonByTitle (String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    @Step("Getting saved article")
    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }


    @Step("Open my list")
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(  //клик на мой лист
                (folder_name_xpath),
                "Cannot find folder by name" + name_of_folder,
                5
        );
    }

    @Step("waitForArticleToAppearByTitle")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent((article_xpath), "Cannot find saved article by title" + article_title, 15);
    }

    @Step("waitForArticleToDisappearByTitle")
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent((article_xpath), "Saved article still present with title" + article_title, 15);
    }
    @Step("Swipe article to delete on Android")
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if(Platform.getInstance().isAndroid())
        {
            this.swipeElementToLeft(
                    (article_xpath),
                    "Cannot swipe to left to delete"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Не может кликнуть на кнопку, чтобы удалить из сохраненного", 5);
        }
        if(Platform.getInstance().isMW())
        {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Waiting for element is exists")
    public void waitForSecondTitlePresent()
    {
        this.waitForElementPresent( //проверить, что вторая статья осталась
                (SECOND_TITLE_OF_MY_LIST),
                "Cannot swipe to left to delete",
                5
        );
    }
}
