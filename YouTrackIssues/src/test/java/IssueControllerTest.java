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
    public void testCreateIssueSimple() {
        testCorrectIssue("Name", "Some description");
    }

    @Test
    public void testCreateIssueFrontBackSpaces() {
        issueController.createNewIssue("    Name", "      Desc  ");
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual.getSummary(), "Name");
        assertEquals(actual.getDescription(), "      Desc  ");
    }

    @Test
    public void testCreateIssueMultipleSpaces() {
        issueController.createNewIssue("Na  me", "D e   s c");
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual.getSummary(), "Na me");
        assertEquals(actual.getDescription(), "D e   s c");
    }

    @Test
    public void testCreateIssueNewLine() {
        issueController.createNewIssue(
            System.lineSeparator() + "Smart" + System.lineSeparator() + "name" + System.lineSeparator(),
            System.lineSeparator() + "hello" + System.lineSeparator() + "there" + System.lineSeparator()
        );
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual.getSummary(), "Smartname");
        assertEquals(actual.getDescription(), "hello" + System.lineSeparator() + "there");
    }

    @Test
    public void testCreateIssueUnicode() {
        final char[] chars = { 0x00BE, 0x0283, 0x04C9, 0x06DD };
        final String testString = new String(chars);
        testCorrectIssue(testString, testString);
    }

    @Test
    public void testCreateIssueRussian() {
        final String testString = "Привет мир!";
        testCorrectIssue(testString, testString);
    }

    @Test
    public void testCreateIssueBigText() {
        final int len = 5000;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("a");
        }
        final String testString = sb.toString();
        testCorrectIssue(testString, testString);
    }

    @Test
    public void testCreateIssueMarkup() {
        issueController.createNewIssue(
                "# Name",
                "# Desc" + System.lineSeparator() +
                "* First" + System.lineSeparator() +
                "* Second"
        );
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual.getSummary(), "# Name");
        assertEquals(
                actual.getDescription(),
                " Desc" + System.lineSeparator() +
                " First" + System.lineSeparator() +
                " Second"
        );
    }

    @Test
    public void testCreateIssueWithEmptyDescription() {
        final Issue created = issueController.createNewIssue("Name", "");
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual.getSummary(), created.getSummary());
        assertEquals("No description", actual.getDescription());
    }

    @Test
    public void testCreateIssueWithEmptyName() {
        final Issue created = issueController.createNewIssue("Name", "Desc");
        issueController.createNewIssue("", "Desc"); // wont be created
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual, created);
    }

    private void testCorrectIssue(final String name, final String description) {
        final Issue created = issueController.createNewIssue(name, description);
        final Issue actual = issueController.getLastIssue();
        assertEquals(actual, created);
    }

}