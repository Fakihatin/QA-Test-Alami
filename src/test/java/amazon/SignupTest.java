package amazon;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SignupTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Paths.get("src/test/resources/webdriver/chromedriver").toString());

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @Given("Navigate to Page Registration")
    public void navigateToPageRegistration() {
        driver.navigate().to("https://www.amazon.com/");
        //Wait Popup Menu Shown
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Start here.")).click();
    }

    @When("A User enters name {string} email {string} and password {string}")
    public void aUserEntersNameEmailAndPassword(String name, String email, String pwd) {
        driver.findElement(By.id("ap_customer_name")).sendKeys(name);
        driver.findElement(By.id("ap_email")).sendKeys(email);
        driver.findElement(By.id("ap_password")).sendKeys(pwd);
        driver.findElement(By.id("ap_password_check")).sendKeys(pwd);
    }

    @And("A User clicks on Verify button")
    public void aUserClicksOnVerifyButton() {
        driver.findElement(By.id("continue")).click();
    }

    @Then("Application shows puzzle human verification.")
    public void applicationShowsPuzzleHumanVerification() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String text = driver.findElement(By.className("a-spacing-mini")).getText();
        assertThat(text, is("Solve this puzzle to protect your account"));
    }

    @Then("Application show error message below email field.")
    public void applicationShowErrorMessageBelowEmailField() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String text = driver.findElements(By.className("a-alert-content"))
                .stream()
                .filter(webElement -> !webElement.getText().isEmpty())
                .findFirst()
                .get().getText();

        assertThat(text, is(oneOf("Enter a valid email address", "Wrong or Invalid email address or mobile phone number. Please correct and try again.")));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
