package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements ITestListener {

	@Override
	public void onTestFailure(ITestResult Result) {
		System.out.println("The name of the testcase Failed is :"+Result.getName());
		try {
			TestUtilities.capturescreenshot(Result.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override		
    public void onTestStart(ITestResult Result)					
    {		
    System.out.println("The name of the testcase Started is : "+Result.getName());					
    }	
	
    @Override		
    public void onTestSuccess(ITestResult Result)					
    {		
    System.out.println("The name of the testcase passed is :"+Result.getName());					
    }	

	private Properties ObjConfig;
	protected static WebDriver driver;
	
	@BeforeMethod
	public WebDriver intializeWebdriver() throws FileNotFoundException, IOException {
		this.ObjConfig = new Properties();
		this.ObjConfig
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
			driver.manage().timeouts().implicitlyWait(
					(long) Integer.parseInt(ObjConfig.getProperty("ImplicitWait").trim()), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(
					(long) Integer.parseInt(ObjConfig.getProperty("Pageloadtimeout").trim()), TimeUnit.SECONDS);
			driver.get(ObjConfig.getProperty("URL").trim().toLowerCase());
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
