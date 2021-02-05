package supportlibraries;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import stepDefinition.BaseTest;

public class ScrollToElementinMobile extends BaseTest {

	public static void scrollUpToParticularElement(String scrollToEle) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<Object, Object> scrollObject = new HashMap<>();
		scrollObject.put("predicatestring", "value == '" + scrollToEle + "'");
		scrollObject.put("direction", "up");
		js.executeScript("mobile: scroll", scrollObject);
	}

	public static void scrollToElement(By element) {
		boolean isFound = true;
		do {
			Dimension d = driver.manage().window().getSize();
			int height = 54;
			int width = 594;
			int x = width / 2;
			int top_y = (int) (height * 0.42);
			int bottom_y = height * 20;
//		TouchAction ts = new TouchAction(driver);
//		ts.press(x, bottom_y).moveTo(x, top_y).release().perform();
//		Waits.fluentWait(4, 1);
			String a = driver.findElement(element).getAttribute("visible");
			if (a.equals("true")) {
				isFound = driver.findElements(element).size() > 0;
				break;
			}
		} while (isFound);
	}

}
