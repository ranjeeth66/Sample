package supportlibraries;

import com.cucumber.listener.Reporter;

import stepDefinition.BaseTest;

public class MessageValidations extends BaseTest {

	public static void logMessage(String logMessage) {
		System.out.println(logMessage);
		Reporter.addStepLog(logMessage);
	}

	public boolean isToastMessageDisplayed(String Message) {
		boolean isMessageDisplayed = false;
		int count = 0;
		do {
			if (driver.getPageSource().contains(Message)) {
				isMessageDisplayed = true;
				break;
			}
			Waits.fluentWait(10, 2);
			count++;
		} while (count < 20);
		return isMessageDisplayed;
	}

}
