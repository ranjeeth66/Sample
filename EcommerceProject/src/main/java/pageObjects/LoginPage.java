package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='headerLogin']")
	WebElement loginLink;

	@FindBy(xpath = "//input[@id='usrName']")
	WebElement username;

	@FindBy(xpath = "(//input[@id='password'])[1]")
	WebElement password;

	@FindBy(xpath = "//input[@name='evenlogin']")
	WebElement submit;

	@FindBy(xpath = "//div[@id='messageBlock']")
	WebElement errorMessage;

	public WebElement link() {
		return loginLink;
	}

	public WebElement name() {
		return username;
	}

	public WebElement pwd() {
		return password;
	}

	public WebElement submitButton() {
		return submit;
	}

	public WebElement errorMessageText() {
		return errorMessage;
	}
}
