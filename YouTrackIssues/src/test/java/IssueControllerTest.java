import static org.junit.Assert.assertEquals;
import org.junit.*;

public class IssueControllerTest {

    private final String BASE_URL = "http://localhost:8080";
    private IssueController issueController;

    @Before
    public void setUp() {
        issueController = new IssueController(BASE_URL);
        issueController.login();
    }

    @After
    public void tearDown() {
        issueController.close();
    }

    @Test
    public void testCreateIssue() {
        final Issue created = issueController.createNewIssue("Name", "some description");
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual, created);
    }

    @Test
    public void testCreateIssueWithEmptyDescription() {
        final Issue created = issueController.createNewIssue("Name", "");
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual.getSummary(), created.getSummary());
        assertEquals("No description", actual.getDescription());
    }

}