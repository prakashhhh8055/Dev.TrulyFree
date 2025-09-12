package pageObjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class SigninViaPhoneNumber extends BasePage {

	BaseClass bc=new BaseClass();
	Properties p=new Properties();
	public SigninViaPhoneNumber(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='Sign Up']") WebElement linkSignup;
	@FindBy(xpath="//input[@id='loginPhoneInput']") WebElement txtPhoneNumber;
	@FindBy(xpath="//button[normalize-space()='CONTINUE']") WebElement btnContinue;
	@FindBy(xpath="//span[normalize-space()='Email']") WebElement linkEmail;
	
	public void setPhoneNumber()
	{
		txtPhoneNumber.sendKeys(p.getProperty("phonenumber"));
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public void clickSignup()
	{
		linkSignup.click();
	}
	
	public void clickEmail()
	{
		linkEmail.click();
	}

}
