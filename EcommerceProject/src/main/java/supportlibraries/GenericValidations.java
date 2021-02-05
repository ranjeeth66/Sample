package supportlibraries;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepDefinition.BaseTest;

public class GenericValidations extends InvokeBrowser {

	// verifyObjectExistsjavaScript

	public boolean verifyObjectExistjavaScript(By strobject, String Objname, String ObjType) {
		try {
			if (driver.findElement(strobject) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// clear Storage

	public void clearLocalStorage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(String.format("window.localstorage.clear();"));
	}
	
	
	//verify object exists by only object
	
	public static boolean verifyObjectonUI(By strObject) {		
		try {
			if (driver.findElement(strObject) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	// generate a random string

	static final String a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public String randomString(int len) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(a.charAt(rnd.nextInt(a.length())));
		}
		return sb.toString();
	}

	// verify object exist using java
	public boolean verifyObjectExist(By strobject, String Objname, String ObjType) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			if (driver.findElement(strobject) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			assertTrue(Objname + " of type" + ObjType + "is not visible", driver.findElement(strobject).isDisplayed());
			;
			return false;
		}
	}

	// select value from dropdown
	public void selectValue(By strobject, String Objname, String ObjType, String strvalue) throws Exception {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			if (driver.findElement(strobject) != null) {
				Select dropdownSiteType = new Select(driver.findElement(strobject));
				dropdownSiteType.selectByVisibleText(strvalue);
			}
		} catch (Exception e) {
			assertTrue(Objname + " not able to select", false);
			throw new Exception(Objname + "not available to select");
		}
	}

	// verify if ObjectNotExist
	public void ObjectNotExist(By strobject, String Objname, String ObjType) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver.findElement(strobject) != null && driver.findElement(strobject).isDisplayed()) {
			try {
				throw new Exception(Objname + " is visible");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	// EnterData in field
	public void EnterData(By strobject, String Objname, String ObjType, String objdata) throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try {
			if (driver.findElement(strobject) != null) {
				if (ObjType == "WEBEDIT") {
					driver.findElement(strobject).sendKeys(objdata);
				}
				if (ObjType == "WEBLIST") {
					Select dropdownSiteType = new Select(driver.findElement(strobject));
					dropdownSiteType.selectByVisibleText(objdata);
				}
			} else {
				assertTrue("Given" + Objname + " is not visible", false);
				throw new Exception("Given" + Objname + " is not visible");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// click object
	public void clickObject(By strobject, String Objname, String ObjType) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver.findElement(strobject) != null && driver.findElement(strobject).isDisplayed()) {
			try {
				if (driver.findElement(strobject) != null) {
					driver.findElement(strobject).click();
				}
			} catch (Exception e) {
				assertTrue("Given" + Objname + " is not visible", false);
				System.out.println(e);
			}
		}
	}

	// getText
	public String GetText(By strobject, String Objname, String ObjType) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver.findElement(strobject) != null) {
			return driver.findElement(strobject).getText();
		} else {
			throw new ElementNotVisibleException(Objname + "not found");
		}
	}
	
	//ContextClick
	public void contextClick(By webElement, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement((webElement))));
			if(driver.findElement(webElement) != null) {
			Actions a = new Actions(driver);	
			a.contextClick(driver.findElement(webElement)).perform();
			}		
		}catch (Exception e) {
			assertTrue("Given" + webElement + " is not visible", false);
			System.out.println(e);
		}
	}
	
	//DoubleClick
		public void doubleClick(By webElement, String elementName) {
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement((webElement))));
				if(driver.findElement(webElement) != null) {
				Actions a = new Actions(driver);	
				a.doubleClick(driver.findElement(webElement)).perform();
				}		
			}catch (Exception e) {
				assertTrue("Given" + webElement + " is not visible", false);
				System.out.println(e);
			}
		}
		
		//hover
		public void hoverOverElement(By webElement, String elementName) {
			try {
				WebDriverWait wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement((webElement))));
				if(driver.findElement(webElement) != null) {
				Actions a = new Actions(driver);	
				a.moveToElement(driver.findElement(webElement)).perform();
				}		
			}catch (Exception e) {
				assertTrue("Given" + webElement + " is not visible", false);
				System.out.println(e);
			}
		}

	// uploadFile
	public void uploadFile(String Filename) throws InterruptedException, AWTException {
		Thread.sleep(5000);
		StringSelection stringSelection = new StringSelection(Filename);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
	}

	BaseTest bt = new BaseTest();

	// dbConnection
	public List<String> dbConnect(String Query) throws Exception {
		PasswordEncodingAndDecoding pec = new PasswordEncodingAndDecoding();
		String username = bt.Username;
		String encodedpassword = bt.Encodedpassword;
		String password = pec.decrypt(encodedpassword);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection("sql url", username, password);
		List<String> data = new ArrayList<String>();
		if (con != null) {
			System.out.println("DB connection successfully established");
		}
		Statement stmt = con.createStatement();
		ResultSet test = stmt.executeQuery(Query);
		while (test.next()) {
			data.add(test.getString(1));
		}
		return data;
	}
	
	//horizontalScroll
	public void scroll(By scrollelement , int x_cordinate, int y_cordinate) {
		WebElement element = driver.findElement(By.xpath("//div[@ref='eBodyHorizontalScrollViewport']"));
		Actions  a = new Actions(driver);
		a.clickAndHold(element).moveByOffset(x_cordinate, y_cordinate).release().build().perform();
	}
}
