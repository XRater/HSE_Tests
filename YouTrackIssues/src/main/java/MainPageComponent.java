import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class MainPageComponent {

    private final WebDriver driver;

    MainPageComponent(final WebDriver driver) {
        this.driver = driver;
    }

    void newIssue() {
        final By buttonSelector = By.linkText(Properties.NEW_ISSUE_BUTTON_SELECTOR);

        final WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSelector));
        final WebElement newIssueButton = driver.findElement(buttonSelector);
        newIssueButton.click();
    }

    void issues() {
        final By buttonSelector = By.linkText(Properties.ISSUES_BUTTON_SELECTOR);

        final WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSelector));

        final WebElement newIssueButton = driver.findElement(buttonSelector);
        newIssueButton.click();
    }

    private static class Properties {
        static String NEW_ISSUE_BUTTON_SELECTOR = "Create Issue";
        static String ISSUES_BUTTON_SELECTOR = "Issues";
    }
}
