import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
//import io.cucumber.core.cli.Main;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = {"pretty"}, features = "src/test/resources/features", glue = "test.stepdefs")

public class CucumberRunner {
}