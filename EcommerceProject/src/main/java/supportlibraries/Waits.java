package supportlibraries;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waits extends InvokeBrowser {

	public static void explicitWait(long timOutinSeconds, By Element) {
		WebDriverWait wait = new WebDriverWait(driver, timOutinSeconds);
		if (wait.until(ExpectedConditions.visibilityOfElementLocated(Element)).isDisplayed()) {
			// Logger.logMessage("Element is found");
		} else {
			// Logger.logmessage("Element not found");
		}
	}

	public static void fluentWait(long timOutinSeconds, long pollingWait) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timOutinSeconds, TimeUnit.SECONDS)
				.pollingEvery(pollingWait, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

	}

}
