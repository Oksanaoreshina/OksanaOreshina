package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject{
    private static final String
    MY_LISTS_BUTTON,
    MY_LIST_LINK,
    OPEN_NAVIGATION;
    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Clicking '{OPEN_NAVIGATION}' to open navigation")
    public void openNavigation()
    {
        if(Platform.getInstance().isMW())
        {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Не находит кнопку навигации в панели", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open my '{MY_LISTS_BUTTON}'")
    public void clickMyLists()
    {
        if(Platform.getInstance().isMW())
        {
            this.tryClickElementWithFewAttempts(MY_LIST_LINK, "Cannot find navigation button to My Lists", 5);
        }
        this.waitForElementAndClick(
                (MY_LISTS_BUTTON), //клик на переход к спискам листов
                "Cannot find saved lists",
                5
        );
    }

    @Step("Swipe to '{MY_LIST_LINK}' for Android")
    public void swipeUpToMyList()
    {
        this.swipeUpToFindElement(
                (MY_LIST_LINK),
                "Cannot find my reading list",
                20

        );
    }



}
