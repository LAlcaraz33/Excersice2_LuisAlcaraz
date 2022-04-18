package Base;


import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ClsReport 
{
	public static String reportLocation = "C:\\Report\\LEEAReport.html"; 
	public static ExtentReports objExtent;
	public static ExtentSparkReporter objSpark;
	public static ExtentTest objTest; 
	

	/**
	 * Setup the Report in the path specified
	 */
	public static void fnSetupReport() 
	{
		objExtent = new ExtentReports();
		objSpark = new ExtentSparkReporter(reportLocation);
		objSpark.config(
				  ExtentSparkReporterConfig.builder()
				    .theme(Theme.DARK)
				    .documentTitle("Selenium Report")
				    .build()
				);
		objExtent.attachReporter(objSpark);
	}
	
	
	/**
	 * Close the Report Generated
	 */
	public static void fnCloseReport() 
	{
		objExtent.flush();
	}

	/**
	 * Log the steps during execution time
	 * @param status
	 * @param description
	 * @param takeScreenshot
	 */
	public static void fnLog(Status status, String description, Boolean takeScreenshot)  
	{
		if(takeScreenshot)
		{ objTest.log(status, description, MediaEntityBuilder.createScreenCaptureFromPath(fnScreenshot()).build());}
		else
		{ objTest.log(status, description); }
		
	}

	
	/**
	 * Takes an screenshot of the execution step
	 * @return
	 */
	private static String fnScreenshot() 
	{
		String strFileLocation;
		try 
		{
			SimpleDateFormat formatter = new SimpleDateFormat("MMddyyy_hhmmss");
			Date date = new Date(System.currentTimeMillis());
			String strSSName = "SS_" + formatter.format(date);
			File scrFile = ((TakesScreenshot)ClsBrowser.objDriver).getScreenshotAs(OutputType.FILE);
			strFileLocation = "C:\\Report\\images\\"+ strSSName.toString() + ".png";
			FileUtils.copyFile(scrFile, new File(strFileLocation));
			return strFileLocation;
		}
		catch(Exception e) 
		{
			strFileLocation = "";
		}
		return strFileLocation;
	}
	
}