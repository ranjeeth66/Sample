package stepDefinition;

import java.io.File;
import java.io.BufferedReader;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import supportlibraries.InvokeBrowser;
import supportlibraries.PasswordEncodingAndDecoding;


public class BaseTest extends InvokeBrowser{

//	public static WebDriver driver;
	public PropertiesConfiguration config;
	public PropertiesConfigurationLayout layout;
	public Properties p;
	public static String Encodedpassword;
	public static String Id;
	public static String Username = null;
	public static String browser ;
	public static String url = null;
	public static String jenkinsexecution = null;
//	InvokeBrowser ib = new InvokeBrowser();

	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		String file = System.getProperty("user.dir");
		System.out.println(file);
		file = file + File.separator + "resources" + File.separator + "Configuration.properties";
		Reporter.loadXMLConfig(new File("resources" + File.separator + "extent-config.xml"));
		FileReader reader = null;
		config = new PropertiesConfiguration();
		layout = new PropertiesConfigurationLayout(config);
		layout.load(new InputStreamReader(new FileInputStream(file)));
		layout.save(new FileWriter(file, false));
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		p = new Properties();
		try {
			p.load(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Encodedpassword = p.getProperty("EncodedPassword");
		Id = p.getProperty("ID");
		String UserHomePath = System.getProperty("user.home");
		if (Encodedpassword.length() == 0 || Id.length() == 0) {
			InputStream CredentialsFile = new FileInputStream(
					UserHomePath + File.separator + "path where the crdentials file is placed for execution");
			BufferedReader buffer = new BufferedReader(new InputStreamReader(CredentialsFile));
			String line = buffer.readLine();
			StringBuilder strbuilder = new StringBuilder();
			while (line != null) {
				strbuilder.append(line).append("\n");
				line = buffer.readLine();
			}
			String credentialsAsString = strbuilder.toString();
			String[] valuesPostSplit = credentialsAsString.split("\\:");
			String Username = valuesPostSplit[0];
			String Password = valuesPostSplit[1];
			config.setProperty("ID", Username);

			PasswordEncodingAndDecoding passEC = new PasswordEncodingAndDecoding();
			Encodedpassword = passEC.encrypt(Password);
			config.setProperty("Encodedpassword", Encodedpassword);
			layout.save(new FileWriter(file, false));
			reader = new FileReader(file);
			p.clear();
			p.load(reader);
		} else {
			System.out.println("credentials exists in config file");
		}

		Encodedpassword = p.getProperty("Encodedpassword");
		Id = p.getProperty("ID");
		Username = p.getProperty("User");
		browser = p.getProperty("Browser");
		url = p.getProperty("ApplicationURL");
		jenkinsexecution = p.getProperty("Jenkins");		
//		openBrowser(browser,jenkinsexecution);
	}

	
	@After
	public void afterScenario(Scenario scenario) throws IOException {
//		driver.close();
		System.out.println("After");
		if(BaseTest.browser.equals("Chrome")) {
			Runtime.getRuntime().exec("TASKKILL /F /IM chrome.exe");
			Runtime.getRuntime().exec("TASKKILL /F /IM chromedriver.exe");
		}else if (BaseTest.browser.equals("IE")) {
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");	
		}
		
	}

}
