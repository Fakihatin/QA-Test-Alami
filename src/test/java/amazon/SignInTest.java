package amazon;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SignInTest {

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
    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Given("Navigate to Page Sign In")
    public void navigateToPageSignIn() {
        String url = "https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&0";
        driver.navigate().to(url);
    }

    @When("A User enter email {string}")
    public void aUserEnterEmail(String email) {
        driver.findElement(By.id("ap_email")).sendKeys(email);
    }
    @And("A User clicks on Continue button")
    public void aUserClicksOnContinueButton() {
        driver.findElement(By.id("continue")).click();
    }

    @And("A User enter password {string}")
    public void aUserEnterPassword(String pwd) {
        driver.findElement(By.id("ap_password")).sendKeys(pwd);
    }

    @And("A User clicks on Sign In button")
    public void aUserClicksOnSignInButton() {
        driver.findElement(By.id("signInSubmit")).click();
    }

    @Then("Directed to shoping page with initial user as {string}")
    public void directedToShopingPageWithInitialUserAs(String initialUser) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String text = driver.findElement(By.id("nav-link-accountList-nav-line-1")).getText();
        assertThat(text, is(initialUser));
    }

    @Then("Show error message {string}")
    public void showErrorMessage(String errorMessage) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String text = driver.findElement(By.className("a-list-item")).getText();
        assertThat(text, is(errorMessage));

    }



}
