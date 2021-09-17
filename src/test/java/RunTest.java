import courgette.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = {"amazon"},
        tags = {"@sign_in"},
        plugin = {
                "pretty",
                "json:build/cucumber-report/cucumber.json",
                "html:build/cucumber-report/cucumber.html",
                "junit:build/cucumber-report/cucumber.xml"}
)
public class RunTest {
}
