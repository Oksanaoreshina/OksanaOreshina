package tests;

import com.sun.org.glassfish.gmbal.Description;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value="search"), @Feature(value="Article")})
    @DisplayName("Compare Article title with expected one")
    @Description("We open 'Object-oriented programming language' and make sure the title is exist ")
    @Step("Start test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOKER)
    public void testCompareArticleTitle()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.ClickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        //ArticlePageObject.takeScreenshot("article_page");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java",
                article_title
        );


    }

    @Test
    @Features(value = {@Feature(value="search"), @Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("we open an article and swipe it to the footer")
    @Step("Start test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.ClickByArticleWithSubstring("Appium");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
}
