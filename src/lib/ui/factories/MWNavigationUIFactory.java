package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.mibile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUIFactory {
    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
