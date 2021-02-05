package Functions;

import java.util.List;

import pageObjects.objects;
import supportlibraries.GenericValidations;
import supportlibraries.InvokeBrowser;

public class login extends InvokeBrowser {
	
	GenericValidations validate = new GenericValidations();

	public void enterCredentials(List<TestData> credentials) {
		for (TestData test : credentials) {
			String username = test.getUsername();
			String password = test.getPassword();
		}
	}
	
	public void enterUsername() {
		driver.findElement(objects.username).click();
		driver.findElement(objects.username).clear();
		driver.findElement(objects.username).sendKeys("Navyaraoo@gmail.com");
	}
	
	public void enterPassword() {
		driver.findElement(objects.password).click();
		driver.findElement(objects.password).clear();
		driver.findElement(objects.password).sendKeys("Navya@12345");
	}
	
	public void validateLogin() {
		driver.findElement(objects.loginButton).click();
		if(validate.verifyObjectonUI(objects.loginCode)) {
			System.out.println("user has logged into facebook successfully");
		}else {
			System.out.println("user isn't routed to facebook successfully post login");
		}
		}
	
}
