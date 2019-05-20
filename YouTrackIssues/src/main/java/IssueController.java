import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IssueController {

    private final String baseUrl;

    private final WebDriver driver;
    private final LoginPageComponent loginPageComponent;
    private final MainPageComponent mainPageComponent;
    private final NewIssuePageComponent newIssuePageComponent;
    private final IssuesListPageComponent issuesListPageComponent;
    private final IssuePageComponent issuePageComponent;

    IssueController(final String baseUrl) {
        this.baseUrl = baseUrl;
        driver = new ChromeDriver();

        loginPageComponent = new LoginPageComponent(driver);
        mainPageComponent = new MainPageComponent(driver);
        newIssuePageComponent = new NewIssuePageComponent(driver);
        issuesListPageComponent = new IssuesListPageComponent(driver);
        issuePageComponent = new IssuePageComponent(driver);

        openMainPage();
    }

    public void login() {
        loginPageComponent.login();
        openMainPage();
    }

    public void close() {
        driver.close();
    }

    public Issue createNewIssue(final String summary, final String description) {
        mainPageComponent.newIssue();
        final Issue issue = newIssuePageComponent.createNewIssue(summary, description);
        openMainPage();
        return issue;
    }

    public Issue getLastIssue() {
        mainPageComponent.issues();
        issuesListPageComponent.openTopIssue();
        final Issue issue = issuePageComponent.getIssue();
        openMainPage();
        return issue;
    }

    private void openMainPage() {
        driver.get(baseUrl + "/dashboard");
    }

}
