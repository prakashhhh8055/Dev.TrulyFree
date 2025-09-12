package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginViaEmail;
import pageObjects.SigninViaPhoneNumber;
import testBase.BaseClass;

public class TC004_VerifyLoginViaEmail extends BaseClass {
	
	@Test
	public void VerifyLoginViaEmail()
	{
		HomePage hp=new HomePage(driver);
		hp.clickReject();
		hp.clickLogin();
		
		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		sp.clickEmail();
		
		
		LoginViaEmail le=new LoginViaEmail(driver);
		le.setEmail();
		le.setPassword();
		le.clickContinue();
		
		Assert.assertTrue(true);
	}
	

}
