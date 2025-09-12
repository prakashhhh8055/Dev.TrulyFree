package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class SignupViaPhoneNumber extends BasePage{
	
	BaseClass bc=new BaseClass();
	public SignupViaPhoneNumber(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="//span[normalize-space()='Email']") WebElement linkEmail;
	@FindBy(xpath="//input[@id='regPhoneInput']") WebElement txtPhoneNum;
	
	public void setPhoneNum()
	{
		txtPhoneNum.sendKeys(bc.generateUSPhoneNumber());
	}
	
	public void clickEmail()
	{
		linkEmail.click();
	}
	
}
