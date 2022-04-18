package Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClsBrowser {
	
	
	public static String BrowserName;
	protected static WebDriver objDriver;
    public static WebDriverWait wait;
    
    
    public ClsBrowser() {
    	
		BrowserName = "CHROME";
	}
 
    
    public WebDriver OpenBrowser()
    {
        switch (BrowserName.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/selenium_driver/chromedriver.exe");
			objDriver = new ChromeDriver();
			break;
		case "EDGE":
			System.setProperty("webdriver.edge.driver", "./src/test/resources/selenium_driver/msedgedriver.exe");
			objDriver = new EdgeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.geckodriver.driver", "./src/test/resources/selenium_driver/gecko.exe");
			objDriver = new FirefoxDriver();
			break;
		default:
			System.out.println("The browser is "+ BrowserName +" not supported.");
			break;
		}
        
        objDriver.manage().window().maximize();
		objDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		objDriver.manage().deleteAllCookies();
        return objDriver;
    }
    
    
    

    public void CloseBrowser()
    {
        objDriver.close();
        objDriver.quit();
    }

    public void NavigateToUrl(String pstrUrl)
    {
        objDriver.get(pstrUrl);
    }
	
	
}
