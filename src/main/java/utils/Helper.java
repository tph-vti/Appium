package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.AppiumDriverManager;
import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Helper {
    protected final static Logger logger = LogManager.getLogger();

    /**
     * Logs a test step message.
     * @param message
     */
    @Step("Step {message}")
    protected void logStep(String message) {
        logger.info("[STEP] " + message);
    }

    /**
     * @deprecated Avoid using Thread.sleep for test waits. Use explicit waits instead.
     *             See waitForSeconds for a recommended alternative.
     */
    @Deprecated
    protected void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logger.error("Sleep interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Waits for the specified number of seconds using WebDriverWait. This is preferred over Thread.sleep.
     * Usage: waitForSeconds(driver, seconds);
     * @param driver The WebDriver instance
     * @param seconds Number of seconds to wait
     */
    protected void waitForSeconds(WebDriver driver, long seconds) {
        new WebDriverWait(driver, java.time.Duration.ofSeconds(seconds))
                .until(d -> false);
    }

    /**
     * Retrieves the value of an environment variable, or returns a default value if not set.
     * @param varName Name of the environment variable
     * @param defaultValue Default value to return if variable is not set
     * @return Value of the environment variable or default value
     */
    public static String getEnvVariable(String varName, String defaultValue) {
        String value = System.getenv(varName);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Load JSON file and store as JSONObject or JSONArray
     * @param filePath path to JSON file
     */
    public static JSONObject loadJsonFile(String filePath) {
        try {
            String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (Exception e) {
            logger.error("Failed to load JSON file: {}", filePath, e);
            return null;
        }
    }

    public String captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) AppiumDriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            
            // Lưu ảnh vào thư mục "screenshots" với tên là tên test case
            String filePath = Paths.get(Constants.SCREENSHOTS_PATH, testName+".png").toString();
            File destination = new File(filePath);
            FileHandler.copy(source, destination);
            
            logger.info("Screenshot taken: " + destination.getAbsolutePath());
            return destination.getAbsolutePath();
        } catch (IOException e) {
            logger.error("Exception while taking screenshot: " + e.getMessage());
        }
        return null;
    }
}
