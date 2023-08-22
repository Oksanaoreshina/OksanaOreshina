package lib.ui.mibile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static
    {
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
        MY_LIST_LINK = "css:a[data-event-name='watchlist']";
    }

    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

}
