package testCases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SigninViaPhoneNumber;
import pageObjects.SignupViaEmail;
import pageObjects.SignupViaPhoneNumber;
import pageObjects.VerificationCode;
import testBase.BaseClass;

public class TC002_SignupViaPhoneNumberTest extends BaseClass{
	
	@Test(groups={"Regression","Sanity"})
	public void verify_SignupViaPhoneNumber() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		
		SigninViaPhoneNumber sp=new SigninViaPhoneNumber(driver);
		Thread.sleep(2000);
		sp.clickSignup();
		
		Thread.sleep(2000);
		
		SignupViaPhoneNumber su=new SignupViaPhoneNumber(driver); 
		BaseClass bc=new BaseClass(); 
		su.setPhoneNum(bc.generatePlainUSPhone());
		Thread.sleep(2000);
		
		SignupViaEmail se=new SignupViaEmail(driver);
		se.selectTCAndPrivacy();
		se.clickContinue();
		
		VerificationCode vc=new VerificationCode(driver);
		vc.setOTP(p.getProperty("otp"));

	}

}
