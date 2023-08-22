package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObject extends MainPageObject {
    private static final String
    SEARCH_INIT_ELEMENT,
    SEARCH_INIT_SKIP,
    SEARCH_RESULT_BY_SUBSTRING_TPL,
    SEARCH_CANCEL_BUTTON,
    SEARCH_RESULT_ELEMENT,
    EMPTY_RESULT_LABEL;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
        
    }

    @Step("Getting '{SEARCH_RESULT_BY_SUBSTRING_TPL}' ")
    public static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    @Step("Initializing '{SEARCH_INIT_ELEMENT}'")
    public void initSearchInput() {
        this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Cannot find search input before clicking search init element");
        this.waitForElementAndClick((SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }
    @Step("Put on '{SEARCH_INIT_SKIP}' on Android")
    public void initSearchInputSkip() {
        this.waitForElementPresent((SEARCH_INIT_SKIP), "Cannot find search input before clicking Skip");
        this.waitForElementAndClick((SEARCH_INIT_SKIP), "Cannot find and click Skip", 5);
    }

    @Step("Waiting for '{SEARCH_CANCEL_BUTTON}' to cancel search results")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent((SEARCH_CANCEL_BUTTON),"Cannot find search cancel button", 5);
    }

    @Step("Waiting for search cancel  to disappear")
    public void waitForCancelButtonToDisAppear()
    {
        this.waitForElementNotPresent((SEARCH_CANCEL_BUTTON),"Search cancel button is still present", 5);
    }

    @Step("Clicking '{SEARCH_CANCEL_BUTTON}' to cancel  search result")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick((SEARCH_CANCEL_BUTTON),"Cannot find click search cancel button", 5);
    }

    @Step("Typing text to the '{SEARCH_INIT_ELEMENT}'")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys((SEARCH_INIT_ELEMENT), search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for the search  result")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath), "Cannot find search result with substring" + substring);

    }

    @Step("Waiting for the search  result and select an article by substring in article title")
    public void ClickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick((search_result_xpath), "Cannot find and click search result with substring" + substring, 10);

    }

    @Step("Getting amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
               (SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements((SEARCH_RESULT_ELEMENT));

    }

    @Step("Waiting for empty result of searching")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent((EMPTY_RESULT_LABEL), "Cannot find empty result label", 15);
    }

    @Step("Making sure there are not results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent((SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }
}
