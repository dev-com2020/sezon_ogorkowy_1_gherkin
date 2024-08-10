import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class MyStepdefs3 {
    private WebDriver driver;


    @Before
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    private void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotName = "test_03_screenshot_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(screenshot.toPath(), Paths.get("screenshots", screenshotName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver.get("http://demo-store.seleniumacademy.com/customer/account/login/");
    }


    @When("user enters {string} and {string}")
    public void userEntersAnd(String email, String password) {

        WebElement usernameInput = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.id("send2"));

        usernameInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
    }


    @Then("user is successfully login")
    public void userIsSuccessfullyLogin() {
        assertTrue(driver.getTitle().contains("My Account"));
    }


    @And("takes a screenshots")
    public void takesAScreenshots() {
        takeScreenshot();
    }

}
