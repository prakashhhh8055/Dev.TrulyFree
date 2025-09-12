package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninViaPhoneNumber;
import pageObjects.SignupViaEmail;
import pageObjects.SignupViaPhoneNumber;
import pageObjects.VerificationCode;
import testBase.BaseClass;

public class TC002_SignupViaPhoneNumberTest extends BaseClass{
	
	@Test
	public void verify_SignupViaPhoneNumber() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.handlePopupIfPresent();
		hp.clickReject();
		hp.clickLogin();
		
		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		sp.clickSignup();
		
		Thread.sleep(2000);
		
		SignupViaPhoneNumber su=new SignupViaPhoneNumber(driver);
		su.setPhoneNum();
		
		SignupViaEmail se=new SignupViaEmail(driver);
		se.selectTCAndPrivacy();
		se.clickContinue();
		
		VerificationCode vc=new VerificationCode(driver);
		vc.setOTP();

		
	}

}
