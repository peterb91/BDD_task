package reportElements;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/resources/reportElements/get_second_biggest_element.feature"},
glue = {"."})
public class RunCucumberTest {
}
