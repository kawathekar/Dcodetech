package Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtilities extends BaseTest{
	public static String Screenshotpath;
	public static String Date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	
	public static void capturescreenshot(String Test_Case) throws Exception{
		File Scrfile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(Scrfile, new File(System.getProperty("user.dir")
				 +"\\target\\Screenshot\\"+Date+"\\"+Test_Case+".jpg"));
	}	

	public static boolean ScrolltoElment ( By Locator) {
		wait(Locator);
		WebElement element = driver.findElement(Locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return true;
	}
	
	
	public static boolean wait ( By Locator) {
		//Declare and initialise a explicit wait
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		//This is the pollintime which will be observed till the wait.
		wait.pollingEvery(Duration.ofMillis(1000));
		//This is how we specify the condition to wait on.
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		wait.until(ExpectedConditions.elementToBeSelected(Locator));
		return true;
	}
	
	
	public static boolean SwitchingFrame ( String Locator , String value ) {
		if (Locator.equalsIgnoreCase("id")) {
			driver.switchTo().frame(value);
		}
		else if (Locator.equalsIgnoreCase("Index")) {
			driver.switchTo().frame(value);
		}
		else if (Locator.equalsIgnoreCase("WebElement")) {
			driver.switchTo().frame(value);
		}
		return true;
	}
	
	public static void SelectDropdown_Single(By Locator, String Type, String Value) {
		Select drp = new Select(driver.findElement(Locator));
		if (Type.equalsIgnoreCase("Value")) {
			drp.selectByValue(Value);
		}else if (Type.equalsIgnoreCase("Index")) {
			drp.selectByIndex(Integer.parseInt(Value));
		}else if (Type.equalsIgnoreCase("Text")) {
			drp.selectByVisibleText(Value);
		}
	}
	
	public static Set gettext_table (By Locator) {
		WebElement table = driver.findElement(Locator);
		List<WebElement> rowsList = table.findElements(By.tagName("tr"));
        List<WebElement> columnsList = null;
        Set<String> set = new HashSet<String>();
       for (WebElement row : rowsList) {
               //System.out.print("This is Row "+row.getText() + ",");
               columnsList = row.findElements(By.tagName("td"));
              for (WebElement column : columnsList) {
            	   System.out.print(column.getText() + ",");
            	   set.add(column.getText());
              }
       		  }
       		  return set;
			  }
	}
