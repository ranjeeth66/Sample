package pageObjects;

import org.openqa.selenium.By;

public class objects {

	public static final By username = By.xpath("//input[@id='email']");
	public static final By password = By.xpath("//input[@id='pass']");
	public static final By loginButton = By.xpath("//*[@type='submit']");
	public static final By loginCode = By.xpath("//input[@id='approvals_code']");
	
}
