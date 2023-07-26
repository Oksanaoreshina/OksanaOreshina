package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
    MY_LISTS_BUTTON = "//*[@class = 'android.widget.ImageView'][@bounds = '[288,1596][360,1668]']",
    MY_LIST_LINK = "//*[@text = 'Learning programming']";
    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_BUTTON), //клик на переход к спискам листов
                "Cannot find saved lists",
                5
        );
    }

    public void swipeUpToMyList()
    {
        this.swipeUpToFindElement(
                By.xpath(MY_LIST_LINK),
                "Cannot find my reading list",
                20

        );
    }



}
