package tests;

import com.sun.org.glassfish.gmbal.Description;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MyListsTest extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
    login = "Oksana890",
    password = "Oksana777*";

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Add article to lists and delete it")
    @Description("Add an article to my list and delete it, after to find out an article is deleted")
    @Step("Start test testSaveArticleToMyList")
    @Severity(value = SeverityLevel.MINOR)
    public void testSaveArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.ClickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticlesToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if(Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not the same page after login", article_title, ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid())
        {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }
}
