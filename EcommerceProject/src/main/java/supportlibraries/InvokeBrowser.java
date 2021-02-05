package supportlibraries;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepDefinition.BaseTest;

public class InvokeBrowser {

	public static WebDriver driver;
	public static WebDriverWait wait=null;
	public void openBrowser(String browser, String jenkinsExecution) throws Exception {
		String driverPathHeadless = System.getProperty("user.dir");
		DesiredCapabilities capabilities = null;
//		BaseTest b = new BaseTest();
		if(jenkinsExecution.equalsIgnoreCase("True")) {
			switch (browser.toLowerCase()) {
			case "ie":
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driverPathHeadless = driverPathHeadless + "\\EcommerceProject\\resources\\IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", driverPathHeadless);
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(BaseTest.url);
				break;
			case "chrome":
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("window-size=1980,960");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driverPathHeadless = driverPathHeadless + "\\EcommerceProject\\resources\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPathHeadless);
				driver = new ChromeDriver(options);
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(BaseTest.url);
				break;
			case "firefox":
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				driverPathHeadless = driverPathHeadless + "\\EcommerceProject\\resources\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPathHeadless);
				FirefoxOptions option = new FirefoxOptions();
				option.setBinary(firefoxBinary);
				option.addPreference("javascript.enabled", true);
				option.setBinary(firefoxBinary);
				option.addArguments("window-size=1980,960");
				driver = new FirefoxDriver(option);
				driver.get(BaseTest.url);
				break;			
				default:
			System.out.println("Invalid Choice");
			throw new Exception("Invalid browser choice");
			}
			} else {
				String driverpath = System.getProperty("user.dir");
				switch(browser.toLowerCase()) {
			case "ie":
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				driverpath = driverpath + "\\EcommerceProject\\resources\\IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", driverpath);
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(BaseTest.url);
				break;
			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments(BaseTest.url);
				options.setExperimentalOption("useAutomationExtension", false);
//				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driverpath = driverpath + "\\resources\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverpath);
				driver = new ChromeDriver(options);
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(BaseTest.url);
				break;
			case "firefox":
				driverpath = driverpath + "\\resources\\chromedriver.exe";
				System.setProperty("webdriver.gecko.driver", driverpath);			
				driver = new FirefoxDriver();
				driver.get("");
				break;			
				default:
			System.out.println("Invalid Choice");
			throw new Exception("Inabvlid browser choice");		
		}
	}
	}

}
