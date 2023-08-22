package lib.ui.factories;

import lib.Platform;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.mibile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
