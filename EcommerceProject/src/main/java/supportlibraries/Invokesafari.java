package supportlibraries;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Invokesafari {

	public static GenericValidations gv = new GenericValidations();
	public static String deviceNumber = "123456";
	// add seetest jar for this and then should be able to create instance of seetest and start working on it
//	public static SeeTestClient seetest;
	public static String path = System.getProperty("user.dir");
	public String accesskey = "";// accesskey of user in seetest
	public static IOSDriver<IOSElement> driver2;
	public DesiredCapabilities dc = new DesiredCapabilities();

	public static String bundleIDofDevice = "";
	public static String bundleIDofSafari = "com.apple.mobilesafari";

	public void invokeDevice() {
		dc.setCapability("accesskey", accesskey);
		dc.setCapability(MobileCapabilityType.UDID, deviceNumber);
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1500);
		dc.setCapability("bundleIDofDevice", bundleIDofDevice);
		dc.setCapability("takeScreenshot", true);
		dc.setCapability("noReset", true);
//		driver2 = new IOSDriver<>(new URL("seetestURL"), dc);
//		seetest = new SeeTestClient(driver2);
	}

}
