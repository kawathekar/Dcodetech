package MoneyControl;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.BasePage_MoneyControl;
import Utilities.BaseTest;
@Listeners(BaseTest.class)
public class Login_Test_Cases extends BaseTest{
	
	@Test
	public static void TC_01_Most_Active_Stock() {
		BasePage_MoneyControl.Stock_Action_Stock_Price();
		
	}

}
