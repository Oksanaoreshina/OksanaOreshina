package lib.ui.mibile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE1 = "css:#content h1",
       FOOTER_ELEMENT = "scc:footer",
       OPTION_ADD_TO_MY_LIST_BUTTON = "css:#page-action li#ca-watch.mw-ui-icon-mf-watch button",
       OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";

    }
    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
