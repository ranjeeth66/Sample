package supportlibraries;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import stepDefinition.BaseTest;

public class CaptureScreenshot extends BaseTest {
	static Scenario scenario;
	InvokeBrowser ib = new InvokeBrowser();

	public static void screenShot(String screenshotName) {
		String screenshot = screenshotName;
		screenCapture(driver, screenshot);
	}

	public static void screenCapture(WebDriver driver, String screenshotName) {
		try {
			String fileName = null;
			String reportPath = System.getProperty("user.dir");
			reportPath = reportPath + File.separator + "UIScreenshots";
			fileName = new SimpleDateFormat("MM_dd_YYYY_HH_mm_ss").format(new java.util.Date());
			fileName = "Run_" + fileName;
			String newDirName = fileName;
			String dirPath = reportPath;

			File newDirectory = new File(dirPath);
			boolean isCreated = newDirectory.mkdirs();

			File oneMoreDirectory = new File(dirPath + File.separator + newDirName);
			isCreated = oneMoreDirectory.mkdir();
			if (isCreated) {
			} else {
				assertTrue("Report Could not be created successfully", false);
			}
			TakesScreenshot a = (TakesScreenshot) driver;
			File source = a.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File(System.getProperty("user.dir") + File.separator + "UIScreenshots"
					+ File.separator + newDirName + File.separator + screenshotName + ".png"));
			String destinationPath = System.getProperty("user.dir") + File.separator + "UIScreenshots" + File.separator
					+ newDirName + File.separator + screenshotName + ".png";
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			System.out.println("screenshotName");

		} catch (Exception e) {

		}
	}
}
