package Tests;

import org.testng.annotations.Test;

import Pages.LandingPage;
import Utilities.BaseTest;


public class LandingPageTest extends LandingPage{
	
	@Test
	public void TC_01_Positivie_Login() {
		this.mousehoverSingin();
		this.ClickingonSingIN();
		this.inputUserName("9867706749");
		this.ClickContinue();
		this.inputPassword("Ovi@2607");
		this.ClickSubmit();
		this.VerifyLogin();
	}
	
	@Test
	public void TC_01_Negative_Login() {
		this.mousehoverSingin();
		this.ClickingonSingIN();
		this.inputUserName("kawathekar.abhishek@gmail.com");
		this.ClickContinue();
		this.inputPassword("Ovi@2617");
		this.ClickSubmit();
		this.VerifyNegativeLogin();
	}
}
