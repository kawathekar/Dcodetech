package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Utilities.BaseTest;



public class LandingPage extends BaseTest{
	
	//Webelement
	private By SignIN = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	private By UserNameavail = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	private By IncorrectPass = By.xpath("//span[@class='a-list-item']");
	private By Orders = By.xpath("//a[@id='nav-orders']");
	
	
	//Input
	private By UserName = By.xpath("//input[@id='ap_email']");
	private By Password = By.xpath("//input[@id='ap_password']");
	
	//Button
	private By SingInButton = By.xpath("//header/div[@id='navbar']/div[@id='nav-flyout-anchor']/"
			+ "div[@id='nav-flyout-accountList']/div[2]/div[1]/div[1]/div[1]/a[1]/span[1]");
	private By Continue = By.xpath("//input[@id='continue']");
	private By Submit = By.xpath("//input[@id='signInSubmit']");
	
	public void mousehoverSingin() {		
		 Actions actions = new Actions(driver);
		 WebElement Singin = driver.findElement(SignIN);
		 actions.moveToElement(Singin).perform();
	}
	
	public void ClickingonSingIN() {
		driver.findElement(SingInButton).click();
	}
	
	public void inputUserName(String Username) {
		driver.findElement(UserName).sendKeys(Username);
	}
	
	public void ClickContinue() {
		driver.findElement(Continue).click();
	}
	
	public void inputPassword(String Password1) {
		driver.findElement(Password).sendKeys(Password1);
	}
	
	public void ClickSubmit() {
		driver.findElement(Submit).click();
	}
	
	public void VerifyLogin() {
		String Username = driver.findElement(UserNameavail).getText();
		Assert.assertTrue(Username.contains("Abhishek"));
	}
	
	public void VerifyNegativeLogin() {
		String Username = driver.findElement(IncorrectPass).getText();
		Assert.assertEquals(Username, "Your password is incorrect");
	}
	
	public void ClickOrders() {
		driver.findElement(Orders).click();
	}


}
