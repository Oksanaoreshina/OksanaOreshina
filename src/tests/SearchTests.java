package tests;

import com.sun.org.glassfish.gmbal.Description;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

@Epic("Tests for searching")
public class SearchTests extends CoreTestCase {

    @Test
    @DisplayName("Search 'Object-oriented programming language'")
    @Description(" We search 'Object-oriented programming language' in results")
    @Step("Start test testSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @DisplayName("Search 'Java' and after cancel it")
    @Description(" We search 'Java' in results and  after put on  button for cancel")
    @Step("Start test testCancelSearch")
    @Severity(value = SeverityLevel.BLOKER)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisAppear();
    }

    @Test
    @DisplayName("Counting the number of articles")
    @Description(" We search 'Linkin Park discography' in results and find out few articles in results")
    @Step("Start test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line );
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0
        );
    }

    @Test
    @DisplayName("Search 'gggiuy' and find out result is empty ")
    @Description(" We search 'gggiuy' and find out result is empty")
    @Step("Start test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        String search_line = "gggiuy";
        SearchPageObject.typeSearchLine(search_line );
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @DisplayName("Open article and find out that  Title is exist")
    @Description(" We open article 'Linkin Park discography' and find out that  Title is exist")
    @Step("Start test TestAssertTitlePresent")
    @Severity(value = SeverityLevel.MINOR)
    public void TestAssertTitlePresent(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Linkin Park discography");
        SearchPageObject.ClickByArticleWithSubstring("Linkin Park discography");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.assertTitlePresent();
    }
}
