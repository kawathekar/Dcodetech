package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements ITestListener{

	
	  @Override public void onTestFailure(ITestResult Result) {
	  System.out.println("The name of the testcase Failed is :"+Result.getName());
	  try { 
		  TestUtilities.capturescreenshot(Result.getName()); 
		  } catch (Exception e) {
			  
		  }
	  
	  }
	  
	  @Override public void onTestStart(ITestResult Result) {
	  System.out.println("The name of the testcase Started is : "+Result.getName())
	  ; }
	  
	  @Override public void onTestSuccess(ITestResult Result) {
	  System.out.println("The name of the testcase passed is :"+Result.getName());
	  }
	 
	private Properties ObjConfig;
	protected static WebDriver driver;
	
	/*@Method = IntializeWebdriver
	 * @Parameter = None
	 * @Description = Intitialize browsers according to properties file and URL
	*/
	@BeforeMethod
	public WebDriver intializeWebdriver() throws FileNotFoundException, IOException {
		this.ObjConfig = new Properties();
		this.ObjConfig
				/* User.dir is dynamic path of the project of that class
				*/
				.load(new FileInputStream(System.getProperty("user.dir") 
						+ "/src/test/resources/config,properties"));
		try {
			String Browser = ObjConfig.getProperty("Browser").trim().toLowerCase();
			if (Browser.equalsIgnoreCase("Chrome")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				if (ObjConfig.getProperty("Incognito").equalsIgnoreCase("true"))
					chromeOptions.addArguments("--incognito");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(chromeOptions);
			} else if (Browser.equalsIgnoreCase("Edge")) {
				EdgeOptions edgeoptions = new EdgeOptions();
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(edgeoptions);
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(
					(long) Integer.parseInt(ObjConfig.getProperty("ImplicitWait").trim()), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(
					(long) Integer.parseInt(ObjConfig.getProperty("Pageloadtimeout").trim()), TimeUnit.SECONDS);
			driver.get(ObjConfig.getProperty("URL").trim().toLowerCase());
		// Explicit Wait
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.
					visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'moneycontrol.com')]")));
			driver.findElement(By.xpath("//a[contains(text(),'moneycontrol.com')]")).click();
			wait.until(ExpectedConditions.
					visibilityOfAllElementsLocatedBy(By.xpath("//button[@id='wzrk-cancel']")));
			driver.findElement(By.xpath("//button[@id='wzrk-cancel']")).click();
			return driver;
		} catch (Exception var) {
			var.printStackTrace();
			return null;
		}
	}
	
	
	  @AfterMethod
	  public void teardown() { 
		  driver.close(); 
		  driver.quit(); 
		  }
	 
	}
