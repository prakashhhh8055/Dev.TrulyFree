package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountInfo;
import pageObjects.HomePage;
import pageObjects.SignUpConfirmation;
import pageObjects.SigninViaPhoneNumber;
import pageObjects.SignupViaEmail;
import pageObjects.SignupViaPhoneNumber;
import testBase.BaseClass;

public class TC001_SignupViaEmailTest extends BaseClass {
	
	@Test
	public void verify_SignUpViaEmail() throws InterruptedException
	{
		logger.info("*****Started TC001_SignupViaEmailTest******");
		HomePage hp=new HomePage(driver);
		hp.handlePopupIfPresent();
		hp.clickReject();
		hp.clickLogin();
		
		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		sp.clickSignup();
		
		SignupViaPhoneNumber su=new SignupViaPhoneNumber(driver);
		su.clickEmail();
		
		SignupViaEmail se=new SignupViaEmail(driver);
		se.setEmail();
		se.setPassword();
		se.selectTCAndPrivacy();
		se.clickContinue();
		
		AccountInfo ac=new AccountInfo(driver);
		ac.setFirstName();
		ac.setLastName();
		ac.setPhonenumber();
		ac.clickDone();
		Thread.sleep(10000);
		
		SignUpConfirmation sc=new SignUpConfirmation(driver);
		sc.clickGettingStartedWithTF();
		
		Assert.assertEquals(sc.isMyAccountCreated(),true);
		
		logger.info("*****Finished TC001_SignupViaEmailTest******");
	}

}
