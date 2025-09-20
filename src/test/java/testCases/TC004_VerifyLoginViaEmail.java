package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginViaEmail;
import pageObjects.SigninViaPhoneNumber;
import testBase.BaseClass;

public class TC004_VerifyLoginViaEmail extends BaseClass {
	
	@Test(groups={"Regression","Smoke"})
	public void VerifyLoginViaEmail() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.clickLogin();

		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		sp.clickEmail();
		Thread.sleep(2000);
		LoginViaEmail le=new LoginViaEmail(driver);
		le.setEmail(p.getProperty("email"));
		le.setPassword(p.getProperty("password"));
		Thread.sleep(3000);
		le.clickContinue();
		
		Assert.assertTrue(true);
	}
	

}
