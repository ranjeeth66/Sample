package testRunner;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.cucumber.listener.Reporter;
import org.junit.BeforeClass;
import com.cucumber.listener.ExtentProperties;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import stepDefinition.BaseTest;
import supportlibraries.InvokeBrowser;
import supportlibraries.PasswordEncodingAndDecoding;
import supportlibraries.Reports;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:./reports/test-report/Graphical_Report.html" }, 
format = {"pretty","html:reports/test-report","json:./reports/test-report/report.json" },
monochrome = true, dryRun = false, features = "Resources/Feature/Testing.feature", glue = "stepDefinition", tags = {
						"@test1" })

public class TestRunner extends InvokeBrowser{
	
	

	@BeforeClass
	public static void setup() {
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties
				.setReportPath("reports" + File.separator + "test-reports" + File.separator + "regressionreport.html");
		extentProperties.setProjectName("Cucumberframework");
	}
	
	
	
	@AfterClass
	public static void teardown() throws ConfigurationException, FileNotFoundException

	{
		BaseTest baseTest = new BaseTest();
		reportCreation();
		String fileName = null;
		FileWriter fWrite = null;
		BufferedWriter bWrite = null;
		String file = System.getProperty("user.dir");
		file = file + File.separator + "resources" + File.separator + "Configuration.properties";
		baseTest.config = new PropertiesConfiguration();
		baseTest.layout = new PropertiesConfigurationLayout(baseTest.config);
		baseTest.layout.load(new InputStreamReader(new FileInputStream(file)));
		baseTest.config.setProperty("ID", "");
		baseTest.config.setProperty("EncodedPassword", "");
		// Reporter.loadXMLConfig("resources/extent-config.xml");
		// Reporter.loadXMLConfig(new
		// File(ConfigFileReader.getInstance().getConfigReader().getReportConfigPath()));
//		Reporter.loadXMLConfig(
//				new File("C:/Users/naren/Desktop/Backup-Navya/EcommerceProject/resources/extent-config.xml"));
//		Reporter.setSystemInfo("User Name", "naren");
//		Reporter.setSystemInfo("Application Name", "Ecommerce Application");
//		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
//		Reporter.setSystemInfo("Environment", "Test");
//		Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
	}

	public static void reportCreation() {
		String fileName = null;
		String file = System.getProperty("user.dir");
		file = file + File.separator + "resources" + File.separator + "Configuration.properties";
		Reporter.loadXMLConfig(new File("resources" + File.separator + "extent-config.xml"));
		FileReader reader = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		fileName = new SimpleDateFormat("MM_dd_YYYY_HH_mm_ss").format(new java.util.Date());
		if (fileName.length() > 0) {
			Reports.reportWork(fileName);
		}

	}

}
