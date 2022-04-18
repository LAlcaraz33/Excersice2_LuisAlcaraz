package Base;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class ClsWebElements extends ClsBrowser
{
	private int DefaultTimeout = 10;
	public static Wait<WebDriver> objFluentWait;
    public static WebDriverWait objExplicitWait;
    public static String strAction = "";
    
    
	

    public WebElement getGetWebElement(By by)
    {
        try
        {
        	ClsReport.fnLog(Status.INFO, "Step - Get Web Element: " + by.toString(), false);
            WebElement pobjElement = ClsBrowser.objDriver.findElement(by);
            ClsReport.fnLog(Status.PASS, "Step - The Web Element: " + by.toString() + " was found as expected.", false);
            return pobjElement;
        }
        catch (NoSuchElementException pobjException)
        {
        	ClsReport.fnLog(Status.FAIL, "Step - The Web Element: " + by.toString() + " was not found as expected. Exception: " + pobjException.getStackTrace(), true);
        	return null;
        }
    }
    
    
    public WebElement getWebElement(String pstrLocator)
    {
    	return getGetWebElement(By.xpath(pstrLocator));
    }
	
	
    public List<WebElement> getWebList(By by)
    {
        try
        {
            List<WebElement> pobjElement = ClsBrowser.objDriver.findElements(by);
            return pobjElement;
        }
        catch (Exception pobjException)
        {
        	System.out.println("The element was ("+  by.toString() +") not located in the page");
            return null;
        }
    }
    
    
    public List<WebElement> getWebList(String pstrLocator)
    {
        return getWebList(By.xpath(pstrLocator));
    }
    
    
    public Object GetFluentWait(final String pstrLocator) 
    {
    	return GetFluentWait(By.xpath(pstrLocator));
    }
    
    
    public Object GetFluentWait(final By by) 
    {
    	try 
    	{
    		// Waiting 30 seconds for an element to be present on the page, checking
    	 	   // for its presence once every 5 seconds.
    	    	objFluentWait = new FluentWait<WebDriver>(ClsBrowser.objDriver)
    	 	       .withTimeout(Duration.ofSeconds(30L))
    	 	       .pollingEvery(Duration.ofSeconds(3L))
    	 	       .ignoring(NoSuchElementException.class);
    	 	       
    	    	//Get Web Element and perform action
    	    	WebElement objElement = objFluentWait.until(new Function<WebDriver, WebElement>() {
    	   	     public WebElement apply(WebDriver driver) {
    	   	       return driver.findElement(by);
    	   	     }
    	   	   });
    	    	
    	    	return objElement;
    	}
    	catch(Exception e) 
    	{
    		System.out.println("The element was ("+ by.toString() +") not located in the page");
            return null;
    	}
    }
    
    
    
    
    
    public boolean Click(final By by) 
	{
    	try 
    	{
    		WebElement objElement = (WebElement) GetFluentWait(by);
    		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, DefaultTimeout);
    		objExplicitWait.until(ExpectedConditions.elementToBeClickable(by));
    		objElement.click();
    		return true;
    	}
    	catch(Exception e) 
    	{
    		return false;
    	}
	}
    
    
    public boolean Click(final String pstrLocator) 
	{
		return Click(By.xpath(pstrLocator));
	}
    
    
    
    public boolean SendKeys(final By by, String pValue) 
	{
    	try 
    	{
    		WebElement objElement = (WebElement) GetFluentWait(by);
    		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, DefaultTimeout);
    		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    		objElement.clear();
    		objElement.sendKeys(pValue);
    		return true;
    	}
    	catch(Exception e) 
    	{
    		return false;
    	}
	}
    
    public boolean SendKeys(final String pstrLocator, String pValue) 
    {
    	return SendKeys(By.xpath(pstrLocator), pValue);
    }
    
    
        
    public boolean SelectItem(final By by, String pMethod, String pValue) 
    {
    	try 
    	{
    		WebElement objElement = (WebElement) GetFluentWait(by);
        	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        	Select selectObject = new Select(objElement);
        	
        	switch (pMethod.toUpperCase()) {
			case "BYVALUE":
				selectObject.selectByValue(pValue);
				break;
			case "BYINDEX":
				selectObject.selectByIndex(Integer.parseInt(pValue));
				break;
			case "BYTEXT":
				selectObject.selectByVisibleText(pValue);
				break;
			}
    		return true;
    	}
    	catch(Exception e) 
    	{
    		return false;
    	}    	
    }
    
    public boolean SelectItem(final String pstrLocator, String pMethod, String pValue) 
    {
    	return SelectItem(By.xpath(pstrLocator), pMethod, pValue);
    }
    
    
    
    public void WaitForElement(final String pstrLocator) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    }
    
    
    
    
    public void WaitForElementClickable(final By by) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
    	objExplicitWait.until(ExpectedConditions.elementToBeClickable(by));
    }
    
    public void WaitForElementClickable(final String pstrLocator) 
    {
    	WaitForElementClickable(By.xpath(pstrLocator));
    }
    
    
    public void WaitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 30);
        wait.until(pageLoadCondition);
    }
    
    
    public void LinkText(final String pstrLocator) 
    {
    	WebElement objElement = ClsBrowser.objDriver.findElement(By.linkText(pstrLocator));
    	objElement.click();
		
    }    
}