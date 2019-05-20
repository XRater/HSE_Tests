import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class NewIssuePageComponent {

    private final WebDriver driver;

    NewIssuePageComponent(final WebDriver driver) {
        this.driver = driver;
    }

    Issue createNewIssue(final String summary, final String description) {
        final By buttonSelector = By.name(Properties.SUMMARY_FIELD_SELECTOR);

        final WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonSelector));

        final WebElement summaryField = driver.findElement(By.name(Properties.SUMMARY_FIELD_SELECTOR));
        final WebElement descriptionField = driver.findElement(By.name(Properties.DESCRIPTION_FIELD_SELECTOR));
        final WebElement submitButton = driver.findElement(By.id(Properties.SUBMIT_BUTTON_SELECTOR));

        summaryField.clear();
        summaryField.sendKeys(summary);
        descriptionField.clear();
        descriptionField.sendKeys(description);
        submitButton.click();

        return new Issue(summary, description);
    }

    private static class Properties {
        static String SUMMARY_FIELD_SELECTOR = "l.D.ni.ei.eit.summary";
        static String DESCRIPTION_FIELD_SELECTOR = "l.D.ni.ei.eit.description";
        static String SUBMIT_BUTTON_SELECTOR = "id_l.D.ni.ei.submitButton_74_0";
    }
}
