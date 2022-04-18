package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import com.aventstack.extentreports.Status;
import Amz.AmzLogin;
import Amz.ExcelWrite;
import Base.ClsBrowser;
import Base.ClsReport;
import Base.ClsWebElements;

public class Amz_Ex extends ClsWebElements
{
	@Rule public TestName TC_Name = new TestName();
	public String URL = "https://www.amazon.com.mx/";
	public static String excelLocation = "C:\\Report";
	
	@BeforeClass
	public static void beforeClass() 
	{
		ClsReport.fnSetupReport();
		ExcelWrite.createExcel();
	}
	
	@Before
	public void setup() 
	{
		ClsBrowser.BrowserName = "CHROME";
		OpenBrowser();
	} 
	
	
	@Test
	public void FlashDealsItems()
	{
		
		try 
		{
			ClsReport.objTest = ClsReport.objExtent.createTest("Items Registration");
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Navigation to URL.", false);
			AmzLogin objLogin = new AmzLogin();
			objLogin.loginMenu();
			ClsReport.fnLog(Status.PASS, "Enter Login Menu.", false);
			objLogin.enterUsername();
			ClsReport.fnLog(Status.PASS, "Username Entered.", false);
			objLogin.enterPassword();
			ClsReport.fnLog(Status.PASS, "Password Entered.", false);
			objLogin.Promotions();
			ClsReport.fnLog(Status.PASS, "Promotions Page.", false);
			objLogin.flashMenu();
			ClsReport.fnLog(Status.PASS, "Flash Deals Filter Applied.", true);
			objLogin.getItems();
			ClsReport.fnLog(Status.PASS, "Items Retrived.",false);
			Thread.sleep(3500);
		}
		catch(Exception e) 
		{
			ClsReport.fnLog(Status.FAIL, "The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage() , true);
		}
	}
		
		
	@After
	public void tearDown() 
	{
		CloseBrowser();
		ClsReport.fnCloseReport();
	}

}

