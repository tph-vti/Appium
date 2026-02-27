package screens;

import org.openqa.selenium.By;

import core.BaseScreen;

class DashBoardSelectors {
    // Define selectors for elements on the Dashboard screen here
    public static final By DASHBOARD_TITLE (String btnTitle) { 
        return By.xpath("//android.widget.Button[@content-desc=\"" + btnTitle + "\"]"); 
    }
}

public class DashBoardScreen extends BaseScreen {

    public DashBoardScreen() {
        super();
        // You can add verification logic here to ensure the Dashboard screen is displayed
    }

    public void selectMainMenu(String btnTitle) {
        tapElement(DashBoardSelectors.DASHBOARD_TITLE(btnTitle));
    }

}
