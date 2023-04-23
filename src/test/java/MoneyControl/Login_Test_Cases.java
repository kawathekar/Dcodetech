package MoneyControl;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.BasePage_MoneyControl;
import Utilities.BaseTest;
@Listeners(BaseTest.class)
public class Login_Test_Cases extends BaseTest{
	
	@Test
	public static void TC_01_Most_Active_Stock() {
		BasePage_MoneyControl.Top_Gainers_Stock_Price();
	}

	@Test
	public static void TC_02_Perseonal_Finance_Banking() {
		BasePage_MoneyControl.Personal_Finaance();
	}
}
