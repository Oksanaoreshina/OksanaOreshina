package lib.ui.factories;

import lib.ui.mibile_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public class ArticlePageObjectFactory {
        public static ArticlePageObjectFactory get(RemoteWebDriver driver)
        {
            if(Platform.getInstance().isAndroid()) {
                return new AndroidArticlePageObject(driver);
            } else {
                return new MWArticlePageObject(driver);
            }
        }
    }
}

