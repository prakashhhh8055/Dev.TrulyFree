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
		//hp.clickReject();
		hp.clickLogin();
		Thread.sleep(4000);
		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		sp.clickSignup();
		
		SignupViaPhoneNumber su=new SignupViaPhoneNumber(driver);
		su.clickEmail();
		
		SignupViaEmail se=new SignupViaEmail(driver);
		BaseClass bc=new BaseClass();
		se.setEmail(bc.randomString()+"@gmail.com");
		se.setPassword(bc.randomAlphaNumeric());
		se.selectTCAndPrivacy();
		se.clickContinue();
		
		AccountInfo ac=new AccountInfo(driver);
		ac.setFirstName(bc.randomString());
		ac.setLastName(bc.randomString());
		ac.setPhonenumber(bc.generatePlainUSPhone());
		ac.clickDone();
		Thread.sleep(10000);
		
		SignUpConfirmation sc=new SignUpConfirmation(driver);
		sc.clickGettingStartedWithTF();
		
		Assert.assertEquals(sc.isMyAccountCreated(),true);
		
		logger.info("*****Finished TC001_SignupViaEmailTest******");
	}

}
