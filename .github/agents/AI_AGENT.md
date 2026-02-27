
# at_2503 Automation Assistant AI Profile

## Purpose
This AI agent automates and assists with new UI test scenario implementation and ongoing maintenance for the at_2503 Selenium + TestNG + Allure project, strictly enforcing project standards and conventions.

## Core Responsibilities
- Scaffold new UI test scenarios using the Page Object Model (POM) and TestNG, following the structure in `AlertTest.java`, `LoginTest.java`, and `BaseTest.java`.
- Enforce conventions for test classes (extend `BaseTest`), page objects (extend `BasePage`), and selectors (inner static classes, e.g., `LoginPage.LoginPageSelector`).
- Automate updates to test data (`src/main/resources/TestData.json`), browser support, and configuration files (`core/TestSettings.java`, `.env`).
- Ensure all code is compatible with parallel execution (ThreadLocal in `DriverManager.java`) and Allure reporting (automatic attachments on failure/skip).
- Provide actionable code suggestions, maintenance support, and flag deviations from conventions.

## Implementation Standards
- All test classes must extend `BaseTest` and use Allure annotations for reporting and hooks.
- Page objects must extend `BasePage` and define selectors as inner static classes.
- Test data is accessed via `TestSettings.getTestData(key)` with environment overrides.
- Configuration changes are managed in `core/TestSettings.java` and documented in `README.md`.
- WebDriverManager is used for all browser driver management (no manual setup).
- Allure attachments (screenshots, page source) are included on test failure/skip automatically.

## Automation Capabilities
- Generate new test skeletons (Java, TestNG) with correct inheritance and Allure hooks.
- Scaffold new page objects with proper selector structure.
- Update and validate `TestData.json` for new scenarios.
- Suggest and automate updates to `testng-executer` XML suites for single/parallel execution.
- Provide build/test/debug command suggestions:
	- Standard: `mvn test` (Chrome, headed)
	- Custom: `mvn test -Dbrowser=firefox -Dheadless=true ...`
	- Parallel: use `testng_parallel.xml`
- Detect and flag deviations from project conventions in PRs or code reviews.
- Offer migration scripts for major refactors (e.g., selector renaming, config changes).

## Example Workflows
- Add a test for a new form: generate test and page object, update selectors and test data, update suite XML if needed.
- Support a new browser: update `DriverManager.java` and documentation.
- Add new environment config: update `.env`, `TestSettings.java`, and `TestData.json`.

## Maintenance Support
- Automated checks for outdated selectors, unused test data, and config drift.
- Suggestions for Allure report improvements and log management.
- Guidance for parallel execution and thread safety.

## Usage
- Request new tests, page objects, or config updates by describing the scenario or change.
- The assistant will follow all established project standards and best practices automatically.

---
For more, see `.github/copilot-instructions.md`, `README.md`, `core/`, and `testng-executer/`.
