import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

class IssuesListPageComponent {

    private final WebDriver driver;

    IssuesListPageComponent(final WebDriver driver) {
        this.driver = driver;
    }

    void openTopIssue() {
        final WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Properties.ISSUES_CONTAINER_SELECTOR)));

        final WebElement topIssueContainer = driver.findElement(By.cssSelector(Properties.ISSUE_CONTAINER_SELECTOR));
        final WebElement topIssue = topIssueContainer.findElement(By.cssSelector(Properties.ISSUE_NAME_SELECTOR));
        topIssue.click();
    }

    private static class Properties {
        static String ISSUE_CONTAINER_SELECTOR = ".issueContainer";
        static String ISSUE_NAME_SELECTOR = ".issueId";
        static String ISSUES_CONTAINER_SELECTOR = "id_l.I.c.issuesContent";
    }
}
