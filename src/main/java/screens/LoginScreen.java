package screens;

import org.openqa.selenium.By;

import core.BaseScreen;

class LoginSelectors {
    // Define selectors for elements on the Login screen here
    public static final By TXT_EMAIL = By.xpath("//android.widget.EditText[@content-desc='input-email']");
    public static final By TXT_PASSWORD = By.xpath("//android.widget.EditText[@content-desc='input-password']");

    public static final By BTN_LOGIN = By.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']");
}

public class LoginScreen extends BaseScreen {

    public LoginScreen() {
        super();
        // You can add verification logic here to ensure the Login screen is displayed
    }

    public void enterLoginForm(String email, String password) {
        tapElement(LoginSelectors.TXT_EMAIL);
        enterText(LoginSelectors.TXT_EMAIL, email);
        logger.info("Entered email: {}", email);

        tapElement(LoginSelectors.TXT_PASSWORD);
        enterText(LoginSelectors.TXT_PASSWORD, password);
        logger.info("Entered password: {}", password);
    }

    public void clickLoginButton() {
        tapElement(LoginSelectors.BTN_LOGIN);
    }

}
