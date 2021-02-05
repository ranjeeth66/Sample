package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "Resources/Login_Test.feature", glue = { "stepDefinition" }, tags = { "@FunctionalTest" })

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
