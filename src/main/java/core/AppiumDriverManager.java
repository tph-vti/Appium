package core;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.BaseOptions;
import utils.Helper;
import utils.Constants;


/**
 * AppiumManager handles AppiumDriver lifecycle management for mobile test automation.
 * Uses ThreadLocal to ensure thread-safe AppiumDriver instances for parallel test execution.
 * Implements factory pattern for iOS and Android platforms.
 */
public class AppiumDriverManager extends Helper {
    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();

    /**
     * {
        "platformName": "Android",
        "appium:automationName": "uiautomator2",
        "appium:platformVersion": "16",
        "appium:app": “/PATH TO YOUR FILE/AndroidNativeDemoApp.apk",
        "appium:appPackage": "com.wdiodemoapp",
        "appium:appActivity": ".MainActivity",
        "appium:appWaitActivity": "com.wdiodemoapp.*",
        "appium:noReset": false,
        "appium:fullReset": false
        }
     */

    public AppiumDriverManager() throws MalformedURLException{
        super();
        // initialAppiumDriver();  
        initBaseOptions();
    }

    public void initialAppiumDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
            .setApp(Constants.ANDROID_DEMO_APP_PATH)
            .setAppActivity(TestSettings.MAIN_ACTIVITY)
            .setAppPackage(TestSettings.APP_PACKAGE)
            .setAppWaitActivity(TestSettings.APP_WAIT_ACTIVITY)
            
            .setPlatformVersion(TestSettings.PLATFORM_VERSION)
            .setNewCommandTimeout(Duration.ofSeconds(60));
        AndroidDriver driver = new AndroidDriver(URI.create(TestSettings.APPIUM_SERVER_URL).toURL(), options);
        appiumDriver.set(driver);
    }

    public void initBaseOptions() throws MalformedURLException {
        @SuppressWarnings("rawtypes")
        BaseOptions baseOptions = new BaseOptions();
        baseOptions.setPlatformName(TestSettings.APPIUM_PLATFORM);
        baseOptions.setAutomationName("uiautomator2");
        baseOptions.setCapability("app", Constants.ANDROID_DEMO_APP_PATH);
        baseOptions.setCapability("platformVersion", TestSettings.PLATFORM_VERSION);
        baseOptions.setCapability("appPackage", TestSettings.APP_PACKAGE);
        baseOptions.setCapability("appActivity", TestSettings.MAIN_ACTIVITY);
        baseOptions.setCapability("appWaitActivity", TestSettings.APP_WAIT_ACTIVITY);
        AndroidDriver driver = new AndroidDriver(URI.create(TestSettings.APPIUM_SERVER_URL).toURL(), baseOptions);
        appiumDriver.set(driver);
    }   
    

    public static AppiumDriver getDriver() {
        return appiumDriver.get();
    }

    public void quit() {
        AppiumDriver driver = appiumDriver.get();
        if (driver != null) {
            driver.quit();
            appiumDriver.remove();
        }
    }
}
