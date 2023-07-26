
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;


public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
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
    public void testSaveArticleToMyList() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInputSkip();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.ClickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.ClickByArticleWithSubstring("High-level programming language");
        ArticlePageObject.addArticleToMyExistingList(name_of_folder); // добавление второй статьи в мой лист
        ArticlePageObject.closeArticle(); // возврат в список статей
        ArticlePageObject.closeArticle(); // возврат на гл страницу
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists(); // переход в список листов
        NavigationUI.swipeUpToMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
        MyListsPageObject.waitForSecondTitlePresent();

        }



        @Test
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