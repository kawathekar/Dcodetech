package Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Utilities.BaseTest;
import Utilities.TestUtilities;

public class BasePage_MoneyControl extends BaseTest{
	
	// Page Factory for the Page
	//Element
	private static By Login = By.xpath("//a[contains(text(),'Hello, Login')]"); 
	private static By Usernameavail = By.xpath("//span[@class='usr_nm']");
	private static By StockAction = By.xpath("//a[contains(text(),'STOCK ACTION')]");
	private static By TopGains = By.xpath("//body/div[@id='mc_mainWrapper']"
			+ "/section[1]/div[1]/div[3]/aside[1]/div[11]/div[3]/div[2]/div[2]"
			+ "/div[1]/div[2]/div[1]/h2[1]/a[1]");
	private static By PersonalFinance = By.xpath("//header/div[1]/div[3]/nav[1]/div[1]/"
			+ "ul[1]/li[8]/a[1]/span[1]");
	private static By Banking  = By.xpath("//header/div[1]/div[3]/nav[1]/div[1]"
			+ "/ul[1]/li[8]/div[1]/div[1]/ul[1]/li[1]/ul[1]/li[5]/a[1]");
	private static By Explainer = By.xpath("//h2[contains(text(),'Explainer')]");
	
	//Input
	private static By UsernameInput = By.xpath("/html/body/div[1]/div/div[3]/div[1]/"
			+ "div[2]/div[5]/div/form/div[1]/input");
	private static By PasswordInput = By.xpath("/html/body/div[1]/div/div[3]/div[1]/"
			+ "div[2]/div[5]/div/form/div[3]/input[2]");
	
	
	
	//Button
	private static By LoginButton = By.xpath("//a[@class='btn_signin dropdown-toggle linkSignIn active']");
	private static By LoginButtonActive = By.xpath("//button[@id='ACCT_LOGIN_SUBMIT']");
	
	//Dropdown
	private static By Exchange_StockAction = By.xpath("//body/div[@id='mc_mainWrapper']/section[1]"
			+ "/div[1]/div[3]/aside[1]/div[11]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/select[1]");
	
	private static By Exchange_TopGainers = By.xpath("//body/div[@id='mc_mainWrapper']/section[1]/div[1]"
			+ "/div[3]/aside[1]/div[11]/div[3]/div[2]/div[2]/div[1]/div[2]/div[1]/select[1]");
	
	private static By Table_TopGainers = By.xpath("//body/div[@id='mc_mainWrapper']/section[1]/div[1]/"
			+ "div[3]/aside[1]/div[11]/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/table[1]");
	
	public static void Clicking_Login() {
		Actions actions = new Actions(driver);
		WebElement Singin = driver.findElement(Login);
		actions.moveToElement(Singin).perform();
		driver.findElement(LoginButton).click();
	}

	public static void Inputing_Credentials(String Username, String Password) {
		TestUtilities.SwitchingFrame("id", "myframe");
		driver.findElement(UsernameInput).click();
		driver.findElement(UsernameInput).sendKeys(Username);
		driver.findElement(PasswordInput).click();
		driver.findElement(PasswordInput).sendKeys(Password);
		driver.findElement(LoginButtonActive).click();
		//TestUtilities.wait(Usernameavail);
		String Username1 = driver.findElement(Usernameavail).getText();
		Assert.assertTrue(Username1.endsWith("Kawathekar"), "Username ends with Kawathekar");
	}
	
	public static void Stock_Action_Stock_Price() {
		TestUtilities.ScrolltoElment(StockAction);
		Set <String>AllText = TestUtilities.gettext_table(Table_TopGainers);
		Assert.assertTrue(AllText.contains("Axis Bank"), "Table contains Axis Bank");
	}
	
	public static void Top_Gainers_Stock_Price() {
		TestUtilities.ScrolltoElment(TopGains);
		TestUtilities.SelectDropdown_Single(Exchange_TopGainers, "Text", "Sensex");
	}
	
	public static void Personal_Finaance (){
		TestUtilities.MouseHover(PersonalFinance);
		TestUtilities.wait_visibility(Banking);
		driver.findElement(Banking).click();
		TestUtilities.ScrolltoElment(Explainer);
	}
}
