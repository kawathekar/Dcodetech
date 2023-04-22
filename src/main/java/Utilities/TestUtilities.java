package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtilities extends BaseTest{
	public static String Screenshotpath;
	public static String Date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	
	public static void capturescreenshot(String Test_Case) throws Exception{
		File Scrfile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(Scrfile, new File(System.getProperty("user.dir")
				 +"\\target\\Screenshot\\"+Date+"\\"+Test_Case+".jpg"));
	}	

	public static boolean ScrolltoElment ( By Locator) throws Exception {
	
		//Declare and initialise a Implicit wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//This is how we specify the condition to wait on.
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
		
		WebElement element = driver.findElement(Locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return true;
	}


	}
