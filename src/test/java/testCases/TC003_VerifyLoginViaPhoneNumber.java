package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninViaPhoneNumber;
import pageObjects.VerificationCode;
import testBase.BaseClass;

public class TC003_VerifyLoginViaPhoneNumber extends BaseClass {
	
	@Test
	public void VerifyLoginViaPhoneNumber() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		
		Thread.sleep(2000);
		
		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		sp.setPhoneNumber(p.getProperty("phonenumber"));
		Thread.sleep(3000);
		sp.clickContinue();
		Thread.sleep(2000);
		VerificationCode vc=new VerificationCode(driver);
		vc.setOTP(p.getProperty("otp"));
	}

}
