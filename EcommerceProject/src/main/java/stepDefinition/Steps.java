package stepDefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Functions.login;
import Functions.TestData;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LoginPage;
import supportlibraries.InvokeBrowser;

public class Steps extends InvokeBrowser {

	public WebDriver driver;
	InvokeBrowser ib = new InvokeBrowser();
	login l = new login();
	
	@Given("^user launches the browser$")
	public void user_launches_the_browser() throws Throwable {
		ib.openBrowser("Chrome", "false");
	}

	@Then("^user enters username$")
	public void user_enters_username() throws Throwable {
		l.enterUsername();
		
	}

	@Then("^user enters password$")
	public void user_enters_password() throws Throwable {
		l.enterPassword();
	}
	
	@Given("^User routes to Facebook Home page$")
	public void user_routes_to_Facebook_Home_page() throws Throwable {
		l.validateLogin();
	}

	@Then("^user enters username using \"([^\"]*)\"$")
	public void user_enters_username_using(String arg1) throws Throwable {
	}

	@Then("^user enters password using \"([^\"]*)\"$")
	public void user_enters_password_using(String arg1) throws Throwable {
	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() throws Throwable {
	}

	@Then("^validates the error message populated$")
	public void validates_the_error_message_populated() throws Throwable {
	}

	@Given("^user is on Adidas Home Page$")
	public void user_is_on_Adidas_Home_Page() throws Throwable {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ranga\\workspace\\EcommerceProject\\Resources\\chromedriver.exe");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://shop.adidas.co.in/");

	}

	@When("^user click in login link$")
	public void user_click_in_login_link() throws Throwable {
		LoginPage l = new LoginPage(driver);
		l.link().click();
	}

	@When("^enter username and password$")
	public void enter_username_and_password() throws Throwable {
		LoginPage l = new LoginPage(driver);
		l.name().sendKeys("rayudu176@gmail.com");
		l.pwd().sendKeys("9493094176");
		l.submitButton().click();

	}

	@Then("^error message will display$")
	public void error_message_will_display() throws Throwable {
		LoginPage l = new LoginPage(driver);
		WebElement ele = l.errorMessageText();
		if (ele.isDisplayed()) {
			System.out.println("Error Message Dispalyed Successfully");
		}

		driver.quit();
	}

	@Then("^user enters values using:$")
	public void user_enters_values_using(List<TestData> credentials) throws Throwable {
		l.enterCredentials(credentials);

	}

}
