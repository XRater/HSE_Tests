import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class IssuePageComponent {

    private final WebDriver driver;

    IssuePageComponent(final WebDriver driver) {
        this.driver = driver;
    }

    Issue getIssue() {
        final By buttonSelector = By.id(Properties.SUMMARY_TEXT_SELECTOR);

        final WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSelector));

        final WebElement summaryText = driver.findElement(By.id(Properties.SUMMARY_TEXT_SELECTOR));
        final WebElement descriptionText = driver.findElement(By.id(Properties.DESCRIPTION_TEXT_SELECTOR));

        return new Issue(summaryText.getText(), descriptionText.getText());
    }

    private static class Properties {
        static String SUMMARY_TEXT_SELECTOR = "id_l.I.ic.icr.it.issSum";
        static String DESCRIPTION_TEXT_SELECTOR = "id_l.I.ic.icr.d.description";
    }
}
