package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Utilities.TestUtilities;

public class OrdersPage extends LandingPage{
	
	private By Order1 = By.xpath("//span[@class='num-orders']");
	private By Browsinghostory = By.xpath("//a[contains(text(),'Conditions of Use & Sale')]");
	private By scrollLang = By.xpath("//span[@class='nav-arrow icp-up-down-arrow']");
	private By HindiLang = By.xpath("//span[contains(text(),'HI')]"
			+ "/ancestor::div[@id='nav-flyout-icp-footer-flyout']");
	
	
	public void VerifyOrdersGiven(String Order) {
		String Orders = driver.findElement(Order1).getText();
		Assert.assertTrue(Orders.contains(Order));	
	}
	
	public void OrderScroll() throws Exception {
		TestUtilities.ScrolltoElment(Browsinghostory);
	}
	
	public void Language_Hindi() throws Exception {
		Actions actions = new Actions(driver);
		WebElement Singin = driver.findElement(scrollLang);
		actions.moveToElement(Singin).perform();
		driver.findElement(HindiLang).click();		
	}

}
