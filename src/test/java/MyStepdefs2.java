import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
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

public class MyStepdefs2 {

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
        String screenshotName = "screenshot_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(screenshot.toPath(), Paths.get("screenshots", screenshotName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Given("wchodzimy na strone madison island")
    public void wchodzimyNaStroneMadisonIsland() {
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @When("uzytkownik wpisuje w pole wyszukiwania {string}")
    public void uzytkownikWpisujeWPoleWyszukiwania(String query) {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.click();
        searchField.sendKeys(query);
    }

    @And("klika przycisk szukaj")
    public void klikaPrzyciskSzukaj() {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.submit();
    }

    @And("zrobimy screenshot")
    public void zrobimyScreenShot(){
        takeScreenshot();
    }

    @Then("strona wyszukiwania zawiera {string}")
    public void stronaWyszukiwaniaZawiera(String result) {
        assertTrue("Search results for:",
                driver.getTitle().contains(result));
    }
}
