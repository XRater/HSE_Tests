import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LoginPageComponent {

    private final WebDriver driver;

    LoginPageComponent(final WebDriver driver) {
        this.driver = driver;
    }

    void login() {
        final WebElement loginField = driver.findElement(By.name(Properties.LOGIN_FIELD_SELECTOR));
        final WebElement passwordField = driver.findElement(By.name(Properties.PASSWORD_FIELD_SELECTOR));
        final WebElement loginButton = driver.findElement(By.id(Properties.LOGIN_BUTTON_SELECTOR));
        loginField.sendKeys(Properties.LOGIN);
        passwordField.sendKeys(Properties.PASSWORD);
        loginButton.click();
    }

    private static class Properties {
        static String LOGIN = "root";
        static String PASSWORD = "Qwerty123";

        static String LOGIN_FIELD_SELECTOR = "l.L.login";
        static String PASSWORD_FIELD_SELECTOR = "l.L.password";
        static String LOGIN_BUTTON_SELECTOR = "id_l.L.loginButton";
    }

}
