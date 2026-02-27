package tests;
import screens.DashBoardScreen;
import screens.LoginScreen;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.Test;

public class MobileTest extends MobileBaseTest {
    @Test()
    @Description("Verify that user can navigate to Register Page from Login Page")
    @Severity(SeverityLevel.CRITICAL)
    public void testDashboardScreenDisplays() {
        DashBoardScreen dashboardScreen = new DashBoardScreen();
        LoginScreen loginScreen = new LoginScreen();

        logStep("1. Select Login Menu");
        dashboardScreen.selectMainMenu("Login");

        logStep("2. Enter Username and Password to login form");
        loginScreen.enterLoginForm("user@example.com", "password123");
        loginScreen.clickLoginButton();

    }
}
