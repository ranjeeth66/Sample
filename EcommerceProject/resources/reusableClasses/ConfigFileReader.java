package reusableClasses;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {

	/*
	 * public static Properties properties; public final String reportConfigPath =
	 * "C:\\Users\\ranga\\workspace\\EcommerceProject\\resources\\extent-config.xml";
	 * 
	 * public static String getReportConfigPath(){
	 * 
	 * String reportConfigPath = properties.getProperty("reportConfigPath");
	 * if(reportConfigPath!= null) return reportConfigPath; else throw new
	 * RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"
	 * ); }
	 */
	public String getReportConfigPath() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"C:/Users/ranga/workspace/EcommerceProject/resources/Configuration.properties"));
			String reportConfigPath = prop.getProperty("reportConfigPath");
			System.out.println("Repo Path:" + reportConfigPath);
			if (reportConfigPath != null)
				return reportConfigPath;
			else
				throw new RuntimeException(
						"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
		}

		catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
