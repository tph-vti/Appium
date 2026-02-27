package tests;

import core.AppiumDriverManager;
import io.qameta.allure.Allure;

import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.Helper;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Base test class for mobile automation tests using Appium
 * Provides setup and teardown methods for managing AppiumDriver lifecycle
 */
public class MobileBaseTest extends Helper {
    protected AppiumDriverManager appiumManager;

    /**
     * Setup executed before each test class.
     * Initializes AppiumDriver instance for mobile tests.
     *
     * @param context TestNG test context
     * @throws MalformedURLException if Appium server URL is malformed
     * @throws URISyntaxException    if Appium server URI is malformed
     */
    @BeforeClass
    public void setup(ITestContext context) throws MalformedURLException, URISyntaxException {
        logger.info("########################################");
        logger.info("[Setup] Starting mobile test: {}", context.getName());
        logger.info("[Setup] Test class: {}", context.getCurrentXmlTest().getName());
        logger.info("========================================");

        appiumManager = new AppiumDriverManager();
        logger.info("[Setup] AppiumDriver initialized successfully");
    }

    /**
     * Cleanup executed after each test class.
     * Quits the AppiumDriver and cleans up resources.
     */
    @AfterClass
    public void tearDown(ITestContext context) {

        logger.info("========================================");
        logger.info("[TearDown] Cleaning up AppiumDriver");
        logger.info("########################################");

        if (AppiumDriverManager.getDriver() != null) {

            try {
                String screenshotPath = captureScreenshot(context.getName() + "_afterMethod");

                if (screenshotPath != null) {
                    Path path = Paths.get(screenshotPath);

                    if (Files.exists(path)) {
                        byte[] screenshotBytes = Files.readAllBytes(path);
                        Allure.addAttachment("Screenshot on Teardown",
                                "image/png",
                                new ByteArrayInputStream(screenshotBytes),
                                "png");
                    } else {
                        logger.warn("Screenshot file not found at: {}", screenshotPath);
                    }
                } else {
                    logger.warn("Screenshot path is null");
                }

            } catch (Exception e) {
                logger.error("Failed during screenshot process", e);
            }
        }

        if (appiumManager != null) {
            appiumManager.quit();
            logger.info("[TearDown] AppiumDriver quit successfully");
        }
    }
}