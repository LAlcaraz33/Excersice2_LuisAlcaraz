package Amz;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import Base.ClsReport;
import Base.ClsWebElements;

public class AmzLogin extends ClsWebElements {
	
	//xpaths
	
	String Email = "intern13@agilethought.com";
	String Password = "Dianasalmeron33";
	String IdentifyBtn = "//span[@id = 'nav-link-accountList-nav-line-1']";
	String EmailTxt = "//input[@id = 'ap_email']";
	String NextBtn = "//input[@id = 'continue']";
	String PasswordTxt = "//input[@id = 'ap_password']"; 
	String StartSession = "//input[@id = 'signInSubmit']";
	String PromotionBtn = "//a[text() = 'Promociones']";
	String FlashMenuBtn = "//span[text() = 'Oferta relámpago']";
	String Items = "//div[@class = 'DealContent-module__truncate_sWbxETx42ZPStTc9jwySW']";
	String ctIndex = "//li[@class = 'a-disabled'][3]";
	String nextPage = "//li[@class = 'a-last']";
	String Price = "//span[@class = 'a-price-whole']";
	JavascriptExecutor js = (JavascriptExecutor) objDriver;
	
	
	//Methods
	
	/**
	 * enter to amazon identify menu.
	 */
	public void loginMenu()
	{
		WaitForLoad();
		WaitForElementClickable(IdentifyBtn);
		Click(IdentifyBtn);
	}
	
	/**
	 * enter amazon registered email and go to next screen.
	 */
	public void enterUsername() 
	{
		WaitForLoad();
		SendKeys(EmailTxt, Email);
		WaitForElementClickable(NextBtn);
		Click(NextBtn);
	}
	
	/**
	 * enter amazon password for entered email and go to next screen.
	 */
	public void enterPassword()
	{
		WaitForLoad();
		SendKeys(PasswordTxt,Password);
		WaitForElementClickable(StartSession);
		Click(StartSession);
	}
	
	
	/**
	 * Go to Promotions Menu.
	 */
	public void Promotions()
	{
		WaitForLoad();
		WaitForElementClickable(PromotionBtn);
		Click(PromotionBtn);
	}
	
	/**
	 * Go to Flash Deals.
	 *  
	 */
	public void flashMenu () throws InterruptedException
	{
		WaitForLoad();
		js.executeScript("window.scrollBy(0,1500)","");
		WaitForElementClickable(FlashMenuBtn);
		Click(FlashMenuBtn);
		WaitForLoad();
		Thread.sleep(2000);
	}
	
	
	/**
	 * Obtain List of Items  
	 */
	public void getItems () throws Exception
	{
		int n=1;
		String cellValue;
		ExcelWrite objExcel = new ExcelWrite();		
		WebElement objText = getWebElement(ctIndex);
		String ntxt = objText.getAttribute("innerText");
		ExcelWrite.createExcel();
		int pageCount = Integer.parseInt(ntxt);
		System.out.println(getWebList(Items));
		
		for(int i =0; i<pageCount; i++)
		{
		Thread.sleep(2500);
		List <WebElement> flashElements = getWebList(Items);
			for(WebElement LIST : flashElements) {
				System.out.println(LIST.getAttribute("innerText"));
				System.out.println(" ");
				cellValue = LIST.getAttribute("innerText");
				objExcel.addItems(n, cellValue);
				ClsReport.fnLog(Status.PASS, "Product " + n + ":  "  + cellValue, false);
				n++;
				}
		
		if(i==pageCount-1)
		{
			js.executeScript("window.scrollBy(0,3500)","");
			ClsReport.fnLog(Status.PASS,"Item List Finished.", true);
			objExcel.saveExcel();
		}else
			{
				js.executeScript("window.scrollBy(0,5000)","");
				Click(nextPage);
			}
		}
	}
	
}
