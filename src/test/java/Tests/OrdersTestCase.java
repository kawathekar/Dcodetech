package Tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.OrdersPage;
import Utilities.BaseTest;


@Listeners(BaseTest.class)
public class OrdersTestCase extends OrdersPage{
	
	@Test
	public void VerifyingOrders() throws Exception {
		this.mousehoverSingin();
		this.ClickingonSingIN();
		this.inputUserName("kawathekar.abhishek@gmail.com");
		this.ClickContinue();
		this.inputPassword("Ovi@2607");
		this.ClickSubmit();
		this.ClickOrders();
		this.VerifyOrdersGiven("2");
	}
	
	@Test
	public void ScrollingdowninOrders() throws Exception {
		this.mousehoverSingin();
		this.ClickingonSingIN();
		this.inputUserName("9867706749");
		this.ClickContinue();
		this.inputPassword("Ovi@2607");
		this.ClickSubmit();
		this.ClickOrders();
		this.VerifyOrdersGiven("2");
		this.OrderScroll();
		this.Language_Hindi();
	}

}
