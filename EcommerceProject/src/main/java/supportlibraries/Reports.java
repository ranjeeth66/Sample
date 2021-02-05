package supportlibraries;

import java.io.File;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertTrue;

public class Reports {

	public static void reportWork(String fileName) {
		File detailedReport = null;
		String reportPath = System.getProperty("user.dir");
		reportPath = reportPath + File.separator + "reports" + File.separator + "RunHistory";
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
		detailedReport = new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator
				+ "TestReport" + File.separator + "Graphical_Report.html");
		new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "TestReport"
				+ File.separator + "Graphical_Report.html");
		new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "RunHistory"
				+ File.separator + fileName + File.separator + "Graphical_Report.html");
		detailedReport.renameTo(new File(System.getProperty("user.dir") + File.separator + "reports" + File.separator
				+ "RunHistory" + File.separator + fileName + File.separator + "Graphical_Report.html"));

	}
}
