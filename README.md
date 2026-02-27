# Selenium + TestNG + Allure UI Test Skeleton

This project provides a cross-browser UI automation framework targeting [https://demo.automationtesting.in/](https://demo.automationtesting.in/) using Selenium WebDriver, TestNG, and Allure reporting. It is designed for maintainable, parallelizable, and configurable browser testing.

## Features
- **Cross-browser:** Supports Chrome, Firefox, and Edge (local or Selenium Grid)
- **Configurable:** All settings via Maven/system properties, `.env`, or hardcoded defaults
- **Parallel execution:** Thread-safe WebDriver management for parallel test runs
- **Page Object Model:** Pages and selectors organized for maintainability
- **Allure reporting:** Automatic screenshots and page source on failure/skip
- **Logging:** Log4j2 for detailed execution logs


# at_2503

## System Requirements & Installation



**Requirements:**
- Java 25 or newer (JDK 25 required)
- Maven 3.6 or newer
- Allure CLI (for report viewing)


**Install Steps:**
1. Install Java 25 ([Adoptium JDK 25](https://adoptium.net/) or via Homebrew: `brew install openjdk@25`)
2. Install Maven ([Download](https://maven.apache.org/download.cgi) or via Homebrew: `brew install maven`)
3. (Optional) Install Allure CLI ([Docs](https://docs.qameta.io/allure/#_installing_a_commandline), Homebrew: `brew install allure`)
4. Clone this repo and run `mvn clean install` to download dependencies and verify setup.

---
UI automation for https://demo.automationtesting.in/ using Selenium WebDriver, TestNG, and Allure. Follows Page Object Model (POM) and supports cross-browser, parallel, and environment-driven testing.


## Quick Start
- Run all tests in Chrome: `mvn test`
- Run in Firefox/headless: `mvn test -Dbrowser=firefox -Dheadless=true`
- Generate Allure report: `allure serve target/allure-results`


## Project Structure

```
at_2503/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── core/         # Driver, config, base classes
│   │   │   ├── pages/        # Page objects (extend BasePage)
│   │   │   └── utils/        # Utilities
│   │   └── resources/
│   │       ├── TestData.json # Test data
│   │       └── log4j2.xml    # Logging config
│   └── test/
│       └── java/
│           ├── BaseTest.java # Base test class
│           └── ...           # Test classes (extend BaseTest)
├── testng-executer/          # TestNG suite XMLs
├── .github/                  # Automation/AI agent instructions
├── pom.xml                   # Maven build file
├── README.md                 # Project documentation
└── ...
```

## Structure
- `src/main/java/pages/` - Page objects
- `src/main/java/core/` - Driver, config, base classes
- `src/main/resources/TestData.json` - Test data
- `testng-executer/` - TestNG suite XMLs

## Configuration
- Override via system properties, `.env`, or `TestData.json`
- See `core/TestSettings.java` for all options

## Logging
- Log4j2, logs to `target/logs/`

## Parallel Execution
- Use `testng-executer/testng_parallel.xml` for multi-threaded runs

## TestNG Suite Execution Options

All TestNG suite XMLs are in `testng-executer/`. You can run any suite using the `-DsuiteXmlFile` property:

- **Single-threaded suite:**
   - `mvn test -DsuiteXmlFile=testng-executer/testng_single.xml`
- **Parallel suite:**
   - `mvn test -DsuiteXmlFile=testng-executer/testng_parallel.xml`

If `-DsuiteXmlFile` is not specified, the default suite is used.

**Examples:**

```
# Run single-threaded suite (default browser)
mvn test -DsuiteXmlFile=testng-executer/testng_single.xml

# Run parallel suite (2 threads, default browser)
## Developer Notes

# Run parallel suite in Firefox, headless
mvn test -DsuiteXmlFile=testng-executer/testng_parallel.xml -Dbrowser=firefox -Dheadless=true
```


## Conventions
- **Test classes** must extend `BaseTest` (handles driver setup/teardown, Allure hooks)
- **Page objects** must extend `BasePage` and use inner static selector classes for locators (see `LoginPage.LoginPageSelector`)
- **Test data** is accessed via `TestSettings.getTestData(key)` and supports environment overrides
- **Configuration** is managed in `core/TestSettings.java` and can be overridden via system properties, `.env`, or `TestData.json`
- **WebDriverManager** is used for all browser driver management (no manual setup)
- **Allure attachments** (screenshots, page source) are included on test failure/skip automatically


## How to Add New Test Scenarios

1. **Create a new Page Object (if needed):**
   - Add a new class in `src/main/java/pages/` extending `BasePage`.
   - Define selectors as inner static classes (see `LoginPage.java` for example).
2. **Add or update test data:**
   - Edit `src/main/resources/TestData.json` and add new keys/values as needed.
3. **Create a new Test Class:**
   - Add a new class in `src/test/java/` extending `BaseTest`.
   - Use Allure annotations for reporting.
   - Reference your page object and test data via `TestSettings.getTestData(key)`.
4. **Add to TestNG suite (if needed):**
   - Edit the appropriate XML in `testng-executer/` to include your new test class.
5. **Run and verify:**
   - Use the Quick Start/TestNG Suite Execution commands above.
   - Check logs in `target/logs/` and Allure reports for results.

See `AlertTest.java` and `LoginTest.java` for working examples.

## References
- See `.github/copilot-instructions.md` and `.github/agents/AI_AGENT.md` for AI agent/project automation standards

---
- WebDriverManager is used for driver binaries (no manual setup)
- Allure attachments (screenshot, page source) on test failure/skip
- Logging via Log4j2; logs in `target/logs/`
- Parallel execution via TestNG suite (`testng_parallel.xml`)
- Extend `BaseTest` for new tests; use `BasePage` for page objects


## Example Test
See `src/test/java/AlertTest.java` for a sample alert handling test.

---

## Author
Minh Pham

## Contact
For questions, support, or contributions, contact: mxstudios.dn@gmail.com
