package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
			tags = "not @ignore",
			features = {"src/test/resources/features"},
			glue = "stepDefinitions",
			plugin = {
					  "json:target/cucumber-reports/Cucumber-report.json",
					  "junit:target/cucumber-reports/Cucumber-report.xml",
					  "html:target/cucumber-reports/Cucumber-report.html" ,
					  "usage"
			},
			monochrome = true,
			dryRun = false
		)
public class TestRunner{
//	@Override
//	@DataProvider(parallel = true)
//	public Object[][] scenarios() {
//		return super.scenarios();
//	}
}
